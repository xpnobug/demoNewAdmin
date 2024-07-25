package com.newadmin.demoservice.mainPro.ltpro.entity;

import com.newadmin.democommon.service.ValueMap;
import java.util.Date;
import java.util.Map;

/**
 * 消息和用户关联实体
 */

public class MessageUserDO extends ValueMap {

    /**
     * id
     */
    public static final String ID = "id";
    /**
     * 消息 ID
     */
    public static final String MESSAGE_ID = "messageId";

    /**
     * 用户 ID
     */
    public static final String USER_ID = "userId";

    /**
     * 是否已读
     */
    public static final String IS_READ = "isRead";

    /**
     * 读取时间
     */
    public static final String READ_TIME = "readTime";

    public MessageUserDO() {
    }

    public MessageUserDO(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 消息 ID
     *
     * @param messageId 消息 ID
     */
    public void setMessageId(String messageId) {
        super.setValue(MESSAGE_ID, messageId);
    }

    /**
     * 获取 消息 ID
     *
     * @return 消息 ID
     */
    public String getMessageId() {
        return super.getValueAsString(MESSAGE_ID);
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
     * 设置 是否已读
     *
     * @param isRead 是否已读
     */
    public void setIsRead(Boolean isRead) {
        super.setValue(IS_READ, isRead);
    }

    /**
     * 获取 是否已读
     *
     * @return 是否已读
     */
    public Boolean getIsRead() {
        return super.getValueAsBoolean(IS_READ);
    }

    /**
     * 设置 读取时间
     *
     * @param readTime 读取时间
     */
    public void setReadTime(Date readTime) {
        super.setValue(READ_TIME, readTime);
    }

    /**
     * 获取 读取时间
     *
     * @return 读取时间
     */
    public Date getReadTime() {
        return super.getValueAsDate(READ_TIME);
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
}