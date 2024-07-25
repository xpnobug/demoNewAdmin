
package com.newadmin.demoservice.config.captcha.behavior.autoconfigure.cache;

import cn.hutool.extra.spring.SpringUtil;
import com.anji.captcha.service.CaptchaCacheService;
import com.anji.captcha.service.impl.CaptchaCacheServiceMemImpl;
import com.anji.captcha.service.impl.CaptchaServiceFactory;
import com.newadmin.demoservice.config.captcha.model.StorageType;
import com.newadmin.demoservice.mainPro.ltpro.common.constant.PropertiesConstants;
import jakarta.annotation.PostConstruct;
import org.redisson.client.RedisClient;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ResolvableType;

/**
 * 行为验证码缓存自动配置
 *
 * @author Bull-BCLS
 * @author Charles7c
 * @since 1.1.0
 */
@AutoConfiguration
public class BehaviorCaptchaCacheAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(
        BehaviorCaptchaCacheAutoConfiguration.class);

    private BehaviorCaptchaCacheAutoConfiguration() {
    }

    /**
     * 自定义缓存实现-默认（内存）
     */
    @AutoConfiguration
    @ConditionalOnMissingBean(CaptchaCacheService.class)
    @ConditionalOnProperty(name = PropertiesConstants.CAPTCHA_BEHAVIOR
        + ".cache-type", havingValue = "default", matchIfMissing = true)
    public static class Default {

        @Bean
        public CaptchaCacheService captchaCacheService() {
            return new CaptchaCacheServiceMemImpl();
        }

        @PostConstruct
        public void postConstruct() {
            CaptchaServiceFactory.cacheService.put(StorageType.DEFAULT.name().toLowerCase(),
                captchaCacheService());
            log.debug(
                "[New Admin] - Auto Configuration 'Captcha-Behavior-Cache-Default' completed initialization.");
        }
    }

    /**
     * 自定义缓存实现-Redis
     */
    @AutoConfiguration(before = RedissonAutoConfiguration.class)
    @ConditionalOnClass(RedisClient.class)
    @ConditionalOnMissingBean(CaptchaCacheService.class)
    @ConditionalOnProperty(name = PropertiesConstants.CAPTCHA_BEHAVIOR
        + ".cache-type", havingValue = "redis")
    public static class Redis {

        @Bean
        public CaptchaCacheService captchaCacheService() {
            return new BehaviorCaptchaCacheServiceImpl();
        }

        @PostConstruct
        public void postConstruct() {
            CaptchaServiceFactory.cacheService.put(StorageType.REDIS.name().toLowerCase(),
                captchaCacheService());
            log.debug(
                "[New Admin] - Auto Configuration 'Captcha-Behavior-Cache-Redis' completed initialization.");
        }
    }

    /**
     * 自定义缓存实现
     */
    @AutoConfiguration
    @ConditionalOnProperty(name = PropertiesConstants.CAPTCHA_BEHAVIOR
        + ".cache-type", havingValue = "custom")
    public static class Custom {

        @Bean
        @ConditionalOnMissingBean
        public CaptchaCacheService captchaCacheService() {
            if (log.isErrorEnabled()) {
                log.error("Consider defining a bean of type '{}' in your configuration.",
                    ResolvableType
                        .forClass(CaptchaCacheService.class));
            }
            throw new NoSuchBeanDefinitionException(CaptchaCacheService.class);
        }

        @PostConstruct
        public void postConstruct() {
            CaptchaServiceFactory.cacheService.put(StorageType.CUSTOM.name().toLowerCase(),
                SpringUtil
                    .getBean(CaptchaCacheService.class));
            log.debug(
                "[New Admin] - Auto Configuration 'Captcha-Behavior-Cache-Custom' completed initialization.");
        }
    }
}
