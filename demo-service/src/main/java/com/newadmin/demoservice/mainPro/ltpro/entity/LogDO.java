package com.newadmin.demoservice.mainPro.ltpro.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.LogStatusEnum;
import java.util.Date;
import java.util.Map;

/**
 * 日志实体
 */

@TableName("reai_log")
public class LogDO extends ValueMap {

    /**
     * ID
     */
    public static final String ID = "id";

    /**
     * 链路 ID
     */
    public static final String TRACE_ID = "traceId";

    /**
     * 日志描述
     */
    public static final String DESCRIPTION = "description";

    /**
     * 所属模块
     */
    public static final String MODULE = "module";

    /**
     * 请求 URL
     */
    public static final String REQUEST_URL = "requestUrl";

    /**
     * 请求方式
     */
    public static final String REQUEST_METHOD = "requestMethod";

    /**
     * 请求头
     */
    public static final String REQUEST_HEADERS = "requestHeaders";

    /**
     * 请求体
     */
    public static final String REQUEST_BODY = "requestBody";

    /**
     * 状态码
     */
    public static final String STATUS_CODE = "statusCode";

    /**
     * 响应头
     */
    public static final String RESPONSE_HEADERS = "responseHeaders";

    /**
     * 响应体
     */
    public static final String RESPONSE_BODY = "responseBody";

    /**
     * 耗时（ms）
     */
    public static final String TIME_TAKEN = "timeTaken";

    /**
     * IP
     */
    public static final String IP = "ip";

    /**
     * IP 归属地
     */
    public static final String ADDRESS = "address";

    /**
     * 浏览器
     */
    public static final String BROWSER = "browser";

    /**
     * 操作系统
     */
    public static final String OS = "os";

    /**
     * 状态
     */
    public static final String STATUS = "status";

    /**
     * 错误信息
     */
    public static final String ERROR_MSG = "errorMsg";

    /**
     * 创建人
     */
    public static final String CREATE_USER = "createUser";

    /**
     * 创建时间
     */
    public static final String CREATE_TIME = "createTime";

    public LogDO() {
    }

    public LogDO(Map<String, Object> map) {
        super(map);
    }

    /**
     * 设置 ID
     *
     * @param id ID
     */
    public void setId(String id) {
        super.setValue(ID, id);
    }

    /**
     * 获取 ID
     *
     * @return ID
     */
    public String getId() {
        return super.getValueAsString(ID);
    }

    /**
     * 设置 链路 ID
     *
     * @param traceId 链路 ID
     */
    public void setTraceId(String traceId) {
        super.setValue(TRACE_ID, traceId);
    }

    /**
     * 获取 链路 ID
     *
     * @return 链路 ID
     */
    public String getTraceId() {
        return super.getValueAsString(TRACE_ID);
    }

    /**
     * 设置 日志描述
     *
     * @param description 日志描述
     */
    public void setDescription(String description) {
        super.setValue(DESCRIPTION, description);
    }

    /**
     * 获取 日志描述
     *
     * @return 日志描述
     */
    public String getDescription() {
        return super.getValueAsString(DESCRIPTION);
    }

    /**
     * 设置 所属模块
     *
     * @param module 所属模块
     */
    public void setModule(String module) {
        super.setValue(MODULE, module);
    }

    /**
     * 获取 所属模块
     *
     * @return 所属模块
     */
    public String getModule() {
        return super.getValueAsString(MODULE);
    }

    /**
     * 设置 请求 URL
     *
     * @param requestUrl 请求 URL
     */
    public void setRequestUrl(String requestUrl) {
        super.setValue(REQUEST_URL, requestUrl);
    }

    /**
     * 获取 请求 URL
     *
     * @return 请求 URL
     */
    public String getRequestUrl() {
        return super.getValueAsString(REQUEST_URL);
    }

    /**
     * 设置 请求方式
     *
     * @param requestMethod 请求方式
     */
    public void setRequestMethod(String requestMethod) {
        super.setValue(REQUEST_METHOD, requestMethod);
    }

    /**
     * 获取 请求方式
     *
     * @return 请求方式
     */
    public String getRequestMethod() {
        return super.getValueAsString(REQUEST_METHOD);
    }

    /**
     * 设置 请求头
     *
     * @param requestHeaders 请求头
     */
    public void setRequestHeaders(String requestHeaders) {
        super.setValue(REQUEST_HEADERS, requestHeaders);
    }

    /**
     * 获取 请求头
     *
     * @return 请求头
     */
    public String getRequestHeaders() {
        return super.getValueAsString(REQUEST_HEADERS);
    }

    /**
     * 设置 请求体
     *
     * @param requestBody 请求体
     */
    public void setRequestBody(String requestBody) {
        super.setValue(REQUEST_BODY, requestBody);
    }

    /**
     * 获取 请求体
     *
     * @return 请求体
     */
    public String getRequestBody() {
        return super.getValueAsString(REQUEST_BODY);
    }

    /**
     * 设置 状态码
     *
     * @param statusCode 状态码
     */
    public void setStatusCode(Integer statusCode) {
        super.setValue(STATUS_CODE, statusCode);
    }

    /**
     * 获取 状态码
     *
     * @return 状态码
     */
    public Integer getStatusCode() {
        return super.getValueAsInteger(STATUS_CODE);
    }

    /**
     * 设置 响应头
     *
     * @param responseHeaders 响应头
     */
    public void setResponseHeaders(String responseHeaders) {
        super.setValue(RESPONSE_HEADERS, responseHeaders);
    }

    /**
     * 获取 响应头
     *
     * @return 响应头
     */
    public String getResponseHeaders() {
        return super.getValueAsString(RESPONSE_HEADERS);
    }

    /**
     * 设置 响应体
     *
     * @param responseBody 响应体
     */
    public void setResponseBody(String responseBody) {
        super.setValue(RESPONSE_BODY, responseBody);
    }

    /**
     * 获取 响应体
     *
     * @return 响应体
     */
    public String getResponseBody() {
        return super.getValueAsString(RESPONSE_BODY);
    }

    /**
     * 设置 耗时（ms）
     *
     * @param timeTaken 耗时（ms）
     */
    public void setTimeTaken(Long timeTaken) {
        super.setValue(TIME_TAKEN, timeTaken);
    }

    /**
     * 获取 耗时（ms）
     *
     * @return 耗时（ms）
     */
    public Long getTimeTaken() {
        return super.getValueAsLong(TIME_TAKEN);
    }

    /**
     * 设置 IP
     *
     * @param ip IP
     */
    public void setIp(String ip) {
        super.setValue(IP, ip);
    }

    /**
     * 获取 IP
     *
     * @return IP
     */
    public String getIp() {
        return super.getValueAsString(IP);
    }

    /**
     * 设置 IP 归属地
     *
     * @param address IP 归属地
     */
    public void setAddress(String address) {
        super.setValue(ADDRESS, address);
    }

    /**
     * 获取 IP 归属地
     *
     * @return IP 归属地
     */
    public String getAddress() {
        return super.getValueAsString(ADDRESS);
    }

    /**
     * 设置 浏览器
     *
     * @param browser 浏览器
     */
    public void setBrowser(String browser) {
        super.setValue(BROWSER, browser);
    }

    /**
     * 获取 浏览器
     *
     * @return 浏览器
     */
    public String getBrowser() {
        return super.getValueAsString(BROWSER);
    }

    /**
     * 设置 操作系统
     *
     * @param os 操作系统
     */
    public void setOs(String os) {
        super.setValue(OS, os);
    }

    /**
     * 获取 操作系统
     *
     * @return 操作系统
     */
    public String getOs() {
        return super.getValueAsString(OS);
    }

    /**
     * 设置 状态
     *
     * @param status 状态
     */
    public void setStatus(LogStatusEnum status) {
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
     * 设置 错误信息
     *
     * @param errorMsg 错误信息
     */
    public void setErrorMsg(String errorMsg) {
        super.setValue(ERROR_MSG, errorMsg);
    }

    /**
     * 获取 错误信息
     *
     * @return 错误信息
     */
    public String getErrorMsg() {
        return super.getValueAsString(ERROR_MSG);
    }

    /**
     * 设置 创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        super.setValue(CREATE_USER, createUser);
    }

    /**
     * 获取 创建人
     *
     * @return 创建人
     */
    public String getCreateUser() {
        return super.getValueAsString(CREATE_USER);
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
}
