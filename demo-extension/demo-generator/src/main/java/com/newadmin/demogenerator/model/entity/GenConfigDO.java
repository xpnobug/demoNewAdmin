package com.newadmin.demogenerator.model.entity;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newadmin.democonfig.util.StrUtils;
import com.newadmin.demogenerator.constant.RegexConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 生成配置实体
 *
 * @author couei
 * @author Charles7c
 * @since 2023/4/12 20:21
 */
@Data
@NoArgsConstructor
@TableName("gen_config")
@Schema(description = "生成配置信息")
public class GenConfigDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 表名称
     */
    @Schema(description = "表名称", example = "sys_user")
    @TableId(type = IdType.INPUT)
    @NotBlank(message = "表名称不能为空")
    private String tableName;

    /**
     * 模块名称
     */
    @Schema(description = "模块名称", example = "continew-admin-system")
    @NotBlank(message = "模块名称不能为空")
    private String moduleName;

    /**
     * 包名称
     */
    @Schema(description = "包名称", example = "top.continew.admin.system")
    @NotBlank(message = "包名称不能为空")
    @Pattern(regexp = RegexConstants.PACKAGE_NAME, message = "包名称格式错误")
    private String packageName;

    /**
     * 业务名称
     */
    @Schema(description = "业务名称", example = "用户")
    @NotBlank(message = "业务名称不能为空")
    private String businessName;

    /**
     * 作者
     */
    @Schema(description = "作者", example = "Charles7c")
    @NotBlank(message = "作者名称不能为空")
    private String author;

    /**
     * 表前缀
     */
    @Schema(description = "表前缀", example = "sys_")
    private String tablePrefix;

    /**
     * 是否覆盖
     */
    @Schema(description = "是否覆盖", example = "false")
    @NotNull(message = "是否覆盖不能为空")
    private Boolean isOverride;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", example = "2023-08-08 08:08:08", type = "string")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间", example = "2023-08-08 08:08:08", type = "string")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 类名前缀
     */
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    @TableField(exist = false)
    private String classNamePrefix;

    public GenConfigDO(String tableName) {
        this.tableName = tableName;
    }

    public String getClassNamePrefix() {
        String rawClassName = StrUtils.blankToDefault(this.tablePrefix, this.tableName,
            prefix -> StrUtil
                .removePrefix(this.tableName, prefix));
        return StrUtil.upperFirst(StrUtil.toCamelCase(rawClassName));
    }
}
