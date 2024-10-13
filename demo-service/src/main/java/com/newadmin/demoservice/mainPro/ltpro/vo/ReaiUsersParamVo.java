package com.newadmin.demoservice.mainPro.ltpro.vo;

import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.GenderEnum;
import java.util.Date;
import java.util.Map;

public class ReaiUsersParamVo extends ValueMap {

    /**
     * 用户ID，主键，唯一标识用户
     */
    public static final String USER_ID = "userId";

    /**
     * 用户名，用户的用户名
     */
    public static final String USERNAME = "username";

    /***/
    public static final String NICK_NAME = "nickName";

    /**
     * 用户类型，用户类型（例如：普通用户、管理员）
     */
    public static final String USER_TYPE = "userType";

    /**
     * 状态，用户状态（例如：激活、禁用）
     */
    public static final String STATUS = "status";

    /**
     * 背景头像
     */
    public static final String USER_COVER = "userCover";

    /**
     * 用户头像
     */
    public static final String AVATAR = "avatar";

    /**
     * 用户等级
     */
    public static final String LEVEL = "level";

    /**
     * 经验值
     */
    public static final String EXP = "exp";

    /**
     * 邮箱
     */
    public static final String EMAIL = "email";

    /**
     * 性别
     */
    public static final String GENDER = "gender";

    /**
     * 注册时间
     */
    public static final String REGISTRATION_TIME = "registrationTime";

    /**
     * 是否关注
     */
    public static final String IS_FOLLOW = "isFollow";

    public ReaiUsersParamVo() {
    }

    public ReaiUsersParamVo(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 用户ID，主键，唯一标识用户
     *
     * @param userId 用户ID，主键，唯一标识用户
     */
    public void setUserId(String userId) {
        super.setValue(USER_ID, userId);
    }

    /**
     * 获取 用户ID，主键，唯一标识用户
     *
     * @return 用户ID，主键，唯一标识用户
     */
    public String getUserId() {
        return super.getValueAsString(USER_ID);
    }

    /**
     * 设置 用户名，用户的用户名
     *
     * @param username 用户名，用户的用户名
     */
    public void setUsername(String username) {
        super.setValue(USERNAME, username);
    }

    /**
     * 获取 用户名，用户的用户名
     *
     * @return 用户名，用户的用户名
     */
    public String getUsername() {
        return super.getValueAsString(USERNAME);
    }

    /**
     * 设置
     *
     * @param nickName
     */
    public void setNickName(String nickName) {
        super.setValue(NICK_NAME, nickName);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getNickName() {
        return super.getValueAsString(NICK_NAME);
    }

    /**
     * 设置 用户类型，用户类型（例如：普通用户、管理员）
     *
     * @param userType 用户类型，用户类型（例如：普通用户、管理员）
     */
    public void setUserType(String userType) {
        super.setValue(USER_TYPE, userType);
    }

    /**
     * 获取 用户类型，用户类型（例如：普通用户、管理员）
     *
     * @return 用户类型，用户类型（例如：普通用户、管理员）
     */
    public String getUserType() {
        return super.getValueAsString(USER_TYPE);
    }

    /**
     * 设置 状态，用户状态（例如：激活、禁用）
     *
     * @param status 状态，用户状态（例如：激活、禁用）
     */
    public void setStatus(String status) {
        super.setValue(STATUS, status);
    }

    /**
     * 获取 状态，用户状态（例如：激活、禁用）
     *
     * @return 状态，用户状态（例如：激活、禁用）
     */
    public String getStatus() {
        return super.getValueAsString(STATUS);
    }

    /**
     * 设置 背景头像
     *
     * @param userCover 背景头像
     */
    public void setUserCover(String userCover) {
        super.setValue(USER_COVER, userCover);
    }

    /**
     * 获取 背景头像
     *
     * @return 背景头像
     */
    public String getUserCover() {
        return super.getValueAsString(USER_COVER);
    }

    /**
     * 设置 用户头像
     *
     * @param avatar 用户头像
     */
    public void setAvatar(String avatar) {
        super.setValue(AVATAR, avatar);
    }

    /**
     * 获取 用户头像
     *
     * @return 用户头像
     */
    public String getAvatar() {
        return super.getValueAsString(AVATAR);
    }

    /**
     * 设置 用户等级
     *
     * @param level 用户等级
     */
    public void setLevel(Integer level) {
        super.setValue(LEVEL, level);
    }

    /**
     * 获取 用户等级
     *
     * @return 用户等级
     */
    public Integer getLevel() {
        return super.getValueAsInteger(LEVEL);
    }

    /**
     * 设置 经验值
     *
     * @param exp 经验值
     */
    public void setExp(String exp) {
        super.setValue(EXP, exp);
    }

    /**
     * 获取 经验值
     *
     * @return 经验值
     */
    public String getExp() {
        return super.getValueAsString(EXP);
    }

    /**
     * 设置 邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        super.setValue(EMAIL, email);
    }

    /**
     * 获取 邮箱
     *
     * @return 邮箱
     */
    public String getEmail() {
        return super.getValueAsString(EMAIL);
    }

    /**
     * 设置 性别
     *
     * @param gender 性别
     */
    public void setGender(GenderEnum gender) {
        super.setValue(GENDER, gender);
    }

    /**
     * 获取 性别
     *
     * @return 性别
     */
    public String getGender() {
        return super.getValueAsString(GENDER);
    }

    /**
     * 设置 注册时间
     *
     * @param registrationTime 注册时间
     */
    public void setRegistrationTime(Date registrationTime) {
        super.setValue(REGISTRATION_TIME, registrationTime);
    }

    /**
     * 获取 注册时间
     *
     * @return 注册时间
     */
    public Date getRegistrationTime() {
        return super.getValueAsDate(REGISTRATION_TIME);
    }

    /**
     * 设置 是否关注
     *
     * @param isFollow 是否关注
     */
    public void setIsFollow(boolean isFollow) {
        super.setValue(IS_FOLLOW, isFollow);
    }

    /**
     * 获取 是否关注
     *
     * @return 是否关注
     */
    public boolean getIsFollow() {
        return super.getValueAsBoolean(IS_FOLLOW);
    }
}
