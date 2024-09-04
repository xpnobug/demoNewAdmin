
package com.newadmin.demoservice.mainPro.ltpro.common.enums;

import com.newadmin.demogenerator.constant.UiConstants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 启用/禁用状态枚举
 *
 * @author couei
 * @since 2022/12/29 22:38
 */
@Getter
@RequiredArgsConstructor
public enum DisEnableStatusEnum {

    /**
     * 启用
     */
    ENABLE(1, "启用", UiConstants.COLOR_SUCCESS),

    /**
     * 禁用
     */
    DISABLE(2, "禁用", UiConstants.COLOR_ERROR),
    ;

    private final Integer value;
    private final String description;
    private final String color;
}
