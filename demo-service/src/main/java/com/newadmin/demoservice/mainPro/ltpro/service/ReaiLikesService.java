package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiLikes;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 存储用户对文章点赞信息的表 服务类
 * </p>
 *
 * @author couei
 * @since 2024-06-21
 */
public interface ReaiLikesService {

    String giveALike(ReaiLikes likes, HttpServletRequest request);

    List<ReaiLikes> getLikesByIds(String uid, String articleId);

    List<ReaiLikes> getLikesByIds(String uid, String[] articleId);

    List<ReaiLikes> getUserInfoAndLikesList(String userId, String articleId);
}
