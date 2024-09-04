package com.newadmin.democonfig.cors;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 跨域配置属性
 */
@Data
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {

    /**
     * 是否启用跨域配置
     */
    private boolean enabled = false;

    /**
     * 允许跨域的域名
     */
    private List<String> allowedOrigins = new ArrayList<>();

    /**
     * 允许跨域的请求方式
     */
    private List<String> allowedMethods = new ArrayList<>();

    /**
     * 允许跨域的请求头
     */
    private List<String> allowedHeaders = new ArrayList<>();

    /**
     * 允许跨域的响应头
     */
    private List<String> exposedHeaders = new ArrayList<>();
}
