
package com.newadmin.demoservice.mainPro.ltpro.common.constant;

import com.newadmin.democommon.core.constant.StringConstants;

/**
 * 配置属性相关常量
 *
 * @author couei
 * @since 1.1.1
 */
public class PropertiesConstants {

    /**
     * New Admin
     */
    public static final String NEW_ADMIN = "new-admin";

    /**
     * 启用配置
     */
    public static final String ENABLED = "enabled";

    /**
     * 安全配置
     */
    public static final String SECURITY = NEW_ADMIN + StringConstants.DOT + "security";

    /**
     * 密码编解码配置
     */
    public static final String SECURITY_PASSWORD = SECURITY + StringConstants.DOT + "password";

    /**
     * 加/解密配置
     */
    public static final String SECURITY_CRYPTO = SECURITY + StringConstants.DOT + "crypto";

    /**
     * 限流器配置
     */
    public static final String SECURITY_LIMITER = SECURITY + StringConstants.DOT + "limiter";

    /**
     * Web 配置
     */
    public static final String WEB = NEW_ADMIN + StringConstants.DOT + "web";

    /**
     * 跨域配置
     */
    public static final String WEB_CORS = WEB + StringConstants.DOT + "cors";

    /**
     * 链路配置
     */
    public static final String WEB_TRACE = WEB + StringConstants.DOT + "trace";

    /**
     * XSS 配置
     */
    public static final String WEB_XSS = WEB + StringConstants.DOT + "xss";

    /**
     * 国际化配置
     */
    public static final String WEB_I18N = WEB + StringConstants.DOT + "i18n";

    /**
     * 日志配置
     */
    public static final String LOG = NEW_ADMIN + StringConstants.DOT + "log";

    /**
     * 存储配置
     */
    public static final String STORAGE = NEW_ADMIN + StringConstants.DOT + "storage";

    /**
     * 本地存储配置
     */
    public static final String STORAGE_LOCAL = STORAGE + StringConstants.DOT + "local";

    /**
     * 验证码配置
     */
    public static final String CAPTCHA = NEW_ADMIN + StringConstants.DOT + "captcha";

    /**
     * 图形验证码配置
     */
    public static final String CAPTCHA_GRAPHIC = CAPTCHA + StringConstants.DOT + "graphic";

    /**
     * 行为验证码配置
     */
    public static final String CAPTCHA_BEHAVIOR = CAPTCHA + StringConstants.DOT + "behavior";

    /**
     * 消息配置
     */
    public static final String MESSAGING = NEW_ADMIN + StringConstants.DOT + "messaging";

    /**
     * WebSocket 配置
     */
    public static final String MESSAGING_WEBSOCKET = MESSAGING + StringConstants.DOT + "websocket";

    private PropertiesConstants() {
    }
}
