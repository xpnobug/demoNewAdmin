package com.newadmin.demoservice.mainPro.ltpro.query;

import com.newadmin.democore.kduck.definition.BeanEntityDef;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import com.newadmin.demoservice.mainPro.ltpro.service.impl.ReaiArticleServiceImpl;
import com.newadmin.demoservice.mainPro.ltpro.service.impl.ReaiFollowServiceImpl;
import com.newadmin.demoservice.mainPro.ltpro.vo.Statistics;
import org.springframework.stereotype.Service;

@Service
public class StatisticsQuery extends DefaultService {

    /**
     * 1.计算用户发布的文章数量
     */
    public long getUserArticleCount(String userId) {
        ValueMap params = new ValueMap();
        params.put("userId", userId);
        // 构建查询构造器
        SelectBuilder selectBuilder = new SelectBuilder(params);
        BeanEntityDef articleBean = super.getEntityDef(ReaiArticleServiceImpl.TABLE_NAME);
        // 查询文章数量
        selectBuilder.from("", articleBean)
            .where()
            .and("user_id", ConditionType.EQUALS, ReaiUsers.USER_ID);
        return super.count(selectBuilder.build());
    }

    /**
     * 2.计算用户关注的用户数量
     */
    public long getUserFollowCount(String userId) {
        ValueMap params = new ValueMap();
        params.put("userId", userId);
        // 构建查询构造器
        SelectBuilder selectBuilder = new SelectBuilder(params);
        BeanEntityDef followBean = super.getEntityDef(ReaiFollowServiceImpl.TABLE_NAME);
        // 查询文章数量
        selectBuilder.from("", followBean)
            .where()
            .and("user_id", ConditionType.EQUALS, ReaiUsers.USER_ID);
        return super.count(selectBuilder.build());
    }

    /**
     * 3.计算用户粉丝数量
     */
    public long getUserFansCount(String userId) {
        ValueMap params = new ValueMap();
        params.put("userId", userId);
        // 构建查询构造器
        SelectBuilder selectBuilder = new SelectBuilder(params);
        BeanEntityDef followBean = super.getEntityDef(ReaiFollowServiceImpl.TABLE_NAME);
        // 查询文章数量
        selectBuilder.from("", followBean)
            .where()
            .and("follow_user_id", ConditionType.EQUALS, ReaiUsers.USER_ID);
        return super.count(selectBuilder.build());
    }

    /**
     * 综合统计
     */
    public Statistics getUserStatistics(String userId) {
        String sql = "SELECT\n"
            + "\tu.user_id AS userId,\n"
            + "\tCOALESCE ( a.article_count, 0 ) AS articleCount,\n"
            + "\tCOALESCE ( f1.following_count, 0 ) AS followingCount,\n"
            + "\tCOALESCE ( f2.follower_count, 0 ) AS followerCount \n"
            + "FROM\n"
            + "\treai_users u\n"
            + "\tLEFT JOIN ( SELECT user_id, COUNT(*) AS article_count FROM reai_article GROUP BY user_id ) a ON u.user_id = a.user_id\n"
            + "\tLEFT JOIN ( SELECT user_id, COUNT(*) AS following_count FROM reai_follow GROUP BY user_id ) f1 ON u.user_id = f1.user_id\n"
            + "\tLEFT JOIN ( SELECT follow_user_id, COUNT(*) AS follower_count FROM reai_follow GROUP BY follow_user_id ) f2 ON u.user_id = f2.follow_user_id \n"
            + "WHERE\n"
            + "\tu.user_id =  '" + userId + "' ";
        SelectBuilder selectBuilder = new SelectBuilder(sql);
        return super.getForBean(selectBuilder.build(), Statistics::new);
    }
}
