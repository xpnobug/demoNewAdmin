package com.newadmin.demoservice.mainPro.filepro.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import java.io.Serial;
import lombok.Data;

/**
 * 修改文件信息
 */
@Data
@Schema(description = "修改文件信息")
public class FileReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "test123")
    @NotBlank(message = "文件名称不能为空")
    private String name;
}