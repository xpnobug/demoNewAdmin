package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiFollow;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiFollowService;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author couei
 * @since 2024-05-25
 */
@Service
public class ReaiFollowServiceImpl extends DefaultService implements ReaiFollowService {

    public static final String TABLE_NAME = "reai_follow";

    @Override
    public String add(ReaiFollow reaiFollow) {
        //获取当前登录的用户信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        Assert.notNull(tokenInfo.loginId, "请先登录");
        String userId = tokenInfo.loginId.toString();
        reaiFollow.setUserId(userId);
        reaiFollow.setFollowTime(new Date());
        return super.add(TABLE_NAME, reaiFollow).toString();
    }

    @Override
    public String delById(String id) {
        //获取当前登录的用户信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        Assert.notNull(tokenInfo.loginId, "请先登录");
        String userId = tokenInfo.loginId.toString();
        //根据userid 查询关注列表
        List<ReaiFollow> list = getFollowList(userId, null);
        // 传入的是关注的用户id，根据这个id 去和 list 比较，如果list中存在，就删除
        list.forEach(follow -> {
            if (follow.getFollowUserId().equals(id)) {
                super.delete(TABLE_NAME, new String[]{follow.getId()});
            }
        });
        return null;
    }

    @Override
    public String quitFollow(String channelId) {
        //获取当前登录的用户信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        Assert.notNull(tokenInfo.loginId, "请先登录");
        String userId = tokenInfo.loginId.toString();
        //根据userid 查询关注列表
        List<ReaiFollow> list = getFlowChannelList(userId, channelId);
        // 传入的是关注的用户id，根据这个id 去和 list 比较，如果list中存在，就删除
        list.forEach(follow -> {
            if (follow.getFollowChannelId().equals(channelId)) {
                super.delete(TABLE_NAME, new String[]{follow.getId()});
            }
        });
        return null;
    }

    public List<ReaiFollow> getFlowChannelList(String userId, String channelId) {
        ValueMap params = new ValueMap();
        params.put(ReaiFollow.USER_ID, userId);
        params.put(ReaiFollow.FOLLOW_CHANNEL_ID, channelId);
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME));
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("user_id", ConditionType.EQUALS, ReaiFollow.USER_ID)
            .and("follow_channel_id", ConditionType.EQUALS, ReaiFollow.FOLLOW_CHANNEL_ID);
        return super.listForBean(selectBuilder.build(), null, ReaiFollow::new);
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public List<ReaiFollow> getFollowList(String userId, String followId) {
        ValueMap params = new ValueMap();
        params.put(ReaiFollow.USER_ID, userId);
        params.put(ReaiFollow.FOLLOW_USER_ID, followId);
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME));
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("user_id", ConditionType.EQUALS, ReaiFollow.USER_ID)
            .and("follow_user_id", ConditionType.EQUALS, ReaiFollow.FOLLOW_USER_ID)
            .and("follow_channel_id", ConditionType.IS_NULL, ReaiFollow.FOLLOW_CHANNEL_ID);
        return super.listForBean(selectBuilder.build(), null, ReaiFollow::new);
    }
}
