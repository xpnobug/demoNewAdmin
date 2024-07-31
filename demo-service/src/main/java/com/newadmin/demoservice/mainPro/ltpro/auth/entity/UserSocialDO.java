
package com.newadmin.demoservice.mainPro.ltpro.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.newadmin.democore.kduck.service.ValueMap;
import java.util.Date;
import java.util.Map;

/**
 * 用户社会化关联实体
 *
 * @author couei
 * @since 2023/10/11 22:10
 */

@TableName("reai_user_social")
public class UserSocialDO extends ValueMap {

    /**
     * 用户 ID
     */
    public static final String USER_ID = "userId";

    /**
     * 来源
     */
    public static final String SOURCE = "source";

    /**
     * 开放 ID
     */
    public static final String OPEN_ID = "openId";

    /**
     * 附加信息
     */
    public static final String META_JSON = "metaJson";

    /**
     * 最后登录时间
     */
    public static final String LAST_LOGIN_TIME = "lastLoginTime";

    /**
     * 创建时间
     */
    public static final String CREATE_TIME = "createTime";

    public UserSocialDO() {
    }

    public UserSocialDO(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 用户 ID
     *
     * @param userId 用户 ID
     */
    public void setUserId(String userId) {
        super.setValue(USER_ID, userId);
    }

    /**
     * 获取 用户 ID
     *
     * @return 用户 ID
     */
    public String getUserId() {
        return super.getValueAsString(USER_ID);
    }

    /**
     * 设置 来源
     *
     * @param source 来源
     */
    public void setSource(String source) {
        super.setValue(SOURCE, source);
    }

    /**
     * 获取 来源
     *
     * @return 来源
     */
    public String getSource() {
        return super.getValueAsString(SOURCE);
    }

    /**
     * 设置 开放 ID
     *
     * @param openId 开放 ID
     */
    public void setOpenId(String openId) {
        super.setValue(OPEN_ID, openId);
    }

    /**
     * 获取 开放 ID
     *
     * @return 开放 ID
     */
    public String getOpenId() {
        return super.getValueAsString(OPEN_ID);
    }

    /**
     * 设置 附加信息
     *
     * @param metaJson 附加信息
     */
    public void setMetaJson(String metaJson) {
        super.setValue(META_JSON, metaJson);
    }

    /**
     * 获取 附加信息
     *
     * @return 附加信息
     */
    public String getMetaJson() {
        return super.getValueAsString(META_JSON);
    }

    /**
     * 设置 最后登录时间
     *
     * @param lastLoginTime 最后登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        super.setValue(LAST_LOGIN_TIME, lastLoginTime);
    }

    /**
     * 获取 最后登录时间
     *
     * @return 最后登录时间
     */
    public Date getLastLoginTime() {
        return super.getValueAsDate(LAST_LOGIN_TIME);
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
}
