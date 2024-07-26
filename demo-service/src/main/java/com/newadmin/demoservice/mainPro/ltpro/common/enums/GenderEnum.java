package com.newadmin.demoservice.mainPro.ltpro.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 性别枚举
 */
@Getter
@RequiredArgsConstructor
public enum GenderEnum {

    /**
     * 未知
     */
    UNKNOWN(0, "未知"),

    /**
     * 男
     */
    MALE(1, "男"),

    /**
     * 女
     */
    FEMALE(2, "女"),
    ;

    private final Integer value;
    private final String description;
}
