
package com.newadmin.demoservice.config.security.crypto.autoconfigure;

import com.newadmin.demoservice.mainPro.ltpro.common.constant.PropertiesConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 加/解密配置属性
 *
 * @author couei
 * @since 1.4.0
 */
@ConfigurationProperties(PropertiesConstants.SECURITY_CRYPTO)
public class CryptoProperties {

    /**
     * 是否启用加/解密配置
     */
    private boolean enabled = true;

    /**
     * 对称加密算法密钥
     */
    private String password;

    /**
     * 非对称加密算法公钥
     */
    private String publicKey;

    /**
     * 非对称加密算法私钥
     */
    private String privateKey;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}