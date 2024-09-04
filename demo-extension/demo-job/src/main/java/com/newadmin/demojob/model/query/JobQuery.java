package com.newadmin.demojob.model.query;

import com.newadmin.demojob.enums.JobStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 任务查询条件
 *
 * @author KAI
 * @since 2024/6/25 16:43
 */
@Data
@Schema(description = "任务查询条件")
public class JobQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务组
     */
    @Schema(description = "任务组", example = "continew-admin")
    private String groupName;

    /**
     * 任务名称
     */
    @Schema(description = "任务名称", example = "定时任务1")
    private String jobName;

    /**
     * 任务状态
     */
    @Schema(description = "任务状态", example = "1")
    private JobStatusEnum jobStatus;

    /**
     * 页码
     */
    @Schema(description = "页码", example = "1")
    @Min(value = 1, message = "页码最小值为 {value}")
    private Integer page = 1;

    /**
     * 每页条数
     */
    @Schema(description = "每页条数", example = "10")
    private Integer size = 10;
}
