package com.newadmin.demoservice.mainPro.ltpro.service;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 登录业务接口
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
    String accountLogin(String username, String password, HttpServletRequest request);

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
//    String socialLogin(AuthUser authUser);

    /**
     * 构建路由树
     *
     * @param userId 用户 ID
     * @return 路由树
     */
//    List<RouteResp> buildRouteTree(Long userId);
}
