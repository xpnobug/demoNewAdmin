package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiArticle;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author couei
 * @since 2024-05-16
 */
public interface ReaiFriendArticleService {

    List<ReaiArticle> friendCircleList(Page page, String userId);

    boolean deleteArticle(String id);
}
