package com.newadmin.demoservice.mainPro.filepro.resp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.demoservice.mainPro.filepro.enums.FileTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.Map;

/**
 * 文件资源统计信息
 */
@Schema(description = "文件资源统计信息")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileStatisticsResp extends ValueMap {

    /**
     * 文件类型
     */
    public static final String TYPE = "type";

    /**
     * 大小（字节）
     */
    public static final String SIZE = "size";

    /**
     * 数量
     */
    public static final String NUMBER = "number";

    /**
     * 分类数据
     */
    public static final String DATA = "data";

    public FileStatisticsResp() {
    }

    public FileStatisticsResp(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 文件类型
     *
     * @param type 文件类型
     */
    public void setType(FileTypeEnum type) {
        super.setValue(TYPE, type);
    }

    /**
     * 获取 文件类型
     *
     * @return 文件类型
     */
    public String getType() {
        return super.getValueAsString(TYPE);
    }

    /**
     * 设置 大小（字节）
     *
     * @param size 大小（字节）
     */
    public void setSize(Long size) {
        super.setValue(SIZE, size);
    }

    /**
     * 获取 大小（字节）
     *
     * @return 大小（字节）
     */
    public Long getSize() {
        return super.getValueAsLong(SIZE);
    }

    /**
     * 设置 数量
     *
     * @param number 数量
     */
    public void setNumber(Long number) {
        super.setValue(NUMBER, number);
    }

    /**
     * 获取 数量
     *
     * @return 数量
     */
    public Long getNumber() {
        return super.getValueAsLong(NUMBER);
    }

    /**
     * 设置 分类数据
     *
     * @param data 分类数据
     */
    public void setData(List<FileStatisticsResp> data) {
        super.setValue(DATA, data);
    }

    /**
     * 获取 分类数据
     *
     * @return 分类数据
     */
    public List<FileStatisticsResp> getData() {
        return super.getValueAsList(DATA);
    }
}