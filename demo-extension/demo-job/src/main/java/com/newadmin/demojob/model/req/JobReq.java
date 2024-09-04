package com.newadmin.demojob.model.req;

import com.newadmin.demojob.enums.JobBlockStrategyEnum;
import com.newadmin.demojob.enums.JobRouteStrategyEnum;
import com.newadmin.demojob.enums.JobStatusEnum;
import com.newadmin.demojob.enums.JobTaskTypeEnum;
import com.newadmin.demojob.enums.JobTriggerTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 创建或修改任务信息
 *
 * @author KAI
 * @author couei
 * @author Charles7c
 * @since 2024/6/25 16:40
 */
@Data
@Schema(description = "创建或修改任务信息")
public class JobReq implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 任务组
     */
    @Schema(description = "任务组", example = "continew-admin")
    @NotBlank(message = "任务组不能为空")
    private String groupName;

    /**
     * 任务名称
     */
    @Schema(description = "任务名称", example = "定时任务1")
    @NotBlank(message = "任务名称不能为空")
    private String jobName;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "定时任务1的描述")
    private String description;

    /**
     * 触发类型
     */
    @Schema(description = "触发类型", example = "2")
    @NotNull(message = "触发类型非法")
    private Integer triggerType;

    /**
     * 间隔时长
     */
    @Schema(description = "间隔时长", example = "60")
    @NotBlank(message = "间隔时长不能为空")
    private String triggerInterval;

    /**
     * 执行器类型
     */
    @Schema(description = "执行器类型", example = "1", defaultValue = "1")
    private Integer executorType = 1;

    /**
     * 任务类型
     */
    @Schema(description = "任务类型", example = "1")
    @NotNull(message = "任务类型非法")
    private Integer taskType;

    /**
     * 执行器名称
     */
    @Schema(description = "执行器名称", example = "test")
    @NotBlank(message = "执行器名称不能为空")
    private String executorInfo;

    /**
     * 任务参数
     */
    @Schema(description = "任务参数", example = "")
    private String argsStr;

    /**
     * 参数类型
     */
    @Schema(description = "参数类型", example = "1")
    private Integer argsType;

    /**
     * 路由策略
     */
    @Schema(description = "路由策略", example = "4")
    @NotNull(message = "路由策略非法")
    private Integer routeKey;

    /**
     * 阻塞策略
     */
    @Schema(description = "阻塞策略", example = "1")
    @NotNull(message = "阻塞策略非法")
    private Integer blockStrategy;

    /**
     * 超时时间（单位：秒）
     */
    @Schema(description = "超时时间（单位：秒）", example = "60")
    @NotNull(message = "超时时间不能为空")
    private Integer executorTimeout;

    /**
     * 最大重试次数
     */
    @Schema(description = "最大重试次数", example = "3")
    @NotNull(message = "最大重试次数不能为空")
    private Integer maxRetryTimes;

    /**
     * 重试间隔（单位：秒）
     */
    @Schema(description = "重试间隔（单位：秒）", example = "1")
    @NotNull(message = "重试间隔不能为空")
    private Integer retryInterval;

    /**
     * 并行数
     */
    @Schema(description = "并行数", example = "1")
    @NotNull(message = "并行数不能为空")
    private Integer parallelNum;

    /**
     * 任务状态
     */
    @Schema(description = "任务状态", example = "0", defaultValue = "0")
    private Integer jobStatus = JobStatusEnum.DISABLED.getValue();

    /**
     * ID
     */
    @Schema(hidden = true)
    private Long id;
}
