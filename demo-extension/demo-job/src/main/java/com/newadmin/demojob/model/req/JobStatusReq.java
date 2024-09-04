package com.newadmin.demojob.model.req;

import com.newadmin.demojob.enums.JobStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 修改任务状态信息
 *
 * @author KAI
 * @author couei
 * @author Charles7c
 * @since 2024/6/27 9:24
 */
@Data
@Schema(description = "修改任务状态信息")
public class JobStatusReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务状态
     */
    @Schema(description = "任务状态", example = "1")
    @NotNull(message = "任务状态非法")
    private Integer jobStatus;

    /**
     * ID
     */
    @Schema(hidden = true)
    private Long id;
}
