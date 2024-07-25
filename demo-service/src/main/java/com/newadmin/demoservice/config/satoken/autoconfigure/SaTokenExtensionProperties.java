package com.newadmin.demoservice.config.satoken.autoconfigure;

import com.newadmin.demoservice.config.satoken.autoconfigure.dao.SaTokenDaoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * SaToken 扩展配置属性
 */
@ConfigurationProperties("sa-token.extension")
public class SaTokenExtensionProperties {

    /**
     * 是否启用扩展
     */
    private boolean enabled = false;

    /**
     * 启用 JWT
     */
    private boolean enableJwt = false;

    /**
     * 持久层配置
     */
    @NestedConfigurationProperty
    private SaTokenDaoProperties dao;

    /**
     * 安全配置
     */
    @NestedConfigurationProperty
    private SaTokenSecurityProperties security;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnableJwt() {
        return enableJwt;
    }

    public void setEnableJwt(boolean enableJwt) {
        this.enableJwt = enableJwt;
    }

    public SaTokenDaoProperties getDao() {
        return dao;
    }

    public void setDao(SaTokenDaoProperties dao) {
        this.dao = dao;
    }

    public SaTokenSecurityProperties getSecurity() {
        return security;
    }

    public void setSecurity(SaTokenSecurityProperties security) {
        this.security = security;
    }
}
