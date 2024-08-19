package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.resp.UserInfoResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiChannel;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiFollow;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.query.ChannelQuery;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiChannelService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiFollowService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ReaiChannel 服务实现类
 * </p>
 *
 * @author couei
 * @since 2024-07-27
 */
@Service
@RequiredArgsConstructor
public class ReaiChannelServiceImpl extends DefaultService implements ReaiChannelService {

    // 定义表名常量
    public static final String TABLE_NAME = "reai_channel";

    private final ReaiFollowService reaiFollowService;

    /**
     * 列表查询方法
     *
     * @param page 分页信息
     * @return 查询结果列表
     */
    @Override
    public List<ReaiChannel> list(Page page) {
        // 创建参数映射
        ValueMap params = new ValueMap();
        // 创建查询构建器
        SelectBuilder selectBuilder = new SelectBuilder(params);
        // 构建查询语句
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)).where()
            .and("name", ConditionType.EQUALS, ReaiChannel.NAME) // 添加条件
            .orderBy().desc("create_time"); // 添加排序
        // 执行查询并返回结果
        return super.listForBean(selectBuilder.build(), page, ReaiChannel::new);
    }

    @Override
    public List<ChannelQuery> listQuery(Integer isOfficial) {
        // 根据是否是官方版块获取版块列表，1: 官方版块，2：用户版块
        List<ReaiChannel> channelList = getChannel(isOfficial);

        // 获取所有加入版块的用户，根据版块id查询 根据channelList中的id
        List<String> channelId = channelList.stream().map(ReaiChannel::getChannelId).toList();
        List<ReaiFollow> followList = getFollowList(channelId);

        // 批量获取用户id
        List<String> userIdList = followList.stream().map(ReaiFollow::getUserId)
            .collect(Collectors.toList());
        // 根据用户id批量查询用户信息
        List<ReaiUsers> userList = getUserList(userIdList);

        // 构建ChannelQuery对象列表
        List<ChannelQuery> channelQueryList = new ArrayList<>();
        // 将查询结果转换为ChannelQuery对象并添加到列表中
        for (ReaiChannel reaiChannel : channelList) {
            ChannelQuery channelQuery = new ChannelQuery();
            BeanUtils.copyProperties(reaiChannel, channelQuery);

            // 获取当前版块的关注记录
            List<ReaiFollow> currentChannelFollows = followList.stream()
                .filter(follow -> follow.getFollowChannelId().equals(reaiChannel.getId()))
                .collect(Collectors.toList());

            // 设置成员数量
            channelQuery.setMemberCount(currentChannelFollows.size());

            boolean isJoined = false; // 默认值为false
            //是否登录
            boolean login = StpUtil.isLogin();
            if (login) {
                // 获取当前登录人
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                String currentUserId = tokenInfo.getLoginId().toString();
                // 判断当前用户是否已加入版块
                isJoined = currentChannelFollows.stream()
                    .anyMatch(follow -> follow.getUserId().equals(currentUserId));
            }
            channelQuery.setIsJoin(isJoined);

            // 将用户信息添加到ChannelQuery对象中
            List<UserInfoResp> userInfoRespList = userList.stream()
                .filter(user -> currentChannelFollows.stream()
                    .anyMatch(follow -> follow.getUserId().equals(user.getUserId())))
                .map(user -> {
                    UserInfoResp userInfoResp = new UserInfoResp();
                    BeanUtils.copyProperties(user, userInfoResp);
                    return userInfoResp;
                })
                .collect(Collectors.toList());

            channelQuery.setUserList(userInfoRespList);

            channelQueryList.add(channelQuery);
        }

        return channelQueryList;
    }


    /**
     * 根据是否是官方版块 获取版块列表 1:官方版块 2：用户版块
     *
     * @param isOfficial
     * @return
     */
    public List<ReaiChannel> getChannel(Integer isOfficial) {
        ValueMap params = new ValueMap();
        params.put(ReaiChannel.IS_OFFICIAL, isOfficial);
        // 创建查询构建器
        SelectBuilder selectBuilder = new SelectBuilder(params);
        // 构建查询语句
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)).where()
            .and("is_official", ConditionType.EQUALS, ReaiChannel.IS_OFFICIAL)
            .orderBy().desc("create_time"); // 添加排序
        return listForBean(selectBuilder.build(), ReaiChannel::new);
    }

    public List<ReaiFollow> getFollowList(List<String> channelId) {
        ValueMap params = new ValueMap();
        params.put(ReaiFollow.FOLLOW_CHANNEL_ID, channelId);
        // 创建查询构建器
        SelectBuilder selectBuilder = new SelectBuilder(params);
        // 构建查询语句
        selectBuilder.from("", super.getEntityDef(ReaiFollowServiceImpl.TABLE_NAME)).where()
            .and("follow_channel_id", ConditionType.IN, ReaiFollow.FOLLOW_CHANNEL_ID)
            .orderBy().desc("follow_time"); // 添加排序
        return listForBean(selectBuilder.build(), ReaiFollow::new);
    }

    public List<ReaiUsers> getUserList(List<String> userIdList) {
        ValueMap params = new ValueMap();
        params.put(ReaiUsers.USER_ID, userIdList);
        // 创建查询构建器
        SelectBuilder selectBuilder = new SelectBuilder(params);
        // 构建查询语句
        selectBuilder.from("", super.getEntityDef(ReaiUsersServiceImpl.TABLE_NAME)).where()
            .and("user_id", ConditionType.IN, ReaiUsers.USER_ID)
            .orderBy().desc("registration_time"); // 添加排序
        return listForBean(selectBuilder.build(), ReaiUsers::new);
    }

    /**
     * 根据 ID 查询
     *
     * @param id 主键 ID
     * @return 查询结果
     */
    @Override
    public Object getById(String id) {
        // 创建参数映射并添加 ID 参数
        ValueMap params = new ValueMap();
        params.put(ReaiChannel.ID, id);
        // 创建查询构建器
        SelectBuilder selectBuilder = new SelectBuilder(params);
        // 构建查询语句
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)).where()
            .and("id", ConditionType.EQUALS, ReaiChannel.ID) // 添加条件
            .orderBy().desc("create_time"); // 添加排序
        // 执行查询并返回结果
        return getForBean(selectBuilder.build(), ReaiChannel::new);
    }

    /**
     * 保存数据
     *
     * @param reaiChannel 实体对象
     * @return 保存后的实体对象
     */
    @Override
    public ReaiChannel save(ReaiChannel reaiChannel) {
        reaiChannel.setFollowCount(0);
        reaiChannel.setPostCount(0);
        reaiChannel.setEssenceCount(0);
        String channelId = super.add(TABLE_NAME, reaiChannel).toString();
        // 保存数据到数据库
        ReaiFollow follow = new ReaiFollow();
        follow.setUserId(reaiChannel.getCreator());
        follow.setFollowChannelId(channelId);
        reaiFollowService.add(follow);
        // 返回保存后的对象
        return reaiChannel;
    }

    @Override
    public boolean joinChannel(ReaiChannel channel) {
        if (channel == null) {
            return false;
        }
        ReaiFollow follow = new ReaiFollow();
        follow.setUserId(channel.getCreator());
        follow.setFollowChannelId(channel.getId());
        reaiFollowService.add(follow);
        return true;
    }

    @Override
    public boolean quitChannel(String id) {
        if (id == null) {
            return false;
        }
        reaiFollowService.quitFollow(id);
        return true;
    }
}
