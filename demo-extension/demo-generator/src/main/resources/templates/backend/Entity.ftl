package ${packageName}.${subPackageName};

import java.io.Serial;
<#if hasLocalDateTime>
  import java.time.LocalDateTime;
</#if>
<#if hasBigDecimal>
  import java.math.BigDecimal;
</#if>

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;


/**
* ${businessName}实体
*
* @author ${author}
* @since ${date}
*/
@Data
@TableName("${tableName}")
public class ${className} {

@Serial
private static final long serialVersionUID = 1L;
<#if fieldConfigs??>
    <#list fieldConfigs as fieldConfig>

      /**
      * ${fieldConfig.comment}
      */
      private ${fieldConfig.fieldType} ${fieldConfig.fieldName};
    </#list>
</#if>
}