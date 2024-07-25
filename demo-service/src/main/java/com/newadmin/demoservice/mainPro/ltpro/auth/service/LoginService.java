package com.newadmin.demoservice.mainPro.ltpro.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import me.zhyd.oauth.model.AuthUser;

/**
 * 登录业务接口
 *
 * @author couei
 * @since 2022/12/21 21:48
 */
public interface LoginService {

    /**
     * 账号登录
     *
     * @param username 用户名
     * @param password 密码
     * @param request  请求对象
     * @return 令牌
     */
    String accountLogin(String username, String password, HttpServletRequest request)
        throws Exception;

    /**
     * 手机号登录
     *
     * @param phone 手机号
     * @return 令牌
     */
    String phoneLogin(String phone);

    /**
     * 邮箱登录
     *
     * @param email 邮箱
     * @return 令牌
     */
    String emailLogin(String email);

    /**
     * 三方账号登录
     *
     * @param authUser 三方账号信息
     * @return 令牌
     */
    String socialLogin(AuthUser authUser);

}
