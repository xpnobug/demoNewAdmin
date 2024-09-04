package com.newadmin.demoservice.mainPro.ltpro.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 操作状态枚举
 *
 * @author couei
 * @author Charles7c
 * @since 2022/12/25 9:09
 */
@Getter
@RequiredArgsConstructor
public enum LogStatusEnum {

    /**
     * 成功
     */
    SUCCESS(1, "成功"),

    /**
     * 失败
     */
    FAILURE(2, "失败"),
    ;

    private final Integer value;
    private final String description;

    @Override
    public String toString() {
        return "%s: %s".formatted(this.value, this.description);
    }
}
