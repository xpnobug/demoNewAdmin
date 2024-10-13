package com.newadmin.demoservice.mainPro.filepro.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 文件上传响应信息
 */
@Data
@Builder
@Accessors(chain = true)
public class FileUploadResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文件 URL
     */
    @Schema(description = "文件 URL", example = "http://localhost:8000/file/65e87dc3fb377a6fb58bdece.jpg")
    private String url;

    /**
     * 文件缩略图
     */
    @Schema(description = "文件缩略图", example = "http://localhost:8000/file/65e87dc3fb377a6fb58bdece.jpg")
    private String thumbnail;

    /**
     * 文件id
     */
    @Schema(description = "文件id", example = "65e87dc3fb377a6fb58bdece")
    private String fileId;
}
