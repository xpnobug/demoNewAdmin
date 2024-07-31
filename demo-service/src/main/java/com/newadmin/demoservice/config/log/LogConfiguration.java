package com.newadmin.demoservice.config.log;

import com.newadmin.demolog.log.core.dao.LogDao;
import com.newadmin.demolog.log.httptrace.autoconfigure.ConditionalOnEnabledLog;
import com.newadmin.demoservice.config.trace.TraceProperties;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志配置
 *
 * @author Charles7c
 * @since 2022/12/24 23:15
 */
@Configuration
@ConditionalOnEnabledLog
public class LogConfiguration {

    /**
     * 日志持久层接口本地实现类
     */
    @Bean
    public LogDao logDao(ReaiUsersService userService, TraceProperties traceProperties) {
        return new LogDaoLocalImpl(userService, traceProperties);
    }
}
