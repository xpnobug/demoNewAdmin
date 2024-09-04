package com.newadmin.demojob.model.resp;

import com.newadmin.demojob.enums.JobBlockStrategyEnum;
import com.newadmin.demojob.enums.JobRouteStrategyEnum;
import com.newadmin.demojob.enums.JobStatusEnum;
import com.newadmin.demojob.enums.JobTaskTypeEnum;
import com.newadmin.demojob.enums.JobTriggerTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 任务信息
 *
 * @author KAI
 * @author couei
 * @author Charles7c
 * @since 2024/6/25 17:15
 */
@Data
@Schema(description = "任务信息")
public class JobResp implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID", example = "1")
    private Long id;

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
     * 描述
     */
    @Schema(description = "描述", example = "定时任务1的描述")
    private String description;

    /**
     * 触发类型
     */
    @Schema(description = "触发类型", example = "2")
    private Integer triggerType;

    /**
     * 间隔时长
     */
    @Schema(description = "间隔时长", example = "60")
    private String triggerInterval;

    /**
     * 执行器类型
     */
    @Schema(description = " 执行器类型", example = "1")
    private Integer executorType;

    /**
     * 任务类型
     */
    @Schema(description = "任务类型", example = "1")
    private Integer taskType;

    /**
     * 执行器名称
     */
    @Schema(description = "执行器名称", example = "test")
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
    private String argsType;

    /**
     * 路由策略
     */
    @Schema(description = "路由策略", example = "1")
    private Integer routeKey;

    /**
     * 阻塞策略
     */
    @Schema(description = "阻塞策略", example = "1")
    private Integer blockStrategy;

    /**
     * 超时时间（单位：秒）
     */
    @Schema(description = "超时时间（单位：秒）", example = "60")
    private Integer executorTimeout;

    /**
     * 最大重试次数
     */
    @Schema(description = "最大重试次数", example = "3")
    private Integer maxRetryTimes;

    /**
     * 重试间隔（单位：秒）
     */
    @Schema(description = "重试间隔", example = "1")
    private Integer retryInterval;

    /**
     * 并行数
     */
    @Schema(description = "并行数", example = "1")
    private Integer parallelNum;

    /**
     * 任务状态
     */
    @Schema(description = "任务状态", example = "1")
    private Integer jobStatus;

    /**
     * 下次触发时间
     */
    @Schema(description = "下次触发时间", example = "2023-08-08 08:09:00", type = "string")
    private Date nextTriggerAt;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", example = "2023-08-08 08:08:00", type = "string")
    private Date createDt;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间", example = "2023-08-08 08:08:00", type = "string")
    private Date updateDt;
}