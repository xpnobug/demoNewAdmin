package com.newadmin.demoservice.mainPro.ltpro.controller.auth;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.newadmin.democonfig.redisCommon.util.RedisUtils;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.util.validate.ValidationUtils;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.req.AccountLoginReq;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.req.EmailLoginReq;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.req.PhoneLoginReq;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.resp.LoginResp;
import com.newadmin.demoservice.mainPro.ltpro.auth.service.LoginService;
import com.newadmin.demoservice.mainPro.ltpro.common.constant.CacheConstants;
import com.newadmin.demoservice.mainPro.nas.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@Tag(name = "认证 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private static final String CAPTCHA_EXPIRED = "验证码已失效";
    private static final String CAPTCHA_ERROR = "验证码错误";
    private final LoginService loginService;
    private final UserService userService;

    /**
     * 账户登录 注解 @SaIgnore 表示该方法不需要认证 注解 @Operation 用于描述接口的操作信息，这里定义了接口的摘要和描述 注解 @PostMapping 指定该方法处理
     * POST 请求，路径为 /account
     *
     * @param loginReq 登录请求
     * @param request  请求
     * @return 登录响应
     */
    @SaIgnore
    @Operation(summary = "账号登录", description = "根据账号和密码进行登录认证")
    @PostMapping("/account")
    public JsonObject accoutLogin(@Validated @RequestBody AccountLoginReq loginReq,
        HttpServletRequest request) throws Exception {
        // 获取验证码，组合出缓存中存储验证码的 key
        String captchaKey = CacheConstants.CAPTCHA_KEY_PREFIX + loginReq.getUuid();
        // 从 Redis 中获取验证码
        String captcha = RedisUtils.get(captchaKey);
        // 验证验证码是否存在，不存在则抛出验证码过期异常
        ValidationUtils.throwIfBlank(captcha, CAPTCHA_EXPIRED);
        // 从 Redis 中删除验证码
        RedisUtils.delete(captchaKey);
        // 验证请求中的验证码是否正确，不正确则抛出验证码错误异常
        ValidationUtils.throwIfNotEqualIgnoreCase(captcha, loginReq.getCaptcha(), CAPTCHA_ERROR);

        // 用户登录
        // 使用 RSA 私钥解密用户输入的密码
//        String rawPassword = ExceptionUtils.exToNull(() -> SecureUtils.decryptByRsaPrivateKey(loginReq.getPassword()));
        // 密码解密提示
//        ValidationUtils.throwIfBlank(rawPassword, "密码解密失败");
        // 调用登录服务进行账号登录，并获取生成的令牌
        String token = loginService.accountLogin(loginReq.getUsername(), loginReq.getPassword(),
            request);
        // 返回包含令牌的响应对象
        return new JsonObject(LoginResp.builder().token(token).build());
    }

    @SaIgnore
    @Operation(summary = "手机号登录", description = "根据手机号和验证码进行登录认证")
    @PostMapping("/phone")
    public JsonObject phoneLogin(@Validated @RequestBody PhoneLoginReq loginReq) {
        // 获取请求中的手机号
        String phone = loginReq.getPhone();
        // 构建验证码缓存的键
        String captchaKey = CacheConstants.CAPTCHA_KEY_PREFIX + phone;
        // 从缓存中获取验证码
        String captcha = RedisUtils.get(captchaKey);
        // 验证验证码是否存在（过期）
        ValidationUtils.throwIfBlank(captcha, CAPTCHA_EXPIRED);
        // 验证请求中的验证码和缓存中的验证码是否一致
        ValidationUtils.throwIfNotEqualIgnoreCase(loginReq.getCaptcha(), captcha, CAPTCHA_ERROR);
        // 验证成功后删除缓存中的验证码
        RedisUtils.delete(captchaKey);
        // 使用手机号进行登录，并获取登录后的token
        String token = loginService.phoneLogin(phone);
        // 返回登录响应，包含生成的token
        return new JsonObject(LoginResp.builder().token(token).build());
    }

    @SaIgnore
    @Operation(summary = "邮箱登录", description = "根据邮箱和验证码进行登录认证")
    @PostMapping("/email")
    public JsonObject emailLogin(@Validated @RequestBody EmailLoginReq loginReq) {
        // 获取请求中的邮箱地址
        String email = loginReq.getEmail();
        // 构建验证码缓存的键
        String captchaKey = CacheConstants.CAPTCHA_KEY_PREFIX + email;
        // 从缓存中获取验证码
        String captcha = RedisUtils.get(captchaKey);
        // 验证验证码是否存在（过期）
        ValidationUtils.throwIfBlank(captcha, CAPTCHA_EXPIRED);
        // 验证请求中的验证码和缓存中的验证码是否一致
        ValidationUtils.throwIfNotEqualIgnoreCase(loginReq.getCaptcha(), captcha, CAPTCHA_ERROR);
        // 验证成功后删除缓存中的验证码
        RedisUtils.delete(captchaKey);
        // 使用邮箱进行登录，并获取登录后的token
        String token = loginService.emailLogin(email);
        // 返回登录响应，包含生成的token
        return new JsonObject(LoginResp.builder().token(token).build());
    }

    @Operation(summary = "用户退出", description = "注销用户的当前登录")
    @Parameter(name = "Authorization", description = "令牌", required = true, example = "Bearer xxxx-xxxx-xxxx-xxxx", in = ParameterIn.HEADER)
    @PostMapping("/logout")
    public JsonObject logout() {
        Object loginId = StpUtil.getLoginId(-1L);
        StpUtil.logout();
        return new JsonObject(loginId);
    }

}
