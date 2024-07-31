package com.newadmin.demoservice.mainPro.ltpro.entity.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 仪表盘-公告信息
 */
@Data
@Schema(description = "仪表盘-公告信息")
public class DashboardNoticeResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID", example = "1")
    private Long id;

    /**
     * 标题
     */
    @Schema(description = "标题", example = "这是公告标题")
    private String title;

    /**
     * 类型（取值于字典 notice_type）
     */
    @Schema(description = "类型（取值于字典 notice_type）", example = "1")
    private String type;
}