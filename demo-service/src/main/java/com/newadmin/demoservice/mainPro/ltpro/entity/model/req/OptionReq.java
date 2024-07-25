

package com.newadmin.demoservice.mainPro.ltpro.entity.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import lombok.Data;

/**
 * 修改参数信息
 */
@Data
@Schema(description = "修改参数信息")
public class OptionReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID", example = "1")
    @NotNull(message = "ID不能为空")
    private Long id;

    /**
     * 键
     */
    @Schema(description = "键", example = "site_title")
    @NotBlank(message = "键不能为空")
    private String code;

    /**
     * 值
     */
    @Schema(description = "值", example = "===")
    @NotBlank(message = "值不能为空")
    private String value;
}