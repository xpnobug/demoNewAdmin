package com.newadmin.demoservice.mainPro.ltpro.entity;

import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.MessageTypeEnum;
import java.util.Date;
import java.util.Map;

/**
 * 消息实体
 */

public class MessageDO extends ValueMap {

    /**
     * ID
     */
    public static final String MESSAGE_ID = "messageId";

    /**
     * 标题
     */
    public static final String TITLE = "title";

    /**
     * 内容
     */
    public static final String CONTENT = "content";

    /**
     * 类型（1：系统消息）
     */
    public static final String TYPE = "type";

    /**
     * 创建人
     */
    public static final String CREATE_USER = "createUser";

    /**
     * 创建时间
     */
    public static final String CREATE_TIME = "createTime";

    public MessageDO() {
    }

    public MessageDO(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 ID
     *
     * @param id ID
     */
    public void setMessageId(String id) {
        super.setValue(MESSAGE_ID, id);
    }

    /**
     * 获取 ID
     *
     * @return ID
     */
    public String getMessageId() {
        return super.getValueAsString(MESSAGE_ID);
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
     * 设置 类型（1：系统消息）
     *
     * @param type 类型（1：系统消息）
     */
    public void setType(MessageTypeEnum type) {
        super.setValue(TYPE, type);
    }

    /**
     * 获取 类型（1：系统消息）
     *
     * @return 类型（1：系统消息）
     */
    public Integer getType() {
        return super.getValueAsInteger(TYPE);
    }

    /**
     * 设置 创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        super.setValue(CREATE_USER, createUser);
    }

    /**
     * 获取 创建人
     *
     * @return 创建人
     */
    public String getCreateUser() {
        return super.getValueAsString(CREATE_USER);
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