package com.newadmin.demoservice.config.satoken;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpInterface;
import com.newadmin.demoservice.config.core.utils.CheckUtils;
import com.newadmin.demoservice.config.satoken.autoconfigure.SaTokenExtensionProperties;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.dto.LoginUser;
import com.newadmin.demoservice.mainPro.ltpro.common.constant.StringConstants;
import com.newadmin.demoservice.mainPro.ltpro.helper.LoginHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Sa-Token 配置
 */
@Configuration
@RequiredArgsConstructor
public class SaTokenConfiguration {

    private final SaTokenExtensionProperties properties;
    private final LoginPasswordProperties loginPasswordProperties;

    /**
     * Sa-Token 权限认证配置
     */
    @Bean
    public StpInterface stpInterface() {
        return new SaTokenPermissionImpl();
    }

    /**
     * SaToken 拦截器配置
     */
    @Bean
    public SaInterceptor saInterceptor() {
        return new SaInterceptor(handle -> SaRouter.match(StringConstants.PATH_PATTERN)
            .notMatch(properties.getSecurity().getExcludes())
            .check(r -> {
                LoginUser loginUser = LoginHelper.getLoginUser();
                if (SaRouter.isMatchCurrURI(loginPasswordProperties.getExcludes())) {
                    return;
                }
                CheckUtils.throwIf(loginUser.isPasswordExpired(), "密码已过期，请修改密码");
            }));
    }
}
