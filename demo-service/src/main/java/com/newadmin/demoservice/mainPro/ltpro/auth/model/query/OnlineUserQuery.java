
package com.newadmin.demoservice.mainPro.ltpro.auth.model.query;

import cn.hutool.core.date.DatePattern;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 在线用户查询条件
 *
 * @author couei
 * @since 2023/1/20 23:07
 */
@Data
@Schema(description = "在线用户查询条件")
public class OnlineUserQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称", example = "张三")
    private String nickname;

    /**
     * 登录时间
     */
    @Schema(description = "登录时间", example = "2023-08-08 00:00:00,2023-08-08 23:59:59")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private List<Date> loginTime;
}
