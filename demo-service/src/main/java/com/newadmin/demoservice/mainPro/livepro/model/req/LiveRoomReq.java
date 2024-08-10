package com.newadmin.demoservice.mainPro.livepro.model.req;

import com.newadmin.democore.kduck.service.ValueMap;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;

/**
 * 创建或修改直播间信息
 *
 * @author couei
 * @since 2024/08/05 21:29
 */
@Data
@Schema(description = "创建或修改直播间信息")
public class LiveRoomReq extends ValueMap {

    @Serial
    private static final long serialVersionUID = 1L;

    private String liveRoomId;
}