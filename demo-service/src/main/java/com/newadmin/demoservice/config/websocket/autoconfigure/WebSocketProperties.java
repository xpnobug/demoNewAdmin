package com.newadmin.demoservice.config.websocket.autoconfigure;

import com.newadmin.democonfig.constant.PropertiesConstants;
import com.newadmin.democonfig.constant.StringConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * WebSocket 配置属性
 */
@ConfigurationProperties(PropertiesConstants.MESSAGING_WEBSOCKET)
public class WebSocketProperties {

    private static final List<String> ALL = Collections.singletonList(StringConstants.ASTERISK);

    /**
     * 是否启用 WebSocket
     */
    private boolean enabled = true;

    /**
     * 路径
     */
    private String path = StringConstants.SLASH + "websocket";

    /**
     * 允许跨域的域名
     */
    private List<String> allowedOrigins = new ArrayList<>(ALL);

    /**
     * 客户端 ID Key
     */
    private String clientIdKey = "CLIENT_ID";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public String getClientIdKey() {
        return clientIdKey;
    }

    public void setClientIdKey(String clientIdKey) {
        this.clientIdKey = clientIdKey;
    }
}
