package com.newadmin.demoservice.mainPro.ltpro.entity.model.resp;

import com.newadmin.democommon.service.ValueMap;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.MessageTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.Map;

/**
 * 消息信息
 */

@Schema(description = "消息信息")
public class MessageResp extends ValueMap {

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
     * 类型
     */
    public static final String TYPE = "type";

    /**
     * 是否已读
     */
    public static final String IS_READ = "isRead";

    /**
     * 读取时间
     */
    public static final String READ_TIME = "readTime";

    /**
     * 创建人
     */
    public static final String CREATE_USER = "createUser";

    /**
     * 创建人
     */
    public static final String CREATE_USER_STRING = "createUserString";

    /**
     * 创建时间
     */
    public static final String CREATE_TIME = "createTime";

    public MessageResp() {
    }

    public MessageResp(Map<String, Object> map) {
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
     * 设置 类型
     *
     * @param type 类型
     */
    public void setType(MessageTypeEnum type) {
        super.setValue(TYPE, type);
    }

    /**
     * 获取 类型
     *
     * @return 类型
     */
    public Integer getType() {
        return super.getValueAsInteger(TYPE);
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
     * 设置 创建人
     *
     * @param createUserString 创建人
     */
    public void setCreateUserString(String createUserString) {
        super.setValue(CREATE_USER_STRING, createUserString);
    }

    /**
     * 获取 创建人
     *
     * @return 创建人
     */
    public String getCreateUserString() {
        return super.getValueAsString(CREATE_USER_STRING);
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