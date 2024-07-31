package com.newadmin.demoservice.config.security.password.autoconfigure;

import com.newadmin.democore.constant.PropertiesConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 密码编解码配置属性
 *
 * @author Jasmine
 * @since 1.3.0
 */
@ConfigurationProperties(PropertiesConstants.SECURITY_PASSWORD)
public class PasswordEncoderProperties {

    /**
     * 是否启用密码编解码配置
     */
    private boolean enabled = false;

    /**
     * 默认启用的编码器 ID（默认：BCryptPasswordEncoder）
     */
    private String encodingId = "bcrypt";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEncodingId() {
        return encodingId;
    }

    public void setEncodingId(String encodingId) {
        this.encodingId = encodingId;
    }
}