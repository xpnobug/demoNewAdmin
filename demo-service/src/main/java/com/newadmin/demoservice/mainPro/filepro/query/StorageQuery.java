package com.newadmin.demoservice.mainPro.filepro.query;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 存储查询条件
 */
@Data
@Schema(description = "存储查询条件")
public class StorageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 关键词
     */
    @Schema(description = "关键词", example = "本地存储")
    private String description;

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    private Integer status;
}