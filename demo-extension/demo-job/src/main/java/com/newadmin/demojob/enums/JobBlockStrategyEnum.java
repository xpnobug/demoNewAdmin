package com.newadmin.demojob.enums;

import com.newadmin.democonfig.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 任务阻塞策略枚举
 *
 * @author couei
 * @author Charles7c
 * @since 2024/7/11 22:28
 */
@Getter
@RequiredArgsConstructor
public enum JobBlockStrategyEnum implements BaseEnum<Integer> {

    /**
     * 丢弃
     */
    DISCARD(1, "丢弃"),

    /**
     * 覆盖
     */
    COVER(2, "覆盖"),

    /**
     * 并行
     */
    PARALLEL(3, "并行"),
    ;

    private final Integer value;
    private final String description;
}
