
package com.newadmin.demoservice.mainPro.ltpro.common.enums;

import com.newadmin.demoservice.mainPro.ltpro.common.constant.UiConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 消息类型枚举
 *
 * @author couei
 * @since 2023/11/2 20:08
 */
@Getter
@RequiredArgsConstructor
public enum MessageTypeEnum {

    /**
     * 安全消息
     */
    SECURITY(1, "安全消息", UiConstants.COLOR_PRIMARY),
    LOGGING(2, "登录消息", UiConstants.COLOR_PRIMARY),
    COMMENT(3, "评论消息", UiConstants.COLOR_PRIMARY),
    ;

    private final Integer value;
    private final String description;
    private final String color;
}
