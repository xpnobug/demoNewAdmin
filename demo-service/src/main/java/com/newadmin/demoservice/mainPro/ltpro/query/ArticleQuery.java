package com.newadmin.demoservice.mainPro.ltpro.query;

import com.newadmin.democore.kduck.definition.BeanEntityDef;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiArticle;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiChannel;
import com.newadmin.demoservice.mainPro.ltpro.service.impl.ReaiArticleServiceImpl;
import com.newadmin.demoservice.mainPro.ltpro.service.impl.ReaiChannelServiceImpl;
import com.newadmin.demoservice.mainPro.ltpro.service.impl.ReaiUsersServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ArticleQuery extends DefaultService {

    /**
     * 分页查询所有数据（首页）
     */
    public List<ReaiArticle> queryArticleAll() {
        // 获取官方版块列表，根据发布平台进行分组 is_official 是否为官方
        ValueMap params = new ValueMap();
        params.put(ReaiChannel.IS_OFFICIAL, "1");

        // 构建查询构造器
        SelectBuilder selectBuilder = new SelectBuilder(params);
        BeanEntityDef articleBean = super.getEntityDef(ReaiArticleServiceImpl.TABLE_NAME);
        BeanEntityDef channelBean = super.getEntityDef(ReaiChannelServiceImpl.TABLE_NAME);
        BeanEntityDef userBean = super.getEntityDef(ReaiUsersServiceImpl.TABLE_NAME);

        selectBuilder.bindFields("article", false, "seoInformation", "editorialSuggestions",
                "recommendations")
            .bindFields("channel", "name", "type")
            .bindFields("user", "userId", "nickName", "avatar", "exp", "level");

        // 设置表连接和条件
        selectBuilder.from("article", articleBean)
            .leftJoinOn("channel", channelBean, "channelId")
            .leftJoinOn("user", userBean, "userId", articleBean)
            .where()
            .and("article.channel_id", ConditionType.EQUALS, "channelId")
            .and("channel.is_official", ConditionType.EQUALS, ReaiChannel.IS_OFFICIAL)
            .and("article.user_id", ConditionType.EQUALS, "userId")
            .orderBy().desc("publish_date");

        // 执行数据库查询并返回结果
        return super.listForBean(selectBuilder.build(), ReaiArticle::new);
    }

    /**
     * 根据用户ID查询文章列表
     *
     * @param userId
     * @return
     */
    public List<ReaiArticle> queryArticleListByUserId(Page page, String userId) {
        // 获取官方版块列表，根据发布平台进行分组 is_official 是否为官方
        ValueMap params = new ValueMap();
//        params.put(ReaiChannel.IS_OFFICIAL, "1");
        params.put("userId", userId);

        // 构建查询构造器
        SelectBuilder selectBuilder = new SelectBuilder(params);
        BeanEntityDef articleBean = super.getEntityDef(ReaiArticleServiceImpl.TABLE_NAME);
        BeanEntityDef userBean = super.getEntityDef(ReaiUsersServiceImpl.TABLE_NAME);

        selectBuilder.bindFields("article", false, "seoInformation", "editorialSuggestions",
                "recommendations", "url")
            .bindFields("user", "userId", "nickName", "avatar", "exp", "level");

        // 设置表连接和条件
        selectBuilder.from("article", articleBean)
            .leftJoinOn("user", userBean, "userId")
            .where()
            .and("article.user_id", ConditionType.EQUALS, ReaiArticle.USER_ID)
            .orderBy().desc("publish_date");

        // 执行数据库查询并返回结果
        return super.listForBean(selectBuilder.build(), page, ReaiArticle::new);
    }

    /**
     * 根据文章ID查询文章信息
     *
     * @param articleId
     * @return
     */
    public ReaiArticle queryArticleInfoById(String articleId) {
        ValueMap params = new ValueMap();
        params.put(ReaiArticle.ARTICLE_ID, articleId);
        SelectBuilder selectBuilder = new SelectBuilder(params);
        BeanEntityDef articleBean = super.getEntityDef(ReaiArticleServiceImpl.TABLE_NAME);
        BeanEntityDef userBean = super.getEntityDef(ReaiUsersServiceImpl.TABLE_NAME);

        selectBuilder.bindFields("article", false, "seoInformation", "editorialSuggestions",
                "recommendations")
            .bindFields("user", "userId", "nickName", "avatar", "exp", "level");

        selectBuilder.from("article", articleBean)
            .innerJoinOn("user", userBean, "userId")
            .where()
            .and("article.article_id", ConditionType.EQUALS, ReaiArticle.ARTICLE_ID)
            .orderBy().desc("publish_date");
        //官方版块文章列表
        return super.getForBean(selectBuilder.build(), ReaiArticle::new);
    }

    /**
     * 分页查询文章列表
     *
     * @param page
     * @return
     */
    public List<ReaiArticle> queryPageArticleList(Page page) {
        SelectBuilder selectBuilder = new SelectBuilder();
        BeanEntityDef articleBean = super.getEntityDef(ReaiArticleServiceImpl.TABLE_NAME);
//        BeanEntityDef channelBean = super.getEntityDef(ReaiChannelServiceImpl.TABLE_NAME);
        BeanEntityDef userBean = super.getEntityDef(ReaiUsersServiceImpl.TABLE_NAME);

        selectBuilder.bindFields("article", false, "seoInformation", "editorialSuggestions",
                "recommendations")
//            .bindFields("channel", "name", "type")
            .bindFields("user", "userId", "nickName", "avatar", "exp", "level");

        selectBuilder.from("article", articleBean)
            .leftJoinOn("user", userBean, "userId")
            .where()

//            .and("article.channel_id", ConditionType.EQUALS, "channelId")
            .and("article.user_id", ConditionType.EQUALS, "userId")
            .orderBy().desc("publish_date");
        //官方版块文章列表
        return super.listForBean(selectBuilder.build(), page, ReaiArticle::new);
    }

    /**
     * 分页查询朋友圈列表
     */
    public List<ReaiArticle> queryPageFriendArticleList(Page page, String userId) {
        ValueMap param = new ValueMap();
        param.put(ReaiArticle.USER_ID, userId);
        param.put(ReaiArticle.CHANNEL_ID, "friendCircle");
        SelectBuilder selectBuilder = new SelectBuilder(param);
        BeanEntityDef articleBean = super.getEntityDef(ReaiArticleServiceImpl.TABLE_NAME);
        BeanEntityDef userBean = super.getEntityDef(ReaiUsersServiceImpl.TABLE_NAME);

        selectBuilder.bindFields("article", false, "seoInformation", "editorialSuggestions",
                "recommendations")
            .bindFields("user", "userId", "nickName", "avatar", "exp", "level");

        selectBuilder.from("article", articleBean)
            .leftJoinOn("user", userBean, "userId")
            .where()
            .and("article.user_id", ConditionType.EQUALS, ReaiArticle.USER_ID)
            .and("article.channel_id", ConditionType.EQUALS, ReaiArticle.CHANNEL_ID)
            .orderBy().desc("publish_date");
        //官方版块文章列表
        return super.listForBean(selectBuilder.build(), page, ReaiArticle::new);
    }

}
