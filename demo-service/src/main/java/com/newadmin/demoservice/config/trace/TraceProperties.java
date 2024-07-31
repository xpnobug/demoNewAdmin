package com.newadmin.demoservice.config.trace;

import com.newadmin.democore.constant.PropertiesConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 链路跟踪配置属性
 *
 * @author Charles7c
 * @since 1.3.0
 */
@ConfigurationProperties(PropertiesConstants.WEB_TRACE)
public class TraceProperties {

    /**
     * 是否启用链路跟踪配置
     */
    private boolean enabled = false;

    /**
     * 响应头名称
     */
    private String headerName = "traceId";

    /**
     * TLog 配置
     */
    @NestedConfigurationProperty
    private TLogProperties tlog;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public TLogProperties getTlog() {
        return tlog;
    }

    public void setTlog(TLogProperties tlog) {
        this.tlog = tlog;
    }
}
