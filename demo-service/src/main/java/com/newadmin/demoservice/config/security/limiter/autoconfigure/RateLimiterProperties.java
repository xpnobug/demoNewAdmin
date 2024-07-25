
package com.newadmin.demoservice.config.security.limiter.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("new-admin.security.limiter")
public class RateLimiterProperties {

    private String keyPrefix = "RateLimiter";

    public RateLimiterProperties() {
    }

    public String getKeyPrefix() {
        return this.keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
