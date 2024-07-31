package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiArticle;
import com.newadmin.demoservice.mainPro.ltpro.vo.ReaiArticleList;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author couei
 * @since 2024-05-16
 */
public interface ReaiArticleService {

    List<ReaiArticle> articleList(String userId);

    /**
     * 获取所有模块文章列表
     *
     * @return
     */
    List<ReaiArticleList> getArticleList();


    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    ReaiArticle addArticle(ReaiArticle article);

    /**
     * 单个文章
     *
     * @param id
     * @return
     */
    ReaiArticle getArticleInfo(Serializable id);

    /**
     * 根据用户id获取文章列表
     *
     * @param id
     * @return
     */
    List<ReaiArticle> getArticleByUserId(String id);

    List<ReaiArticle> selectAllList(Page page);

    /**
     * 更新文章
     *
     * @param article
     * @return
     */
    ReaiArticle updateArticle(ReaiArticle article);

    List<ReaiArticle> friendCircleList(Page page, String userId);

    List<ReaiArticle> friendArticleList(Page page, String userId);

    JsonObject deleteArticle(String id);
}
