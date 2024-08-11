package com.newadmin.demoservice.mainPro.livepro.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.demoservice.config.srs.annotation.SrsProperties;
import com.newadmin.demoservice.mainPro.livepro.model.entity.UserLiveRoomDO;
import com.newadmin.demoservice.mainPro.livepro.model.query.LiveRoomQuery;
import com.newadmin.demoservice.mainPro.livepro.model.req.LiveRoomReq;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveResp;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveRoomDetailResp;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveRoomResp;
import com.newadmin.demoservice.mainPro.livepro.service.LiveRoomService;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 直播间业务实现
 *
 * @author couei
 * @since 2024/08/05 21:29
 */
@Service
@RequiredArgsConstructor
public class LiveRoomServiceImpl extends DefaultService implements LiveRoomService {

    public static final String TABLE_NAME = "live_room";

    private final SrsProperties srsProperties;

    /**
     * 分页查询直播房间列表
     *
     * @param query 查询条件对象
     * @param page  分页信息对象
     * @return 直播房间响应列表
     */
    @Override
    public List<LiveRoomResp> page(LiveRoomQuery query, Page page) {
        // 创建查询构建器
        SelectBuilder selectBuilder = new SelectBuilder();

        // 设置查询的表名，并构建基本查询
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where() // 添加WHERE条件（如果有）
            .orderBy().desc("create_time"); // 按照创建时间倒序排列

        // 返回结果列表
        return super.listForBean(selectBuilder.build(), LiveRoomResp::new);
    }

    /**
     * 获取直播房间详情
     *
     * @param isShow 显示状态
     * @param id     直播房间ID
     * @return 直播房间详情响应对象
     */
    @Override
    public LiveRoomDetailResp getDetail(Integer isShow, String id) {
        // 构建参数映射，用于WHERE条件
        ValueMap param = new ValueMap();
        param.put(LiveRoomDetailResp.IS_SHOW, isShow); // 添加显示状态条件
        param.put("id", id); // 添加直播房间ID条件

        // 创建查询构建器，并初始化参数
        SelectBuilder selectBuilder = new SelectBuilder(param);

        // 设置查询的表名，并构建WHERE条件
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("is_show", ConditionType.EQUALS, LiveRoomDetailResp.IS_SHOW) // 添加显示状态的条件
            .and("id", ConditionType.EQUALS, "id"); // 添加ID的条件

        // 返回查询结果，映射为响应对象
        return super.getForBean(selectBuilder.build(), LiveRoomDetailResp::new);
    }

    @Override
    public String add(LiveRoomResp req) {
        //获取当前登录的用户信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        Assert.notNull(tokenInfo.loginId, "用户未登录");

        // 构建参数映射，用于WHERE条件
        ValueMap param = new ValueMap();
        param.put("userId", tokenInfo.loginId.toString()); // 添加直播房间的显示状态条件
        // 创建查询构建器，并初始化参数
        SelectBuilder selectBuilder = new SelectBuilder(param);
        selectBuilder.from("", super.getEntityDef(LiveServiceImpl.TABLE_NAME))
            .where()
            .and("user_id", ConditionType.EQUALS, "userId"); // 添加ID的条件);

        LiveResp liveInfo = super.getForBean(selectBuilder.build(), LiveResp::new);
        if (liveInfo == null) {
            req.put("name", req.getName());
            req.setCreatedTime(new Date());
            req.setUpdatedTime(new Date());
            String roomId = super.add(TABLE_NAME, req).toString();

            // 添加用户和直播房间的关系
            UserLiveRoomDO userLiveRoomDO = new UserLiveRoomDO();
            userLiveRoomDO.setUserId(tokenInfo.loginId.toString());
            userLiveRoomDO.setLiveRoomId(roomId);
            userLiveRoomDO.setCreateTime(new Date());
            userLiveRoomDO.setUpdateTime(new Date());
            super.add("user_live_room", userLiveRoomDO);
            // 设置推流地址
            req.put("id", roomId);
            req.put("secretKey", req.get("key"));
            req.put("rtmpUrl", srsProperties.getPushUrl() + srsProperties.getPushPath() + "/roomId_"
                + roomId); // 设置拉流地址
            req.put("flvUrl",
                srsProperties.getPullUrl() + srsProperties.getPushPath() + "/roomId_" + roomId
                    + ".flv"); // 设置拉流地址
            req.put("hlsUrl",
                srsProperties.getPullUrl() + srsProperties.getPushPath() + "/roomId_" + roomId
                    + ".m3u8"); // 设置拉流地址
            super.update(TABLE_NAME, req);
        }
        return null;
    }

    @Override
    public void update(LiveRoomReq req) {
        super.update(TABLE_NAME, req);
    }

    @Override
    public void delete(String id) {
        super.delete(TABLE_NAME, new String[]{id});
    }

}