package com.newadmin.demoservice.mainPro.filepro.resp;

import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.demoservice.mainPro.filepro.enums.StorageTypeEnum;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.DisEnableStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;

/**
 * 存储响应信息
 */

@Schema(description = "存储响应信息")
public class StorageResp extends ValueMap {

    /**
     * 名称
     */
    public static final String NAME = "name";

    /**
     * 编码
     */
    public static final String CODE = "code";

    /**
     * 状态
     */
    public static final String STATUS = "status";

    /**
     * 类型
     */
    public static final String TYPE = "type";

    /**
     * 访问密钥
     */
    public static final String ACCESS_KEY = "accessKey";

    /**
     * 私有密钥
     */
    public static final String SECRET_KEY = "secretKey";

    /**
     * 终端节点
     */
    public static final String ENDPOINT = "endpoint";

    /**
     * 桶名称
     */
    public static final String BUCKET_NAME = "bucketName";

    /**
     * 域名
     */
    public static final String DOMAIN = "domain";

    /**
     * 描述
     */
    public static final String DESCRIPTION = "description";

    /**
     * 是否为默认存储
     */
    public static final String IS_DEFAULT = "isDefault";

    /**
     * 排序
     */
    public static final String SORT = "sort";

    public StorageResp() {
    }

    public StorageResp(Map<String, Object> map) {
        super(map);
    }

//    @Override
//    public Boolean getDisabled() {
//        return this.getIsDefault();
//    }

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
     * 设置 编码
     *
     * @param code 编码
     */
    public void setCode(String code) {
        super.setValue(CODE, code);
    }

    /**
     * 获取 编码
     *
     * @return 编码
     */
    public String getCode() {
        return super.getValueAsString(CODE);
    }

    /**
     * 设置 状态
     *
     * @param status 状态
     */
    public void setStatus(DisEnableStatusEnum status) {
        super.setValue(STATUS, status);
    }

    /**
     * 获取 状态
     *
     * @return 状态
     */
    public String getStatus() {
        return super.getValueAsString(STATUS);
    }

    /**
     * 设置 类型
     *
     * @param type 类型
     */
    public void setType(StorageTypeEnum type) {
        super.setValue(TYPE, type);
    }

    /**
     * 获取 类型
     *
     * @return 类型
     */
    public String getType() {
        return super.getValueAsString(TYPE);
    }

    /**
     * 设置 访问密钥
     *
     * @param accessKey 访问密钥
     */
    public void setAccessKey(String accessKey) {
        super.setValue(ACCESS_KEY, accessKey);
    }

    /**
     * 获取 访问密钥
     *
     * @return 访问密钥
     */
    public String getAccessKey() {
        return super.getValueAsString(ACCESS_KEY);
    }

    /**
     * 设置 私有密钥
     *
     * @param secretKey 私有密钥
     */
    public void setSecretKey(String secretKey) {
        super.setValue(SECRET_KEY, secretKey);
    }

    /**
     * 获取 私有密钥
     *
     * @return 私有密钥
     */
    public String getSecretKey() {
        return super.getValueAsString(SECRET_KEY);
    }

    /**
     * 设置 终端节点
     *
     * @param endpoint 终端节点
     */
    public void setEndpoint(String endpoint) {
        super.setValue(ENDPOINT, endpoint);
    }

    /**
     * 获取 终端节点
     *
     * @return 终端节点
     */
    public String getEndpoint() {
        return super.getValueAsString(ENDPOINT);
    }

    /**
     * 设置 桶名称
     *
     * @param bucketName 桶名称
     */
    public void setBucketName(String bucketName) {
        super.setValue(BUCKET_NAME, bucketName);
    }

    /**
     * 获取 桶名称
     *
     * @return 桶名称
     */
    public String getBucketName() {
        return super.getValueAsString(BUCKET_NAME);
    }

    /**
     * 设置 域名
     *
     * @param domain 域名
     */
    public void setDomain(String domain) {
        super.setValue(DOMAIN, domain);
    }

    /**
     * 获取 域名
     *
     * @return 域名
     */
    public String getDomain() {
        return super.getValueAsString(DOMAIN);
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

    /**
     * 设置 是否为默认存储
     *
     * @param isDefault 是否为默认存储
     */
    public void setIsDefault(Boolean isDefault) {
        super.setValue(IS_DEFAULT, isDefault);
    }

    /**
     * 获取 是否为默认存储
     *
     * @return 是否为默认存储
     */
    public Boolean getIsDefault() {
        return super.getValueAsBoolean(IS_DEFAULT);
    }

    /**
     * 设置 排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        super.setValue(SORT, sort);
    }

    /**
     * 获取 排序
     *
     * @return 排序
     */
    public Integer getSort() {
        return super.getValueAsInteger(SORT);
    }
}