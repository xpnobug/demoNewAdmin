package com.newadmin.demogenerator.model.entity;

import com.newadmin.democore.kduck.service.ValueMap;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.Map;

/**
 * 字段配置实体
 *
 * @author couei
 * @author Charles7c
 * @since 2023/4/12 20:21
 */

@Schema(description = "字段配置信息")
public class FieldConfig extends ValueMap {

    /***/
    public static final String ID = "id";
    /**
     * 表名称
     */
    public static final String TABLE_NAME = "tableName";

    /**
     * 列名称
     */
    public static final String COLUMN_NAME = "columnName";

    /**
     * 列类型
     */
    public static final String COLUMN_TYPE = "columnType";

    /**
     * 列大小
     */
    public static final String COLUMN_SIZE = "columnSize";

    /**
     * 字段名称
     */
    public static final String FIELD_NAME = "fieldName";

    /**
     * 字段类型
     */
    public static final String FIELD_TYPE = "fieldType";

    /**
     * 字段排序
     */
    public static final String FIELD_SORT = "fieldSort";

    /**
     * 注释
     */
    public static final String COMMENT = "comment";

    /**
     * 是否必填
     */
    public static final String IS_REQUIRED = "isRequired";

    /**
     * 是否在列表中显示
     */
    public static final String SHOW_IN_LIST = "showInList";

    /**
     * 是否在表单中显示
     */
    public static final String SHOW_IN_FORM = "showInForm";

    /**
     * 是否在查询中显示
     */
    public static final String SHOW_IN_QUERY = "showInQuery";

    /**
     * 表单类型
     */
    public static final String FORM_TYPE = "formType";

    /**
     * 查询方式
     */
    public static final String QUERY_TYPE = "queryType";

    /**
     * 创建时间
     */
    public static final String CREATE_TIME = "createTime";

    public FieldConfig() {
    }

    public FieldConfig(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 表名称
     *
     * @param tableName 表名称
     */
    public void setTableName(String tableName) {
        super.setValue(TABLE_NAME, tableName);
    }

    /**
     * 获取 表名称
     *
     * @return 表名称
     */
    public String getTableName() {
        return super.getValueAsString(TABLE_NAME);
    }

    /**
     * 设置 列名称
     *
     * @param columnName 列名称
     */
    public void setColumnName(String columnName) {
        super.setValue(COLUMN_NAME, columnName);
    }

    /**
     * 获取 列名称
     *
     * @return 列名称
     */
    public String getColumnName() {
        return super.getValueAsString(COLUMN_NAME);
    }

    /**
     * 设置 列类型
     *
     * @param columnType 列类型
     */
    public void setColumnType(String columnType) {
        super.setValue(COLUMN_TYPE, columnType);
    }

    /**
     * 获取 列类型
     *
     * @return 列类型
     */
    public String getColumnType() {
        return super.getValueAsString(COLUMN_TYPE);
    }

    /**
     * 设置 列大小
     *
     * @param columnSize 列大小
     */
    public void setColumnSize(String columnSize) {
        super.setValue(COLUMN_SIZE, columnSize);
    }

    /**
     * 获取 列大小
     *
     * @return 列大小
     */
    public String getColumnSize() {
        return super.getValueAsString(COLUMN_SIZE);
    }

    /**
     * 设置 字段名称
     *
     * @param fieldName 字段名称
     */
    public void setFieldName(String fieldName) {
        super.setValue(FIELD_NAME, fieldName);
    }

    /**
     * 获取 字段名称
     *
     * @return 字段名称
     */
    public String getFieldName() {
        return super.getValueAsString(FIELD_NAME);
    }

    /**
     * 设置 字段类型
     *
     * @param fieldType 字段类型
     */
    public void setFieldType(String fieldType) {
        super.setValue(FIELD_TYPE, fieldType);
    }

    /**
     * 获取 字段类型
     *
     * @return 字段类型
     */
    public String getFieldType() {
        return super.getValueAsString(FIELD_TYPE);
    }

    /**
     * 设置 字段排序
     *
     * @param fieldSort 字段排序
     */
    public void setFieldSort(Integer fieldSort) {
        super.setValue(FIELD_SORT, fieldSort);
    }

    /**
     * 获取 字段排序
     *
     * @return 字段排序
     */
    public Integer getFieldSort() {
        return super.getValueAsInteger(FIELD_SORT);
    }

    /**
     * 设置 注释
     *
     * @param comment 注释
     */
    public void setComment(String comment) {
        super.setValue(COMMENT, comment);
    }

    /**
     * 获取 注释
     *
     * @return 注释
     */
    public String getComment() {
        return super.getValueAsString(COMMENT);
    }

    /**
     * 设置 是否必填
     *
     * @param isRequired 是否必填
     */
    public void setIsRequired(Boolean isRequired) {
        super.setValue(IS_REQUIRED, isRequired);
    }

    /**
     * 获取 是否必填
     *
     * @return 是否必填
     */
    public Boolean getIsRequired() {
        return super.getValueAsBoolean(IS_REQUIRED);
    }

    /**
     * 设置 是否在列表中显示
     *
     * @param showInList 是否在列表中显示
     */
    public void setShowInList(Boolean showInList) {
        super.setValue(SHOW_IN_LIST, showInList);
    }

    /**
     * 获取 是否在列表中显示
     *
     * @return 是否在列表中显示
     */
    public Boolean getShowInList() {
        return super.getValueAsBoolean(SHOW_IN_LIST);
    }

    /**
     * 设置 是否在表单中显示
     *
     * @param showInForm 是否在表单中显示
     */
    public void setShowInForm(Boolean showInForm) {
        super.setValue(SHOW_IN_FORM, showInForm);
    }

    /**
     * 获取 是否在表单中显示
     *
     * @return 是否在表单中显示
     */
    public Boolean getShowInForm() {
        return super.getValueAsBoolean(SHOW_IN_FORM);
    }

    /**
     * 设置 是否在查询中显示
     *
     * @param showInQuery 是否在查询中显示
     */
    public void setShowInQuery(Boolean showInQuery) {
        super.setValue(SHOW_IN_QUERY, showInQuery);
    }

    /**
     * 获取 是否在查询中显示
     *
     * @return 是否在查询中显示
     */
    public Boolean getShowInQuery() {
        return super.getValueAsBoolean(SHOW_IN_QUERY);
    }

    /**
     * 设置 表单类型
     *
     * @param formType 表单类型
     */
    public void setFormType(Integer formType) {
        super.setValue(FORM_TYPE, formType);
    }

    /**
     * 获取 表单类型
     *
     * @return 表单类型
     */
    public Integer getFormType() {
        return super.getValueAsInteger(FORM_TYPE);
    }

    /**
     * 设置 查询方式
     *
     * @param queryType 查询方式
     */
    public void setQueryType(Integer queryType) {
        super.setValue(QUERY_TYPE, queryType);
    }

    /**
     * 获取 查询方式
     *
     * @return 查询方式
     */
    public Integer getQueryType() {
        return super.getValueAsInt(QUERY_TYPE);
    }

    /**
     * 设置 创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        super.setValue(CREATE_TIME, createTime);
    }

    /**
     * 获取 创建时间
     *
     * @return 创建时间
     */
    public Date getCreateTime() {
        return super.getValueAsDate(CREATE_TIME);
    }

    /**
     * 设置
     *
     * @param id
     */
    public void setId(String id) {
        super.setValue(ID, id);
    }

    /**
     * 获取
     *
     * @return
     */
    public String getId() {
        return super.getValueAsString(ID);
    }
}
