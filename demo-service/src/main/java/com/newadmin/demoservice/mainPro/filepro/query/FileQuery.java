
package com.newadmin.demoservice.mainPro.filepro.query;

import com.newadmin.demoservice.mainPro.filepro.enums.FileTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 文件查询条件
 */
@Data
@Schema(description = "文件查询条件")
public class FileQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "图片")
    private String name;

    /**
     * 类型
     */
    @Schema(description = "类型", example = "2")
    private FileTypeEnum type;
}