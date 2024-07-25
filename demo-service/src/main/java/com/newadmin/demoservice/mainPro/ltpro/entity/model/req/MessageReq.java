package com.newadmin.demoservice.mainPro.ltpro.entity.model.req;

import com.newadmin.demoservice.mainPro.ltpro.common.enums.MessageTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import lombok.Data;

/**
 * 创建消息信息
 */
@Data
@Schema(description = "创建消息信息")
public class MessageReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @Schema(description = "标题", example = "欢迎注册 xxx")
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 内容
     */
    @Schema(description = "内容", example = "尊敬的 xx，欢迎注册使用，请及时配置您的密码。")
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 类型
     */
    @Schema(description = "类型（1：系统消息）", example = "1")
    @NotNull(message = "类型非法")
    private MessageTypeEnum type;
}