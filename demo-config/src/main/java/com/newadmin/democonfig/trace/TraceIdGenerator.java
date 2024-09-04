package com.newadmin.democonfig.trace;

import com.yomahub.tlog.id.TLogIdGenerator;
import com.yomahub.tlog.id.snowflake.UniqueIdGenerator;

/**
 * TLog ID 生成器
 *
 * @author Jasmine
 * @author couei
 * @author Charles7c
 * @since 1.3.0
 */
public class TraceIdGenerator extends TLogIdGenerator {

    @Override
    public String generateTraceId() {
        return String.valueOf(UniqueIdGenerator.generateId());
    }
}