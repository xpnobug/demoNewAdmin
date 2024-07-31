package com.newadmin.demoservice.config.trace;

import com.yomahub.tlog.id.TLogIdGenerator;
import com.yomahub.tlog.id.snowflake.UniqueIdGenerator;

/**
 * TLog ID 生成器
 *
 * @author Jasmine
 * @author Charles7c
 * @since 1.3.0
 */
public class TraceIdGenerator extends TLogIdGenerator {

    @Override
    public String generateTraceId() {
        return String.valueOf(UniqueIdGenerator.generateId());
    }
}