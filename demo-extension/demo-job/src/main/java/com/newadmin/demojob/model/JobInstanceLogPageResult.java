package com.newadmin.demojob.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 任务实例日志分页信息
 *
 * @author couei
 * @author Charles7c
 * @since 2024/7/14 21:51
 */
@Data
@Schema(description = "任务实例日志分页信息")
public class JobInstanceLogPageResult implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID", example = "1")
    private Long id;

    /**
     * 日志详情
     */
    @Schema(description = "日志详情")
    private List message;

    /**
     * 是否结束
     */
    @Schema(description = "是否结束", example = "true")
    private boolean isFinished;

    /**
     * 起始索引
     */
    @Schema(description = "起始索引", example = "0")
    private Integer fromIndex;

    /**
     * 下一个开始 ID
     */
    @Schema(description = "下一个开始ID", example = "9")
    private Long nextStartId;
}
