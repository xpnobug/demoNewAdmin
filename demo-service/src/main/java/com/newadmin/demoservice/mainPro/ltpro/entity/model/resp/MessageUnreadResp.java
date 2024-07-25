package com.newadmin.demoservice.mainPro.ltpro.entity.model.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 未读消息信息
 */
@Data
@Schema(description = "未读消息信息")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageUnreadResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 未读消息数量
     */
    @Schema(description = "未读消息数量", example = "20")
    private Long total;

    /**
     * 各类型未读消息数量
     */
    @Schema(description = "各类型未读消息数量")
    private List<MessageTypeUnreadResp> details;
}