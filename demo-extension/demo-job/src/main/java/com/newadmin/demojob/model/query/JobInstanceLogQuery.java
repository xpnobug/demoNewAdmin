package com.newadmin.demojob.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 任务实例日志查询条件
 *
 * @author KAI
 * @since 2024/6/28 16:58
 */
@Data
@Schema(description = "任务实例日志查询条件")
public class JobInstanceLogQuery implements Serializable {

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

    /**
     * 任务实例 ID
     */
    @Schema(description = "任务实例ID", example = "1")
    private Long taskId;

    /**
     * 开始 ID
     */
    @Schema(description = "开始ID", example = "2850")
    private Integer startId;

    /**
     * 起始索引
     */
    @Schema(description = "起始索引", example = "0")
    @Min(value = 0, message = "起始索引最小值为 {value}")
    private Integer fromIndex = 0;

    /**
     * 每页条数
     */
    @Schema(description = "每页条数", example = "50")
    private Integer size = 50;
}
