package com.newadmin.demojob.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 任务实例查询条件
 *
 * @author KAI
 * @since 2024/6/28 16:58
 */
@Data
@Schema(description = "任务实例查询条件")
public class JobInstanceQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务 ID
     */
    @Schema(description = "任务ID", example = "1")
    private Long jobId;

    /**
     * 任务批次 ID
     */
    @Schema(description = "任务批次ID", example = "1")
    private Long taskBatchId;
}
