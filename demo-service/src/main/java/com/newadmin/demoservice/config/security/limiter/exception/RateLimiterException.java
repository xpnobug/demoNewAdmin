package com.newadmin.demoservice.config.security.limiter.exception;

import com.newadmin.demoservice.config.core.exception.BaseException;

/**
 * 限流异常
 */
public class RateLimiterException extends BaseException {

    public RateLimiterException(String message) {
        super(message);
    }

    public RateLimiterException(String message, Throwable cause) {
        super(message, cause);
    }
}
