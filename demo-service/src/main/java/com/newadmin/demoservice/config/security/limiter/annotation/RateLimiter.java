package com.newadmin.demoservice.config.security.limiter.annotation;

import com.newadmin.demoservice.config.security.limiter.enums.LimitType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.redisson.api.RateIntervalUnit;

/**
 * 限流注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimiter {

    /**
     * 类型
     */
    LimitType type() default LimitType.DEFAULT;

    /**
     * 名称
     */
    String name() default "";

    /**
     * 键（支持 Spring EL 表达式）
     */
    String key() default "";

    /**
     * 速率（指定时间间隔产生的令牌数）
     */
    int rate() default Integer.MAX_VALUE;

    /**
     * 速率间隔（时间间隔）
     */
    int interval() default 0;

    /**
     * 速率间隔时间单位（默认：毫秒）
     */
    RateIntervalUnit unit() default RateIntervalUnit.MILLISECONDS;

    /**
     * 提示信息
     */
    String message() default "操作过于频繁，请稍后再试";
}