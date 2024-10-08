
package com.newadmin.democonfig.security.limiter.autoconfigure;

import com.newadmin.democonfig.security.limiter.core.DefaultRateLimiterNameGenerator;
import com.newadmin.democonfig.security.limiter.core.RateLimiterNameGenerator;
import com.newadmin.democonfig.constant.PropertiesConstants;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * 限流器自动配置
 */
@AutoConfiguration
@EnableConfigurationProperties(RateLimiterProperties.class)
@ComponentScan({"com.newadmin.demoservice.config.security.limiter.core"})
@ConditionalOnProperty(prefix = PropertiesConstants.SECURITY_LIMITER, name = PropertiesConstants.ENABLED, matchIfMissing = true)
public class RateLimiterAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(RateLimiterAutoConfiguration.class);

    /**
     * 限流器名称生成器
     */
    @Bean
    @ConditionalOnMissingBean
    public RateLimiterNameGenerator nameGenerator() {
        return new DefaultRateLimiterNameGenerator();
    }

    @PostConstruct
    public void postConstruct() {
        log.debug(
            "[New Admin] - 自动配置'Security-RateLimiter'已完成初始化。");
    }
}
