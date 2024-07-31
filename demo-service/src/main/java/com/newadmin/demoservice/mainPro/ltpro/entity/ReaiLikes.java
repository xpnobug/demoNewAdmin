package com.newadmin.demoservice.mainPro.ltpro.entity;

import com.newadmin.democore.kduck.service.ValueMap;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 存储用户对文章点赞信息的表
 * </p>
 *
 * @author couei
 * @since 2024-06-21
 */

public class ReaiLikes extends ValueMap {

    /***/
    public static final String LIKE_ID = "likeId";

    /**
     * 关联 reai_article 表的外键
     */
    public static final String ARTICLE_ID = "articleId";

    /**
     * 关联 Users 表的外键
     */
    public static final String USER_ID = "userId";

    /**
     * 点赞的时间戳
     */
    public static final String LIKED_AT = "likedAt";

    /**
     * 游客id
     */
    public static final String GUEST_ID = "guestId";

    public ReaiLikes() {
    }

    public ReaiLikes(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置
     *
     * @param likeId
     */
    public void setLikeId(String likeId) {
        super.setValue(LIKE_ID, likeId);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getLikeId() {
        return super.getValueAsString(LIKE_ID);
    }

    /**
     * 设置 关联 reai_article 表的外键
     *
     * @param articleId 关联 reai_article 表的外键
     */
    public void setArticleId(String articleId) {
        super.setValue(ARTICLE_ID, articleId);
    }

    /**
     * 获取 关联 reai_article 表的外键
     *
     * @return 关联 reai_article 表的外键
     */
    public String getArticleId() {
        return super.getValueAsString(ARTICLE_ID);
    }

    /**
     * 设置 关联 Users 表的外键
     *
     * @param userId 关联 Users 表的外键
     */
    public void setUserId(String userId) {
        super.setValue(USER_ID, userId);
    }

    /**
     * 获取 关联 Users 表的外键
     *
     * @return 关联 Users 表的外键
     */
    public String getUserId() {
        return super.getValueAsString(USER_ID);
    }

    /**
     * 设置 点赞的时间戳
     *
     * @param likedAt 点赞的时间戳
     */
    public void setLikedAt(Date likedAt) {
        super.setValue(LIKED_AT, likedAt);
    }

    /**
     * 获取 点赞的时间戳
     *
     * @return 点赞的时间戳
     */
    public Date getLikedAt() {
        return super.getValueAsDate(LIKED_AT);
    }

    /**
     * 设置 游客id
     *
     * @param guestId 游客id
     */
    public void setGuestId(String guestId) {
        super.setValue(GUEST_ID, guestId);
    }

    /**
     * 获取 游客id
     *
     * @return 游客id
     */
    public String getGuestId() {
        return super.getValueAsString(GUEST_ID);
    }
}
