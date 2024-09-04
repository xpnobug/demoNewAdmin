package com.newadmin.demojob.enums;

import com.newadmin.democonfig.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 任务触发类型枚举
 *
 * @author couei
 * @author Charles7c
 * @since 2024/7/11 22:28
 */
@Getter
@RequiredArgsConstructor
public enum JobTriggerTypeEnum implements BaseEnum<Integer> {

    /**
     * 固定时间
     */
    FIXED_TIME(2, "固定时间"),

    /**
     * CRON
     */
    CRON(3, "CRON");

    private final Integer value;
    private final String description;
}
