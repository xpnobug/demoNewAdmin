package com.newadmin.demoservice.mainPro.ltpro.entity;

import com.newadmin.democore.kduck.service.ValueMap;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author couei
 * @since 2024-05-25
 */

public class ReaiFollow extends ValueMap {

    /**
     * id
     */
    public static final String ID = "id";

    /**
     * 用户id
     */
    public static final String USER_ID = "userId";

    /**
     * 用户名称
     */
    public static final String USERNAME = "username";

    /**
     * 关注用户id
     */
    public static final String FOLLOW_USER_ID = "followUserId";

    /**
     * 加入的版块id
     */
    public static final String FOLLOW_CHANNEL_ID = "followChannelId";

    /**
     * 关注时间
     */
    public static final String FOLLOW_TIME = "followTime";

    public ReaiFollow() {
    }

    public ReaiFollow(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 id
     *
     * @param id id
     */
    public void setId(String id) {
        super.setValue(ID, id);
    }

    /**
     * 获取 id
     *
     * @return id
     */
    public String getId() {
        return super.getValueAsString(ID);
    }

    /**
     * 设置 用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        super.setValue(USER_ID, userId);
    }

    /**
     * 获取 用户id
     *
     * @return 用户id
     */
    public String getUserId() {
        return super.getValueAsString(USER_ID);
    }

    /**
     * 设置 用户名称
     *
     * @param username 用户名称
     */
    public void setUsername(String username) {
        super.setValue(USERNAME, username);
    }

    /**
     * 获取 用户名称
     *
     * @return 用户名称
     */
    public String getUsername() {
        return super.getValueAsString(USERNAME);
    }

    /**
     * 设置 关注用户id
     *
     * @param followUserId 关注用户id
     */
    public void setFollowUserId(String followUserId) {
        super.setValue(FOLLOW_USER_ID, followUserId);
    }

    /**
     * 获取 关注用户id
     *
     * @return 关注用户id
     */
    public String getFollowUserId() {
        return super.getValueAsString(FOLLOW_USER_ID);
    }

    /**
     * 设置 关注时间
     *
     * @param followTime 关注时间
     */
    public void setFollowTime(Date followTime) {
        super.setValue(FOLLOW_TIME, followTime);
    }

    /**
     * 获取 关注时间
     *
     * @return 关注时间
     */
    public Date getFollowTime() {
        return super.getValueAsDate(FOLLOW_TIME);
    }

    /**
     * 设置 加入的版块id
     *
     * @param followChannelId 加入的版块id
     */
    public void setFollowChannelId(String followChannelId) {
        super.setValue(FOLLOW_CHANNEL_ID, followChannelId);
    }

    /**
     * 获取 加入的版块id
     *
     * @return 加入的版块id
     */
    public String getFollowChannelId() {
        return super.getValueAsString(FOLLOW_CHANNEL_ID);
    }
}
