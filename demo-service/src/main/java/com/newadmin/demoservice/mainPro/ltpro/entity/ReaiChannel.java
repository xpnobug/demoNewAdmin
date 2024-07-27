package com.newadmin.demoservice.mainPro.ltpro.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.newadmin.democommon.service.ValueMap;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author couei
 * @since 2024-07-27
 */

@TableName("reai_channel")
public class ReaiChannel extends ValueMap {

    /**
     * 唯一标识符
     */
    public static final String ID = "id";

    /**
     * 版块名称
     */
    public static final String NAME = "name";

    /**
     * 简要说明
     */
    public static final String SUMMARY = "summary";

    /**
     * 标题
     */
    public static final String TITLE = "title";

    /**
     * 类型
     */
    public static final String TYPE = "type";

    /**
     * 活动时间
     */
    public static final String ACTIVE_TIME = "activeTime";

    /**
     * 属性
     */
    public static final String ATTRIBUTE = "attribute";

    /**
     * 背景图 URL
     */
    public static final String BACKGROUND = "background";

    /**
     * 内容
     */
    public static final String CONTENT = "content";

    /**
     * 创建时间
     */
    public static final String CREATE_TIME = "createTime";

    /**
     * 创建者 ID
     */
    public static final String CREATOR = "creator";

    /**
     * 创建者姓名
     */
    public static final String CREATOR_NAME = "creatorName";

    /**
     * 精华数
     */
    public static final String ESSENCE_COUNT = "essenceCount";

    /**
     * 关注数
     */
    public static final String FOLLOW_COUNT = "followCount";

    /**
     * 帖子数
     */
    public static final String POST_COUNT = "postCount";

    /**
     * 帖子分类数
     */
    public static final String THREAD_CLASS_COUNT = "threadClassCount";

    /**
     * 版块管理员信息（JSON 类型）
     */
    public static final String FORUM_ADMIN = "forumAdmin";

    /**
     * 版块用户信息（JSON 类型）
     */
    public static final String FORUM_USER = "forumUser";

    /**
     * 独立版块标志
     */
    public static final String INDEPENDENT_FORUM = "independentForum";

    /**
     * 邀请权限
     */
    public static final String INVITE_POWER = "invitePower";

    /**
     * 是否匿名
     */
    public static final String IS_ANONYMOUS = "isAnonymous";

    /**
     * 是否会员
     */
    public static final String IS_MEMBER = "isMember";

    /**
     * 是否免付费
     */
    public static final String IS_NO_PAY = "isNoPay";

    /**
     * 是否官方
     */
    public static final String IS_OFFICIAL = "isOfficial";

    /**
     * 是否隐私
     */
    public static final String IS_PRIVACY = "isPrivacy";

    /**
     * 最后发布信息（JSON 类型）
     */
    public static final String LAST_PUBLISH = "lastPublish";

    /**
     * 版块 logo URL
     */
    public static final String LOGO = "logo";

    /**
     * 版块 logo 源 URL
     */
    public static final String LOGO_SRC = "logoSrc";

    /**
     * 成员数
     */
    public static final String MEMBER_COUNT = "memberCount";

    /**
     * 免付费组
     */
    public static final String NO_PAY_GROUP = "noPayGroup";

    /**
     * 是否可以支付加入
     */
    public static final String PAY_CAN_IN = "payCanIn";

    /**
     * 积分类型
     */
    public static final String PAY_FORUM_INTEGRAL_TYPE = "payForumIntegralType";

    /**
     * 加入时间
     */
    public static final String PAY_FORUM_JOIN_TIME = "payForumJoinTime";

    /**
     * 加入时间开始
     */
    public static final String PAY_FORUM_JOIN_TIME_BEGIN = "payForumJoinTimeBegin";

    /**
     * 加入时间结束
     */
    public static final String PAY_FORUM_JOIN_TIME_END = "payForumJoinTimeEnd";

    /**
     * 支付版块开放
     */
    public static final String PAY_FORUM_OPEN = "payForumOpen";

    /**
     * 支付版块价格数量
     */
    public static final String PAY_FORUM_PRICE_NUM = "payForumPriceNum";

    /**
     * 支付版块价格类型
     */
    public static final String PAY_FORUM_PRICE_TYPE = "payForumPriceType";

    /**
     * 订阅时间
     */
    public static final String PAY_FORUM_SUBSCRIPTION_TIME = "payForumSubscriptionTime";

    /**
     * 订阅类型
     */
    public static final String PAY_FORUM_SUBSCRIPTION_TYPE = "payForumSubscriptionType";

    /**
     * 支付版块类型
     */
    public static final String PAY_FORUM_TYPE = "payForumType";

    public ReaiChannel() {
    }

    public ReaiChannel(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 唯一标识符
     *
     * @param id 唯一标识符
     */
    public void setId(String id) {
        super.setValue(ID, id);
    }

    /**
     * 获取 唯一标识符
     *
     * @return 唯一标识符
     */
    public String getId() {
        return super.getValueAsString(ID);
    }

    /**
     * 设置 版块名称
     *
     * @param name 版块名称
     */
    public void setName(String name) {
        super.setValue(NAME, name);
    }

    /**
     * 获取 版块名称
     *
     * @return 版块名称
     */
    public String getName() {
        return super.getValueAsString(NAME);
    }

    /**
     * 设置 简要说明
     *
     * @param summary 简要说明
     */
    public void setSummary(String summary) {
        super.setValue(SUMMARY, summary);
    }

    /**
     * 获取 简要说明
     *
     * @return 简要说明
     */
    public String getSummary() {
        return super.getValueAsString(SUMMARY);
    }

    /**
     * 设置 标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        super.setValue(TITLE, title);
    }

    /**
     * 获取 标题
     *
     * @return 标题
     */
    public String getTitle() {
        return super.getValueAsString(TITLE);
    }

    /**
     * 设置 类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        super.setValue(TYPE, type);
    }

    /**
     * 获取 类型
     *
     * @return 类型
     */
    public String getType() {
        return super.getValueAsString(TYPE);
    }

    /**
     * 设置 活动时间
     *
     * @param activeTime 活动时间
     */
    public void setActiveTime(Date activeTime) {
        super.setValue(ACTIVE_TIME, activeTime);
    }

    /**
     * 获取 活动时间
     *
     * @return 活动时间
     */
    public Date getActiveTime() {
        return super.getValueAsDate(ACTIVE_TIME);
    }

    /**
     * 设置 属性
     *
     * @param attribute 属性
     */
    public void setAttribute(Integer attribute) {
        super.setValue(ATTRIBUTE, attribute);
    }

    /**
     * 获取 属性
     *
     * @return 属性
     */
    public Integer getAttribute() {
        return super.getValueAsInteger(ATTRIBUTE);
    }

    /**
     * 设置 背景图 URL
     *
     * @param background 背景图 URL
     */
    public void setBackground(String background) {
        super.setValue(BACKGROUND, background);
    }

    /**
     * 获取 背景图 URL
     *
     * @return 背景图 URL
     */
    public String getBackground() {
        return super.getValueAsString(BACKGROUND);
    }

    /**
     * 设置 内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        super.setValue(CONTENT, content);
    }

    /**
     * 获取 内容
     *
     * @return 内容
     */
    public String getContent() {
        return super.getValueAsString(CONTENT);
    }

    /**
     * 设置 创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        super.setValue(CREATE_TIME, createTime);
    }

    /**
     * 获取 创建时间
     *
     * @return 创建时间
     */
    public Date getCreateTime() {
        return super.getValueAsDate(CREATE_TIME);
    }

    /**
     * 设置 创建者 ID
     *
     * @param creator 创建者 ID
     */
    public void setCreator(Integer creator) {
        super.setValue(CREATOR, creator);
    }

    /**
     * 获取 创建者 ID
     *
     * @return 创建者 ID
     */
    public Integer getCreator() {
        return super.getValueAsInteger(CREATOR);
    }

    /**
     * 设置 创建者姓名
     *
     * @param creatorName 创建者姓名
     */
    public void setCreatorName(String creatorName) {
        super.setValue(CREATOR_NAME, creatorName);
    }

    /**
     * 获取 创建者姓名
     *
     * @return 创建者姓名
     */
    public String getCreatorName() {
        return super.getValueAsString(CREATOR_NAME);
    }

    /**
     * 设置 精华数
     *
     * @param essenceCount 精华数
     */
    public void setEssenceCount(Integer essenceCount) {
        super.setValue(ESSENCE_COUNT, essenceCount);
    }

    /**
     * 获取 精华数
     *
     * @return 精华数
     */
    public Integer getEssenceCount() {
        return super.getValueAsInteger(ESSENCE_COUNT);
    }

    /**
     * 设置 关注数
     *
     * @param followCount 关注数
     */
    public void setFollowCount(Integer followCount) {
        super.setValue(FOLLOW_COUNT, followCount);
    }

    /**
     * 获取 关注数
     *
     * @return 关注数
     */
    public Integer getFollowCount() {
        return super.getValueAsInteger(FOLLOW_COUNT);
    }

    /**
     * 设置 帖子数
     *
     * @param postCount 帖子数
     */
    public void setPostCount(Integer postCount) {
        super.setValue(POST_COUNT, postCount);
    }

    /**
     * 获取 帖子数
     *
     * @return 帖子数
     */
    public Integer getPostCount() {
        return super.getValueAsInteger(POST_COUNT);
    }

    /**
     * 设置 帖子分类数
     *
     * @param threadClassCount 帖子分类数
     */
    public void setThreadClassCount(Integer threadClassCount) {
        super.setValue(THREAD_CLASS_COUNT, threadClassCount);
    }

    /**
     * 获取 帖子分类数
     *
     * @return 帖子分类数
     */
    public Integer getThreadClassCount() {
        return super.getValueAsInteger(THREAD_CLASS_COUNT);
    }

    /**
     * 设置 版块管理员信息（JSON 类型）
     *
     * @param forumAdmin 版块管理员信息（JSON 类型）
     */
    public void setForumAdmin(String forumAdmin) {
        super.setValue(FORUM_ADMIN, forumAdmin);
    }

    /**
     * 获取 版块管理员信息（JSON 类型）
     *
     * @return 版块管理员信息（JSON 类型）
     */
    public String getForumAdmin() {
        return super.getValueAsString(FORUM_ADMIN);
    }

    /**
     * 设置 版块用户信息（JSON 类型）
     *
     * @param forumUser 版块用户信息（JSON 类型）
     */
    public void setForumUser(String forumUser) {
        super.setValue(FORUM_USER, forumUser);
    }

    /**
     * 获取 版块用户信息（JSON 类型）
     *
     * @return 版块用户信息（JSON 类型）
     */
    public String getForumUser() {
        return super.getValueAsString(FORUM_USER);
    }

    /**
     * 设置 独立版块标志
     *
     * @param independentForum 独立版块标志
     */
    public void setIndependentForum(Boolean independentForum) {
        super.setValue(INDEPENDENT_FORUM, independentForum);
    }

    /**
     * 获取 独立版块标志
     *
     * @return 独立版块标志
     */
    public Boolean getIndependentForum() {
        return super.getValueAsBoolean(INDEPENDENT_FORUM);
    }

    /**
     * 设置 邀请权限
     *
     * @param invitePower 邀请权限
     */
    public void setInvitePower(Integer invitePower) {
        super.setValue(INVITE_POWER, invitePower);
    }

    /**
     * 获取 邀请权限
     *
     * @return 邀请权限
     */
    public Integer getInvitePower() {
        return super.getValueAsInteger(INVITE_POWER);
    }

    /**
     * 设置 是否匿名
     *
     * @param isAnonymous 是否匿名
     */
    public void setIsAnonymous(Boolean isAnonymous) {
        super.setValue(IS_ANONYMOUS, isAnonymous);
    }

    /**
     * 获取 是否匿名
     *
     * @return 是否匿名
     */
    public Boolean getIsAnonymous() {
        return super.getValueAsBoolean(IS_ANONYMOUS);
    }

    /**
     * 设置 是否会员
     *
     * @param isMember 是否会员
     */
    public void setIsMember(Boolean isMember) {
        super.setValue(IS_MEMBER, isMember);
    }

    /**
     * 获取 是否会员
     *
     * @return 是否会员
     */
    public Boolean getIsMember() {
        return super.getValueAsBoolean(IS_MEMBER);
    }

    /**
     * 设置 是否免付费
     *
     * @param isNoPay 是否免付费
     */
    public void setIsNoPay(Boolean isNoPay) {
        super.setValue(IS_NO_PAY, isNoPay);
    }

    /**
     * 获取 是否免付费
     *
     * @return 是否免付费
     */
    public Boolean getIsNoPay() {
        return super.getValueAsBoolean(IS_NO_PAY);
    }

    /**
     * 设置 是否官方
     *
     * @param isOfficial 是否官方
     */
    public void setIsOfficial(Boolean isOfficial) {
        super.setValue(IS_OFFICIAL, isOfficial);
    }

    /**
     * 获取 是否官方
     *
     * @return 是否官方
     */
    public Boolean getIsOfficial() {
        return super.getValueAsBoolean(IS_OFFICIAL);
    }

    /**
     * 设置 是否隐私
     *
     * @param isPrivacy 是否隐私
     */
    public void setIsPrivacy(Boolean isPrivacy) {
        super.setValue(IS_PRIVACY, isPrivacy);
    }

    /**
     * 获取 是否隐私
     *
     * @return 是否隐私
     */
    public Boolean getIsPrivacy() {
        return super.getValueAsBoolean(IS_PRIVACY);
    }

    /**
     * 设置 最后发布信息（JSON 类型）
     *
     * @param lastPublish 最后发布信息（JSON 类型）
     */
    public void setLastPublish(String lastPublish) {
        super.setValue(LAST_PUBLISH, lastPublish);
    }

    /**
     * 获取 最后发布信息（JSON 类型）
     *
     * @return 最后发布信息（JSON 类型）
     */
    public String getLastPublish() {
        return super.getValueAsString(LAST_PUBLISH);
    }

    /**
     * 设置 版块 logo URL
     *
     * @param logo 版块 logo URL
     */
    public void setLogo(String logo) {
        super.setValue(LOGO, logo);
    }

    /**
     * 获取 版块 logo URL
     *
     * @return 版块 logo URL
     */
    public String getLogo() {
        return super.getValueAsString(LOGO);
    }

    /**
     * 设置 版块 logo 源 URL
     *
     * @param logoSrc 版块 logo 源 URL
     */
    public void setLogoSrc(String logoSrc) {
        super.setValue(LOGO_SRC, logoSrc);
    }

    /**
     * 获取 版块 logo 源 URL
     *
     * @return 版块 logo 源 URL
     */
    public String getLogoSrc() {
        return super.getValueAsString(LOGO_SRC);
    }

    /**
     * 设置 成员数
     *
     * @param memberCount 成员数
     */
    public void setMemberCount(Integer memberCount) {
        super.setValue(MEMBER_COUNT, memberCount);
    }

    /**
     * 获取 成员数
     *
     * @return 成员数
     */
    public Integer getMemberCount() {
        return super.getValueAsInteger(MEMBER_COUNT);
    }

    /**
     * 设置 免付费组
     *
     * @param noPayGroup 免付费组
     */
    public void setNoPayGroup(String noPayGroup) {
        super.setValue(NO_PAY_GROUP, noPayGroup);
    }

    /**
     * 获取 免付费组
     *
     * @return 免付费组
     */
    public String getNoPayGroup() {
        return super.getValueAsString(NO_PAY_GROUP);
    }

    /**
     * 设置 是否可以支付加入
     *
     * @param payCanIn 是否可以支付加入
     */
    public void setPayCanIn(Boolean payCanIn) {
        super.setValue(PAY_CAN_IN, payCanIn);
    }

    /**
     * 获取 是否可以支付加入
     *
     * @return 是否可以支付加入
     */
    public Boolean getPayCanIn() {
        return super.getValueAsBoolean(PAY_CAN_IN);
    }

    /**
     * 设置 积分类型
     *
     * @param payForumIntegralType 积分类型
     */
    public void setPayForumIntegralType(Integer payForumIntegralType) {
        super.setValue(PAY_FORUM_INTEGRAL_TYPE, payForumIntegralType);
    }

    /**
     * 获取 积分类型
     *
     * @return 积分类型
     */
    public Integer getPayForumIntegralType() {
        return super.getValueAsInteger(PAY_FORUM_INTEGRAL_TYPE);
    }

    /**
     * 设置 加入时间
     *
     * @param payForumJoinTime 加入时间
     */
    public void setPayForumJoinTime(Integer payForumJoinTime) {
        super.setValue(PAY_FORUM_JOIN_TIME, payForumJoinTime);
    }

    /**
     * 获取 加入时间
     *
     * @return 加入时间
     */
    public Integer getPayForumJoinTime() {
        return super.getValueAsInteger(PAY_FORUM_JOIN_TIME);
    }

    /**
     * 设置 加入时间开始
     *
     * @param payForumJoinTimeBegin 加入时间开始
     */
    public void setPayForumJoinTimeBegin(Date payForumJoinTimeBegin) {
        super.setValue(PAY_FORUM_JOIN_TIME_BEGIN, payForumJoinTimeBegin);
    }

    /**
     * 获取 加入时间开始
     *
     * @return 加入时间开始
     */
    public Date getPayForumJoinTimeBegin() {
        return super.getValueAsDate(PAY_FORUM_JOIN_TIME_BEGIN);
    }

    /**
     * 设置 加入时间结束
     *
     * @param payForumJoinTimeEnd 加入时间结束
     */
    public void setPayForumJoinTimeEnd(Date payForumJoinTimeEnd) {
        super.setValue(PAY_FORUM_JOIN_TIME_END, payForumJoinTimeEnd);
    }

    /**
     * 获取 加入时间结束
     *
     * @return 加入时间结束
     */
    public Date getPayForumJoinTimeEnd() {
        return super.getValueAsDate(PAY_FORUM_JOIN_TIME_END);
    }

    /**
     * 设置 支付版块开放
     *
     * @param payForumOpen 支付版块开放
     */
    public void setPayForumOpen(Boolean payForumOpen) {
        super.setValue(PAY_FORUM_OPEN, payForumOpen);
    }

    /**
     * 获取 支付版块开放
     *
     * @return 支付版块开放
     */
    public Boolean getPayForumOpen() {
        return super.getValueAsBoolean(PAY_FORUM_OPEN);
    }

    /**
     * 设置 支付版块价格数量
     *
     * @param payForumPriceNum 支付版块价格数量
     */
    public void setPayForumPriceNum(BigDecimal payForumPriceNum) {
        super.setValue(PAY_FORUM_PRICE_NUM, payForumPriceNum);
    }

    /**
     * 获取 支付版块价格数量
     *
     * @return 支付版块价格数量
     */
    public Integer getPayForumPriceNum() {
        return super.getValueAsInteger(PAY_FORUM_PRICE_NUM);
    }

    /**
     * 设置 支付版块价格类型
     *
     * @param payForumPriceType 支付版块价格类型
     */
    public void setPayForumPriceType(Integer payForumPriceType) {
        super.setValue(PAY_FORUM_PRICE_TYPE, payForumPriceType);
    }

    /**
     * 获取 支付版块价格类型
     *
     * @return 支付版块价格类型
     */
    public Integer getPayForumPriceType() {
        return super.getValueAsInteger(PAY_FORUM_PRICE_TYPE);
    }

    /**
     * 设置 订阅时间
     *
     * @param payForumSubscriptionTime 订阅时间
     */
    public void setPayForumSubscriptionTime(Integer payForumSubscriptionTime) {
        super.setValue(PAY_FORUM_SUBSCRIPTION_TIME, payForumSubscriptionTime);
    }

    /**
     * 获取 订阅时间
     *
     * @return 订阅时间
     */
    public Integer getPayForumSubscriptionTime() {
        return super.getValueAsInteger(PAY_FORUM_SUBSCRIPTION_TIME);
    }

    /**
     * 设置 订阅类型
     *
     * @param payForumSubscriptionType 订阅类型
     */
    public void setPayForumSubscriptionType(Integer payForumSubscriptionType) {
        super.setValue(PAY_FORUM_SUBSCRIPTION_TYPE, payForumSubscriptionType);
    }

    /**
     * 获取 订阅类型
     *
     * @return 订阅类型
     */
    public Integer getPayForumSubscriptionType() {
        return super.getValueAsInteger(PAY_FORUM_SUBSCRIPTION_TYPE);
    }

    /**
     * 设置 支付版块类型
     *
     * @param payForumType 支付版块类型
     */
    public void setPayForumType(Integer payForumType) {
        super.setValue(PAY_FORUM_TYPE, payForumType);
    }

    /**
     * 获取 支付版块类型
     *
     * @return 支付版块类型
     */
    public Integer getPayForumType() {
        return super.getValueAsInteger(PAY_FORUM_TYPE);
    }
}
