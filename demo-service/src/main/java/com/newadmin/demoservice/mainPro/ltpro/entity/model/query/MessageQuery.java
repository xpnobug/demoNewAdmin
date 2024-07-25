

package com.newadmin.demoservice.mainPro.ltpro.entity.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 消息查询条件
 */
@Data
@Schema(description = "消息查询条件")
public class MessageQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "MESSAGE_ID", example = "1")
    private String messageId;

    /**
     * 标题
     */
    @Schema(description = "标题", example = "欢迎注册 xxx")
    private String title;

    /**
     * 类型
     */
    @Schema(description = "类型（1：系统消息）", example = "1")
    private Integer type;

    /**
     * 是否已读
     */
    @Schema(description = "是否已读", example = "true")
    private Boolean isRead;

    /**
     * 用户 ID
     */
    @Schema(hidden = true)
    private String userId;
}