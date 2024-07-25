
package com.newadmin.demoservice.config.properties;

import cn.hutool.extra.spring.SpringUtil;

/**
 * RSA 配置属性
 *
 * @author Zheng Jie（ELADMIN）
 * @author Charles7c
 * @since 2022/12/21 20:21
 */
public class RsaProperties {

    /**
     * 私钥
     */
    public static final String PRIVATE_KEY;
    public static final String PUBLIC_KEY;

    static {
        PRIVATE_KEY = SpringUtil.getProperty("new-admin.security.crypto.private-key");
        PUBLIC_KEY = SpringUtil.getProperty("new-admin.security.crypto.public-key");
    }

    private RsaProperties() {
    }
}
