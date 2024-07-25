
package com.newadmin.demoservice.mainPro.ltpro.auth.model.req;

import cn.hutool.core.lang.RegexPool;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 手机号登录信息
 *
 * @author couei
 * @since 2023/10/26 22:37
 */
@Data
@Schema(description = "手机号登录信息")
public class PhoneLoginReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     */
    @Schema(description = "手机号", example = "13811111111")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = RegexPool.MOBILE, message = "手机号格式错误")
    private String phone;

    /**
     * 验证码
     */
    @Schema(description = "验证码", example = "8888")
    @NotBlank(message = "验证码不能为空")
    private String captcha;
}
