package com.newadmin.demoservice.mainPro.ltpro.entity;

import com.newadmin.democommon.service.ValueMap;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.GenderEnum;
import com.newadmin.demoservice.mainPro.ltpro.vo.Statistics;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author couei
 * @since 2024-05-14
 */

public class ReaiUsers extends ValueMap {

    /**
     * 用户ID，主键，唯一标识用户
     */
    public static final String USER_ID = "userId";

    /**
     * 用户名，用户的用户名
     */
    public static final String USERNAME = "username";

    /**
     * 密码，用户密码（加密存储）
     */
    public static final String PASSWORD = "password";

    /**
     * 电子邮箱，用户邮箱地址
     */
    public static final String EMAIL = "email";

    /**
     * 真实姓名，用户真实姓名
     */
    public static final String FULL_NAME = "fullName";

    /**
     * 手机号码，用户联系电话
     */
    public static final String PHONE_NUMBER = "phoneNumber";

    /**
     * 地址，用户地址
     */
    public static final String ADDRESS = "address";

    /**
     * 注册时间，用户注册时间
     */
    public static final String REGISTRATION_TIME = "registrationTime";

    /**
     * 最后登录时间，用户最后一次登录时间
     */
    public static final String LAST_LOGIN_TIME = "lastLoginTime";

    /**
     * 用户类型，用户类型（例如：普通用户、管理员）
     */
    public static final String USER_TYPE = "userType";

    /**
     * 状态，用户状态（例如：激活、禁用）
     */
    public static final String STATUS = "status";

    /**
     * 密码重置令牌，用户请求密码重置时生成的临时令牌
     */
    public static final String PASSWORD_RESET_TOKEN = "passwordResetToken";

    /**
     * 密码重置令牌过期时间，密码重置令牌的过期时间
     */
    public static final String PASSWORD_RESET_TOKEN_EXPIRES_AT = "passwordResetTokenExpiresAt";

    /**
     * 密码重置请求时间，记录用户请求密码重置的时间
     */
    public static final String PASSWORD_RESET_REQUESTED_AT = "passwordResetRequestedAt";

    /**
     * 账户锁定标志，标记用户账户是否被锁定
     */
    public static final String ACCOUNT_LOCKED = "accountLocked";

    /**
     * 账户锁定时间，记录用户账户被锁定的时间
     */
    public static final String ACCOUNT_LOCKED_AT = "accountLockedAt";

    /**
     * 账户锁定解锁时间，记录用户账户被解锁的时间
     */
    public static final String ACCOUNT_UNLOCKED_AT = "accountUnlockedAt";

    /**
     * 密码最后修改时间，记录用户密码最后一次修改的时间
     */
    public static final String PASSWORD_LAST_UPDATED_AT = "passwordLastUpdatedAt";

    /**
     * 密码过期时间，设定用户密码的有效期
     */
    public static final String PASSWORD_EXPIRES_AT = "passwordExpiresAt";

    /**
     * 最后登录失败时间，记录用户最后一次登录失败的时间
     */
    public static final String LAST_FAILED_LOGIN = "lastFailedLogin";

    /**
     * 登录失败次数，记录用户登录失败的次数
     */
    public static final String FAILED_LOGIN_ATTEMPTS = "failedLoginAttempts";

    /**
     * 双因素认证开启标志，标记用户是否开启双因素认证
     */
    public static final String TWO_FACTOR_AUTH_ENABLED = "twoFactorAuthEnabled";

    /**
     * 会话令牌，用于验证用户会话
     */
    public static final String SESSION_TOKEN = "sessionToken";

    /**
     * 会话令牌过期时间，用于限制会话时效性
     */
    public static final String SESSION_TOKEN_EXPIRES_AT = "sessionTokenExpiresAt";

    /**
     * 微信ID，用于存储用户在微信登录时的唯一标识
     */
    public static final String WECHAT_ID = "wechatId";

    /**
     * QQID，用于存储用户在QQ登录时的唯一标识
     */
    public static final String QQ_ID = "qqId";

    /**
     * 用户头像
     */
    public static final String USER_COVER = "userCover";

    /**
     * 用户等级
     */
    public static final String LEVEL = "level";

    /**
     * 经验值
     */
    public static final String EXP = "exp";

    /***/
    public static final String AVATAR = "avatar";

    /**
     * 设置不参与查询
     */
    public static final String IS_FOLLOW = "isFollow";

    /***/
    public static final String STATISTICS = "statistics";

    /**
     * 别名
     */
    public static final String NICK_NAME = "nickName";
    /**
     * 性别
     */
    public static final String GENDER = "gender";

    public ReaiUsers() {
    }

    public ReaiUsers(Map<String, Object> map) {
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
     * 设置 密码，用户密码（加密存储）
     *
     * @param password 密码，用户密码（加密存储）
     */
    public void setPassword(String password) {
        super.setValue(PASSWORD, password);
    }

    /**
     * 获取 密码，用户密码（加密存储）
     *
     * @return 密码，用户密码（加密存储）
     */
    public String getPassword() {
        return super.getValueAsString(PASSWORD);
    }

    /**
     * 设置 电子邮箱，用户邮箱地址
     *
     * @param email 电子邮箱，用户邮箱地址
     */
    public void setEmail(String email) {
        super.setValue(EMAIL, email);
    }

    /**
     * 获取 电子邮箱，用户邮箱地址
     *
     * @return 电子邮箱，用户邮箱地址
     */
    public String getEmail() {
        return super.getValueAsString(EMAIL);
    }

    /**
     * 设置 真实姓名，用户真实姓名
     *
     * @param fullName 真实姓名，用户真实姓名
     */
    public void setFullName(String fullName) {
        super.setValue(FULL_NAME, fullName);
    }

    /**
     * 获取 真实姓名，用户真实姓名
     *
     * @return 真实姓名，用户真实姓名
     */
    public String getFullName() {
        return super.getValueAsString(FULL_NAME);
    }

    /**
     * 设置 手机号码，用户联系电话
     *
     * @param phoneNumber 手机号码，用户联系电话
     */
    public void setPhoneNumber(String phoneNumber) {
        super.setValue(PHONE_NUMBER, phoneNumber);
    }

    /**
     * 获取 手机号码，用户联系电话
     *
     * @return 手机号码，用户联系电话
     */
    public String getPhoneNumber() {
        return super.getValueAsString(PHONE_NUMBER);
    }

    /**
     * 设置 地址，用户地址
     *
     * @param address 地址，用户地址
     */
    public void setAddress(String address) {
        super.setValue(ADDRESS, address);
    }

    /**
     * 获取 地址，用户地址
     *
     * @return 地址，用户地址
     */
    public String getAddress() {
        return super.getValueAsString(ADDRESS);
    }

    /**
     * 设置 注册时间，用户注册时间
     *
     * @param registrationTime 注册时间，用户注册时间
     */
    public void setRegistrationTime(Date registrationTime) {
        super.setValue(REGISTRATION_TIME, registrationTime);
    }

    /**
     * 获取 注册时间，用户注册时间
     *
     * @return 注册时间，用户注册时间
     */
    public Date getRegistrationTime() {
        return super.getValueAsDate(REGISTRATION_TIME);
    }

    /**
     * 设置 最后登录时间，用户最后一次登录时间
     *
     * @param lastLoginTime 最后登录时间，用户最后一次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        super.setValue(LAST_LOGIN_TIME, lastLoginTime);
    }

    /**
     * 获取 最后登录时间，用户最后一次登录时间
     *
     * @return 最后登录时间，用户最后一次登录时间
     */
    public Date getLastLoginTime() {
        return super.getValueAsDate(LAST_LOGIN_TIME);
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
     * 设置 密码重置令牌，用户请求密码重置时生成的临时令牌
     *
     * @param passwordResetToken 密码重置令牌，用户请求密码重置时生成的临时令牌
     */
    public void setPasswordResetToken(String passwordResetToken) {
        super.setValue(PASSWORD_RESET_TOKEN, passwordResetToken);
    }

    /**
     * 获取 密码重置令牌，用户请求密码重置时生成的临时令牌
     *
     * @return 密码重置令牌，用户请求密码重置时生成的临时令牌
     */
    public String getPasswordResetToken() {
        return super.getValueAsString(PASSWORD_RESET_TOKEN);
    }

    /**
     * 设置 密码重置令牌过期时间，密码重置令牌的过期时间
     *
     * @param passwordResetTokenExpiresAt 密码重置令牌过期时间，密码重置令牌的过期时间
     */
    public void setPasswordResetTokenExpiresAt(Date passwordResetTokenExpiresAt) {
        super.setValue(PASSWORD_RESET_TOKEN_EXPIRES_AT, passwordResetTokenExpiresAt);
    }

    /**
     * 获取 密码重置令牌过期时间，密码重置令牌的过期时间
     *
     * @return 密码重置令牌过期时间，密码重置令牌的过期时间
     */
    public Date getPasswordResetTokenExpiresAt() {
        return super.getValueAsDate(PASSWORD_RESET_TOKEN_EXPIRES_AT);
    }

    /**
     * 设置 密码重置请求时间，记录用户请求密码重置的时间
     *
     * @param passwordResetRequestedAt 密码重置请求时间，记录用户请求密码重置的时间
     */
    public void setPasswordResetRequestedAt(Date passwordResetRequestedAt) {
        super.setValue(PASSWORD_RESET_REQUESTED_AT, passwordResetRequestedAt);
    }

    /**
     * 获取 密码重置请求时间，记录用户请求密码重置的时间
     *
     * @return 密码重置请求时间，记录用户请求密码重置的时间
     */
    public Date getPasswordResetRequestedAt() {
        return super.getValueAsDate(PASSWORD_RESET_REQUESTED_AT);
    }

    /**
     * 设置 账户锁定标志，标记用户账户是否被锁定
     *
     * @param accountLocked 账户锁定标志，标记用户账户是否被锁定
     */
    public void setAccountLocked(Boolean accountLocked) {
        super.setValue(ACCOUNT_LOCKED, accountLocked);
    }

    /**
     * 获取 账户锁定标志，标记用户账户是否被锁定
     *
     * @return 账户锁定标志，标记用户账户是否被锁定
     */
    public Boolean getAccountLocked() {
        return super.getValueAsBoolean(ACCOUNT_LOCKED);
    }

    /**
     * 设置 账户锁定时间，记录用户账户被锁定的时间
     *
     * @param accountLockedAt 账户锁定时间，记录用户账户被锁定的时间
     */
    public void setAccountLockedAt(Date accountLockedAt) {
        super.setValue(ACCOUNT_LOCKED_AT, accountLockedAt);
    }

    /**
     * 获取 账户锁定时间，记录用户账户被锁定的时间
     *
     * @return 账户锁定时间，记录用户账户被锁定的时间
     */
    public Date getAccountLockedAt() {
        return super.getValueAsDate(ACCOUNT_LOCKED_AT);
    }

    /**
     * 设置 账户锁定解锁时间，记录用户账户被解锁的时间
     *
     * @param accountUnlockedAt 账户锁定解锁时间，记录用户账户被解锁的时间
     */
    public void setAccountUnlockedAt(Date accountUnlockedAt) {
        super.setValue(ACCOUNT_UNLOCKED_AT, accountUnlockedAt);
    }

    /**
     * 获取 账户锁定解锁时间，记录用户账户被解锁的时间
     *
     * @return 账户锁定解锁时间，记录用户账户被解锁的时间
     */
    public Date getAccountUnlockedAt() {
        return super.getValueAsDate(ACCOUNT_UNLOCKED_AT);
    }

    /**
     * 设置 密码最后修改时间，记录用户密码最后一次修改的时间
     *
     * @param passwordLastUpdatedAt 密码最后修改时间，记录用户密码最后一次修改的时间
     */
    public void setPasswordLastUpdatedAt(Date passwordLastUpdatedAt) {
        super.setValue(PASSWORD_LAST_UPDATED_AT, passwordLastUpdatedAt);
    }

    /**
     * 获取 密码最后修改时间，记录用户密码最后一次修改的时间
     *
     * @return 密码最后修改时间，记录用户密码最后一次修改的时间
     */
    public Date getPasswordLastUpdatedAt() {
        return super.getValueAsDate(PASSWORD_LAST_UPDATED_AT);
    }

    /**
     * 设置 密码过期时间，设定用户密码的有效期
     *
     * @param passwordExpiresAt 密码过期时间，设定用户密码的有效期
     */
    public void setPasswordExpiresAt(Date passwordExpiresAt) {
        super.setValue(PASSWORD_EXPIRES_AT, passwordExpiresAt);
    }

    /**
     * 获取 密码过期时间，设定用户密码的有效期
     *
     * @return 密码过期时间，设定用户密码的有效期
     */
    public Date getPasswordExpiresAt() {
        return super.getValueAsDate(PASSWORD_EXPIRES_AT);
    }

    /**
     * 设置 最后登录失败时间，记录用户最后一次登录失败的时间
     *
     * @param lastFailedLogin 最后登录失败时间，记录用户最后一次登录失败的时间
     */
    public void setLastFailedLogin(Date lastFailedLogin) {
        super.setValue(LAST_FAILED_LOGIN, lastFailedLogin);
    }

    /**
     * 获取 最后登录失败时间，记录用户最后一次登录失败的时间
     *
     * @return 最后登录失败时间，记录用户最后一次登录失败的时间
     */
    public Date getLastFailedLogin() {
        return super.getValueAsDate(LAST_FAILED_LOGIN);
    }

    /**
     * 设置 登录失败次数，记录用户登录失败的次数
     *
     * @param failedLoginAttempts 登录失败次数，记录用户登录失败的次数
     */
    public void setFailedLoginAttempts(Integer failedLoginAttempts) {
        super.setValue(FAILED_LOGIN_ATTEMPTS, failedLoginAttempts);
    }

    /**
     * 获取 登录失败次数，记录用户登录失败的次数
     *
     * @return 登录失败次数，记录用户登录失败的次数
     */
    public Integer getFailedLoginAttempts() {
        return super.getValueAsInteger(FAILED_LOGIN_ATTEMPTS);
    }

    /**
     * 设置 双因素认证开启标志，标记用户是否开启双因素认证
     *
     * @param twoFactorAuthEnabled 双因素认证开启标志，标记用户是否开启双因素认证
     */
    public void setTwoFactorAuthEnabled(Boolean twoFactorAuthEnabled) {
        super.setValue(TWO_FACTOR_AUTH_ENABLED, twoFactorAuthEnabled);
    }

    /**
     * 获取 双因素认证开启标志，标记用户是否开启双因素认证
     *
     * @return 双因素认证开启标志，标记用户是否开启双因素认证
     */
    public Boolean getTwoFactorAuthEnabled() {
        return super.getValueAsBoolean(TWO_FACTOR_AUTH_ENABLED);
    }

    /**
     * 设置 会话令牌，用于验证用户会话
     *
     * @param sessionToken 会话令牌，用于验证用户会话
     */
    public void setSessionToken(String sessionToken) {
        super.setValue(SESSION_TOKEN, sessionToken);
    }

    /**
     * 获取 会话令牌，用于验证用户会话
     *
     * @return 会话令牌，用于验证用户会话
     */
    public String getSessionToken() {
        return super.getValueAsString(SESSION_TOKEN);
    }

    /**
     * 设置 会话令牌过期时间，用于限制会话时效性
     *
     * @param sessionTokenExpiresAt 会话令牌过期时间，用于限制会话时效性
     */
    public void setSessionTokenExpiresAt(Date sessionTokenExpiresAt) {
        super.setValue(SESSION_TOKEN_EXPIRES_AT, sessionTokenExpiresAt);
    }

    /**
     * 获取 会话令牌过期时间，用于限制会话时效性
     *
     * @return 会话令牌过期时间，用于限制会话时效性
     */
    public Date getSessionTokenExpiresAt() {
        return super.getValueAsDate(SESSION_TOKEN_EXPIRES_AT);
    }

    /**
     * 设置 微信ID，用于存储用户在微信登录时的唯一标识
     *
     * @param wechatId 微信ID，用于存储用户在微信登录时的唯一标识
     */
    public void setWechatId(String wechatId) {
        super.setValue(WECHAT_ID, wechatId);
    }

    /**
     * 获取 微信ID，用于存储用户在微信登录时的唯一标识
     *
     * @return 微信ID，用于存储用户在微信登录时的唯一标识
     */
    public String getWechatId() {
        return super.getValueAsString(WECHAT_ID);
    }

    /**
     * 设置 QQID，用于存储用户在QQ登录时的唯一标识
     *
     * @param qqId QQID，用于存储用户在QQ登录时的唯一标识
     */
    public void setQqId(String qqId) {
        super.setValue(QQ_ID, qqId);
    }

    /**
     * 获取 QQID，用于存储用户在QQ登录时的唯一标识
     *
     * @return QQID，用于存储用户在QQ登录时的唯一标识
     */
    public String getQqId() {
        return super.getValueAsString(QQ_ID);
    }

    /**
     * 设置 用户头像
     *
     * @param userCover 用户头像
     */
    public void setUserCover(String userCover) {
        super.setValue(USER_COVER, userCover);
    }

    /**
     * 获取 用户头像
     *
     * @return 用户头像
     */
    public String getUserCover() {
        return super.getValueAsString(USER_COVER);
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
     * 设置
     *
     * @param avatar
     */
    public void setAvatar(String avatar) {
        super.setValue(AVATAR, avatar);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getAvatar() {
        return super.getValueAsString(AVATAR);
    }

    /**
     * 设置 设置不参与查询
     *
     * @param isFollow 设置不参与查询
     */
    public void setIsFollow(Boolean isFollow) {
        super.setValue(IS_FOLLOW, isFollow);
    }

    /**
     * 获取 设置不参与查询
     *
     * @return 设置不参与查询
     */
    public Boolean getIsFollow() {
        return super.getValueAsBoolean(IS_FOLLOW);
    }

    /**
     * 设置
     *
     * @param statistics
     */
    public void setStatistics(Statistics statistics) {
        super.setValue(STATISTICS, statistics);
    }

    /**
     * 设置 别名
     *
     * @param nickName 别名
     */
    public void setNickName(String nickName) {
        super.setValue(NICK_NAME, nickName);
    }

    /**
     * 获取 别名
     *
     * @return 别名
     */
    public String getNickName() {
        return super.getValueAsString(NICK_NAME);
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
}
