package com.newadmin.demogenerator.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 表单类型枚举
 *
 * @author couei
 * @author Charles7c
 * @since 2023/8/6 10:49
 */
@Getter
@RequiredArgsConstructor
public enum FormTypeEnum {

    /**
     * 输入框
     */
    INPUT(1, "输入框"),

    /**
     * 数字输入框
     */
    INPUT_NUMBER(2, "数字输入框"),

    /**
     * 密码输入框
     */
    INPUT_PASSWORD(3, "密码输入框"),

    /**
     * 下拉框
     */
    SELECT(4, "下拉框"),

    /**
     * 单选框
     */
    RADIO(5, "单选框"),

    /**
     * 开关
     */
    SWITCH(6, "开关"),

    /**
     * 复选框
     */
    CHECK_BOX(7, "复选框"),

    /**
     * 文本域
     */
    TEXT_AREA(8, "文本域"),

    /**
     * 日期时间框
     */
    DATE_TIME(9, "日期时间框"),

    /**
     * 日期框
     */
    DATE(10, "日期框"),

    /**
     * 树形选择
     */
    TREE_SELECT(11, "树选择"),
    ;

    private final Integer value;
    private final String description;

    @Override
    public String toString() {
        return "%s: %s".formatted(this.value, this.description);
    }
}
