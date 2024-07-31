
package com.newadmin.demoservice.mainPro.ltpro.helper;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import com.newadmin.democore.util.ExceptionUtils;
import com.newadmin.democore.util.IpUtils;
import com.newadmin.democore.util.ServletUtils;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.dto.LoginUser;
import com.newadmin.demoservice.mainPro.ltpro.common.constant.CacheConstants;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 登录助手
 */
public class LoginHelper {

    private LoginHelper() {
    }

    /**
     * 用户登录并缓存用户信息
     *
     * @param users 登录用户信息
     * @return 令牌
     */
    public static String login(ReaiUsers users) {
        // 记录登录信息
        HttpServletRequest request = ServletUtils.getRequest();
        LoginUser loginUser = new LoginUser();
        loginUser.setIp(JakartaServletUtil.getClientIP(request));
        loginUser.setAddress(
            ExceptionUtils.exToNull(() -> IpUtils.getIpv4Address(loginUser.getIp())));
        loginUser.setBrowser(ServletUtils.getBrowser(request));
        loginUser.setLoginTime(LocalDateTime.now());
        loginUser.setOs(StrUtil.subBefore(ServletUtils.getOs(request), " or", false));
        // 登录并缓存用户信息
        StpUtil.login(users.getUserId());
        SaHolder.getStorage().set("LOGIN_USER", loginUser);
        String tokenValue = StpUtil.getTokenValue();
        loginUser.setToken(tokenValue);
        StpUtil.getTokenSession().set("LOGIN_USER", loginUser);
        return tokenValue;
    }

    /**
     * 获取登录用户信息
     *
     * @return 登录用户信息
     * @throws NotLoginException 未登录异常
     */
    public static LoginUser getLoginUser() throws NotLoginException {
        StpUtil.checkLogin();
        LoginUser loginUser = (LoginUser) SaHolder.getStorage().get(CacheConstants.LOGIN_USER_KEY);
        if (null != loginUser) {
            return loginUser;
        }
        SaSession tokenSession = StpUtil.getTokenSession();
        loginUser = (LoginUser) tokenSession.get(CacheConstants.LOGIN_USER_KEY);
        SaHolder.getStorage().set(CacheConstants.LOGIN_USER_KEY, loginUser);
        return loginUser;
    }

    /**
     * 根据 Token 获取登录用户信息
     *
     * @param token 用户 Token
     * @return 登录用户信息
     */
    public static LoginUser getLoginUser(String token) {
        SaSession tokenSession = StpUtil.getTokenSessionByToken(token);
        if (null == tokenSession) {
            return null;
        }
        return (LoginUser) tokenSession.get("token");
    }

    /**
     * 获取登录用户 ID
     *
     * @return 登录用户 ID
     */
    public static String getUserId() {
        return getLoginUser().getId();
    }

    /**
     * 获取登录用户名
     *
     * @return 登录用户名
     */
    public static String getUsername() {
        return getLoginUser().getUsername();
    }

}
