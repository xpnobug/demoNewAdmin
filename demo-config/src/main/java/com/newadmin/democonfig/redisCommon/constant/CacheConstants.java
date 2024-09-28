
package com.newadmin.democonfig.redisCommon.constant;

/**
 * 缓存相关常量
 *
 * @author couei
 * @since 2022/12/22 19:30
 */
public class CacheConstants {

    /**
     * 分隔符
     */
    public static final String DELIMITER = ":";

    /**
     * 登录用户键
     */
    public static final String LOGIN_USER_KEY = "LOGIN_USER";

    /**
     * 验证码键前缀
     */
    public static final String CAPTCHA_KEY_PREFIX = "CAPTCHA" + DELIMITER;

    /**
     * 用户缓存键前缀
     */
    public static final String USER_KEY_PREFIX = "USER" + DELIMITER;

    /**
     * 菜单缓存键前缀
     */
    public static final String MENU_KEY_PREFIX = "MENU" + DELIMITER;

    /**
     * 字典缓存键前缀
     */
    public static final String DICT_KEY_PREFIX = "DICT" + DELIMITER;

    /**
     * 参数缓存键前缀
     */
    public static final String OPTION_KEY_PREFIX = "OPTION" + DELIMITER;

    /**
     * 仪表盘缓存键前缀
     */
    public static final String DASHBOARD_KEY_PREFIX = "DASHBOARD" + DELIMITER;

    /**
     * 用户密码错误次数缓存键前缀
     */
    public static final String USER_PASSWORD_ERROR_KEY_PREFIX =
        USER_KEY_PREFIX + "PASSWORD_ERROR" + DELIMITER;

    /**
     * 数据导入临时会话key
     */
    public static final String DATA_IMPORT_KEY = "SYSTEM" + DELIMITER + "DATA_IMPORT" + DELIMITER;

    /**
     * 首页文章列表缓存键
     */
    public static final String HOME_ARTICLE_LIST_KEY = "home:article:list";

    /**
     *
     */

    private CacheConstants() {
    }
}
