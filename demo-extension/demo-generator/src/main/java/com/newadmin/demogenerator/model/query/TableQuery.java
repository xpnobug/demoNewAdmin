package com.newadmin.demogenerator.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 表信息查询条件
 *
 * @author couei
 * @author Charles7c
 * @since 2023/4/12 20:21
 */
@Data
@Schema(description = "表信息查询条件")
public class TableQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 表名称
     */
    @Schema(description = "表名称", example = "sys_user")
    private String tableName;
}
