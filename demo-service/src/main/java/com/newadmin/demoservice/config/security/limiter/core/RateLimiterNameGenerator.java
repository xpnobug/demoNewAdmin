package com.newadmin.demoservice.config.security.limiter.core;

import java.lang.reflect.Method;

/**
 * 限流器名称生成器
 */
@FunctionalInterface
public interface RateLimiterNameGenerator {

    /**
     * 为给定方法及其参数生成速率限制器名称。
     *
     * @param target the target instance
     * @param method the method being called
     * @param args   the method parameters (with any var-args expanded)
     * @return a generated rate limiter name
     */
    String generate(Object target, Method method, Object... args);
}
