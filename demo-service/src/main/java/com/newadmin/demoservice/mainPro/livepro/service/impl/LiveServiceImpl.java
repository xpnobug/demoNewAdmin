package com.newadmin.demoservice.mainPro.livepro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.newadmin.democonfig.redisCommon.util.RedisUtils;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.demoservice.mainPro.livepro.model.query.LiveQuery;
import com.newadmin.demoservice.mainPro.livepro.model.query.LiveRoomQuery;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveDetailResp;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveResp;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveRoomDetailResp;
import com.newadmin.demoservice.mainPro.livepro.service.LiveRoomService;
import com.newadmin.demoservice.mainPro.livepro.service.LiveService;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.query.UserInfoQuery;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 直播记录业务实现
 *
 * @author couei
 * @since 2024/08/05 22:02
 */
@Service
@RequiredArgsConstructor
public class LiveServiceImpl extends DefaultService implements LiveService {

    public static final String TABLE_NAME = "live";

    private final LiveRoomService liveRoomService;
    private final ReaiUsersService usersService;

    /**
     * 分页查询直播列表，并填充每个直播记录的直播房间详情
     *
     * @param query 查询条件对象，包含直播房间的显示状态等信息
     * @param page  分页信息对象，包含当前页码和每页记录数
     * @return 直播响应列表，每个直播记录包含对应的直播房间详情
     */
    @Override
    public List<LiveResp> page(LiveQuery query, Page page) {
        // 构建参数映射，用于WHERE条件
        ValueMap param = new ValueMap();
        param.put("isShow", query.getLiveRoomIsShow()); // 添加直播房间的显示状态条件

        // 创建查询构建器，并初始化参数
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("socket_id", ConditionType.IS_NOT_NULL, LiveResp.SOCKET_ID)// 添加WHERE条件（如果有）
            .orderBy().desc("create_time"); // 按照创建时间倒序排列

        // 执行查询并分页返回结果
        List<LiveResp> liveResps = super.listForBean(selectBuilder.build(), page, LiveResp::new);

        // 为每个直播记录获取并设置对应的直播房间详情
        liveResps.forEach(liveResp -> {
            // 获取用户信息
            ReaiUsers userInfo = usersService.getUserInfo(liveResp.getUserId());
            UserInfoQuery userInfoResp = BeanUtil.copyProperties(userInfo, UserInfoQuery.class);
            // 根据直播房间的显示状态和直播房间ID获取详情
            LiveRoomDetailResp detail = liveRoomService.getDetail(query.getLiveRoomIsShow(),
                liveResp.getLiveRoomId());
            // 设置直播房间详情到当前直播记录中
            liveResp.setUserInfoQuery(userInfoResp);
            liveResp.setLiveRoom(detail);
        });

        return liveResps; // 返回包含详情的直播响应列表
    }

    @Override
    public LiveDetailResp getDetail(String id) {
        // 构建参数映射，用于WHERE条件
        ValueMap param = new ValueMap();
        param.put(LiveDetailResp.LIVE_ROOM_ID, id); // 添加显示状态条件

        // 创建查询构建器，并初始化参数
        SelectBuilder selectBuilder = new SelectBuilder(param);

        // 设置查询的表名，并构建WHERE条件
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("live_room_id", ConditionType.EQUALS, LiveDetailResp.LIVE_ROOM_ID); // 添加显示状态的条件
        return super.getForBean(selectBuilder.build(), LiveDetailResp::new);
    }

    @Override
    public LiveRoomQuery getLiveRoomByUserId(String userId) {
        // 构建参数映射，用于WHERE条件
        ValueMap param = new ValueMap();
        param.put("userId", userId); // 添加直播房间的显示状态条件
        // 创建查询构建器，并初始化参数
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("user_id", ConditionType.EQUALS, "userId");
        LiveResp liveInfo = super.getForBean(selectBuilder.build(), LiveResp::new);

        if (liveInfo != null) {
            LiveRoomDetailResp roomInfo = liveRoomService.getDetail(0, liveInfo.getLiveRoomId());
            // 获取用户信息
            ReaiUsers userInfo = usersService.getUserInfo(userId);
            UserInfoQuery userInfoResp = BeanUtil.copyProperties(userInfo, UserInfoQuery.class);

            LiveRoomQuery liveRoomQuery = new LiveRoomQuery();
            liveRoomQuery.put("id", liveInfo.getId());
            liveRoomQuery.put("liveRoomId", liveInfo.getLiveRoomId());
            liveRoomQuery.put("created_at", liveInfo.getCreatedTime());
            liveRoomQuery.put("updated_at", liveInfo.getUpdatedTime());
            liveRoomQuery.put("live_room", roomInfo);
            liveRoomQuery.put("user", userInfoResp);

            return liveRoomQuery;
        }
        return null;
    }
//
//      @Override
//      public String add(LiveReq req) {
//            return super.add(TABLE_NAME, req).toString();
//      }
//
//      @Override
//      public void update(LiveReq req) {
//          super.update(TABLE_NAME, req);
//      }

    @Override
    public void delete(String id) {
        super.delete(TABLE_NAME, new String[]{id});
    }

    @Override
    public void updateLiveRoom(String id, String socketId) {
        String liveInfoKey = RedisUtils.formatKey("liveInfo", id);
        LiveDetailResp liveInfo = RedisUtils.get(liveInfoKey);
        if (liveInfo == null) {
            liveInfo = getDetail(id);
            RedisUtils.set(liveInfoKey, liveInfo, Duration.ofMinutes(10)); // 设置缓存有效期为10分钟
        }
        LiveResp liveResp = new LiveResp();
        liveResp.setId(liveInfo.getId());
        if (Objects.equals(socketId, "roomNoLive")) {
            liveResp.setSocketId(null);
        } else {
            liveResp.setSocketId(socketId);
        }
        super.update(TABLE_NAME, liveResp);
    }

}