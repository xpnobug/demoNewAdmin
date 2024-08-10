package com.newadmin.demoservice.mainPro.livepro.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import lombok.Data;

/**
 * 创建或修改直播记录信息
 *
 * @author couei
 * @since 2024/08/05 22:02
 */
@Data
@Schema(description = "创建或修改直播记录信息")
public class LiveReq {

    @Serial
    private static final long serialVersionUID = 1L;
}