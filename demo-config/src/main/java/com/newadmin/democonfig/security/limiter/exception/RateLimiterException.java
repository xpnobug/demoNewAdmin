package com.newadmin.democonfig.security.limiter.exception;

import com.newadmin.democonfig.exception.BaseException;

/**
 * 限流异常
 *
 * @author KAI
 * @since 2.2.0
 */
public class RateLimiterException extends BaseException {

    public RateLimiterException(String message) {
        super(message);
    }

    public RateLimiterException(String message, Throwable cause) {
        super(message, cause);
    }
}
