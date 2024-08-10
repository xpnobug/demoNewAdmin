package com.newadmin.demoservice.mainPro.livepro.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 直播记录查询条件
 *
 * @author couei
 * @since 2024/08/05 22:02
 */
@Data
@Schema(description = "直播记录查询条件")
public class LiveQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "是否展示")
    private Integer liveRoomIsShow;
}