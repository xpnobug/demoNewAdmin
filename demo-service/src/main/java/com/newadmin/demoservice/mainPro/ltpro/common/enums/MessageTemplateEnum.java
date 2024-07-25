package com.newadmin.demoservice.mainPro.ltpro.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 消息模板枚举
 */
@Getter
@RequiredArgsConstructor
public enum MessageTemplateEnum {

    /**
     * 第三方登录
     */
    SOCIAL_REGISTER("欢迎注册 %s", "尊敬的 %s，欢迎注册使用，请及时配置您的密码。"),
    USER_LOGIN("欢迎登录 %s", "尊敬的 %s，欢迎登录使用。"),
    COMMENT_SEND("收到一条新的评论@ %s ", "%s");

    private final String title;
    private final String content;
}
