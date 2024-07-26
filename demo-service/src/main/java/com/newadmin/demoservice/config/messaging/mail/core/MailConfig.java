package com.newadmin.demoservice.config.messaging.mail.core;

import cn.hutool.core.map.MapUtil;
import com.newadmin.demoservice.config.core.utils.ValidationUtils;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

/**
 * 邮件配置
 */
public class MailConfig {

    private static final Charset DEFAULT_CHARSET;

    public static final String DEFAULT_PROTOCOL = "smtp";

    /**
     * 协议
     */
    private String protocol = DEFAULT_PROTOCOL;

    /**
     * 服务器地址
     */
    private String host;

    /**
     * 服务器端口
     */
    private Integer port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码（授权码）
     */
    private String password;

    /**
     * 发件人
     */
    private String from;

    /**
     * 是否启用 SSL 连接
     */
    private boolean sslEnabled = false;

    /**
     * SSL 端口
     */
    private Integer sslPort;

    private Charset defaultEncoding;

    private final Map<String, String> properties;

    public MailConfig() {
        this.defaultEncoding = DEFAULT_CHARSET;
        this.properties = MapUtil.newHashMap();
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public boolean isSslEnabled() {
        return sslEnabled;
    }

    public void setSslEnabled(boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
    }

    public Integer getSslPort() {
        return sslPort;
    }

    public void setSslPort(Integer sslPort) {
        this.sslPort = sslPort;
    }

    public Charset getDefaultEncoding() {
        return defaultEncoding;
    }

    public void setDefaultEncoding(Charset defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    /**
     * 将当前配置转换为 JavaMail 的 Properties 对象
     *
     * @return Properties 对象
     */
    public Properties toJavaMailProperties() {
        //配置转换为 JavaMail 的 Properties 对象
        Properties javaMailProperties = new Properties();
        javaMailProperties.putAll(this.getProperties());
        javaMailProperties.put("mail.from", this.getFrom());
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.ssl.enable", this.isSslEnabled());
        if (this.isSslEnabled()) {
            ValidationUtils.throwIfNull(this.getSslPort(), "邮件配置错误：SSL端口不能为空");
            javaMailProperties.put("mail.smtp.socketFactory.port", this.sslPort);
            // 设置SSL socket工厂类
            javaMailProperties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
            // 启用服务器主机名验证
            javaMailProperties.put("mail.smtp.ssl.checkserveridentity", true);
        }
        return javaMailProperties;
    }

    static {
        DEFAULT_CHARSET = StandardCharsets.UTF_8;
    }
}