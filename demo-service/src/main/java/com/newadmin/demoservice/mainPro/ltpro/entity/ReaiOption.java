
package com.newadmin.demoservice.mainPro.ltpro.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.newadmin.democommon.service.ValueMap;
import java.util.Map;

/**
 * 参数实体
 */

@TableName("reai_option")
public class ReaiOption extends ValueMap {

    /**
     * 类别
     */
    public static final String CATEGORY = "category";

    /**
     * 名称
     */
    public static final String NAME = "name";

    /**
     * 键
     */
    public static final String CODE = "code";

    /**
     * 值
     */
    public static final String VALUE = "value";

    /**
     * 默认值
     */
    public static final String DEFAULT_VALUE = "defaultValue";

    /**
     * 描述
     */
    public static final String DESCRIPTION = "description";

    public ReaiOption() {
    }

    public ReaiOption(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 类别
     *
     * @param category 类别
     */
    public void setCategory(String category) {
        super.setValue(CATEGORY, category);
    }

    /**
     * 获取 类别
     *
     * @return 类别
     */
    public String getCategory() {
        return super.getValueAsString(CATEGORY);
    }

    /**
     * 设置 名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        super.setValue(NAME, name);
    }

    /**
     * 获取 名称
     *
     * @return 名称
     */
    public String getName() {
        return super.getValueAsString(NAME);
    }

    /**
     * 设置 键
     *
     * @param code 键
     */
    public void setCode(String code) {
        super.setValue(CODE, code);
    }

    /**
     * 获取 键
     *
     * @return 键
     */
    public String getCode() {
        return super.getValueAsString(CODE);
    }

    /**
     * 设置 值
     *
     * @param value 值
     */
    public void setValue(String value) {
        super.setValue(VALUE, value);
    }

    /**
     * 获取 值
     *
     * @return 值
     */
    public String getValue() {
        return super.getValueAsString(VALUE);
    }

    /**
     * 设置 默认值
     *
     * @param defaultValue 默认值
     */
    public void setDefaultValue(String defaultValue) {
        super.setValue(DEFAULT_VALUE, defaultValue);
    }

    /**
     * 获取 默认值
     *
     * @return 默认值
     */
    public String getDefaultValue() {
        return super.getValueAsString(DEFAULT_VALUE);
    }

    /**
     * 设置 描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        super.setValue(DESCRIPTION, description);
    }

    /**
     * 获取 描述
     *
     * @return 描述
     */
    public String getDescription() {
        return super.getValueAsString(DESCRIPTION);
    }
}
