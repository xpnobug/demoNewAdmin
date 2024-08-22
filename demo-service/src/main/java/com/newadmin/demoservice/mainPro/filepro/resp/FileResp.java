package com.newadmin.demoservice.mainPro.filepro.resp;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.newadmin.demoservice.mainPro.filepro.enums.FileTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;

/**
 * 文件信息
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "文件详情信息")
public class FileResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "example")
    private String name;

    /**
     * 大小（字节）
     */
    @Schema(description = "大小（字节）", example = "4096")
    private Long size;

    /**
     * URL
     */
    @Schema(description = "URL", example = "https://examplebucket.oss-cn-hangzhou.aliyuncs.com/example/example.jpg")
    private String url;

    /**
     * 扩展名
     */
    @Schema(description = "扩展名", example = "jpg")
    private String extension;

    /**
     * 类型
     */
    @Schema(description = "类型", example = "2")
    private FileTypeEnum type;

    /**
     * 缩略图大小（字节)
     */
    @Schema(description = "缩略图大小（字节)", example = "1024")
    private Long thumbnailSize;

    /**
     * 缩略图 URL
     */
    @Schema(description = "缩略图 URL", example = "https://examplebucket.oss-cn-hangzhou.aliyuncs.com/example/example.jpg.min.jpg")
    private String thumbnailUrl;

    /**
     * 存储 ID
     */
    @Schema(description = "存储 ID", example = "1")
    private String storageId;

    /**
     * 存储名称
     */
    @Schema(description = "存储名称", example = "MinIO")
    private String storageName;
}