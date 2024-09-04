package com.newadmin.demoservice.config.websocket.autoconfigure;

import cn.hutool.extra.spring.SpringUtil;
import com.newadmin.democonfig.constant.PropertiesConstants;
import com.newadmin.demoservice.config.websocket.core.WebSocketClientService;
import com.newadmin.demoservice.config.websocket.core.WebSocketInterceptor;
import com.newadmin.demoservice.config.websocket.dao.WebSocketSessionDao;
import com.newadmin.demoservice.config.websocket.dao.WebSocketSessionDaoDefaultImpl;
import com.newadmin.demoservice.mainPro.livepro.service.impl.SocketLiveImpl;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * WebSocket 自动配置
 */
@AutoConfiguration
@EnableWebSocket
@EnableConfigurationProperties(WebSocketProperties.class)
@ConditionalOnProperty(prefix = PropertiesConstants.MESSAGING_WEBSOCKET, name = PropertiesConstants.ENABLED, matchIfMissing = true)
public class WebSocketAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(WebSocketAutoConfiguration.class);
    private final WebSocketProperties properties;

    public WebSocketAutoConfiguration(WebSocketProperties properties) {
        this.properties = properties;
    }

    @Bean
    public WebSocketConfigurer webSocketConfigurer(WebSocketHandler handler,
        HandshakeInterceptor interceptor) {
        return registry -> registry.addHandler(handler, properties.getPath())
            .addInterceptors(interceptor)
            .setAllowedOrigins(properties.getAllowedOrigins().toArray(String[]::new));
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketHandler webSocketHandler() {
        return new com.newadmin.demoservice.config.websocket.core.WebSocketHandler(properties,
            SpringUtil
                .getBean(WebSocketSessionDao.class), SpringUtil.getBean(SocketLiveImpl.class));
    }

    @Bean
    @ConditionalOnMissingBean
    public HandshakeInterceptor handshakeInterceptor() {
        return new WebSocketInterceptor(properties,
            SpringUtil.getBean(WebSocketClientService.class));
    }

    /**
     * WebSocket 会话 DAO
     */
    @Bean
    @ConditionalOnMissingBean
    public WebSocketSessionDao webSocketSessionDao() {
        return new WebSocketSessionDaoDefaultImpl();
    }

    /**
     * WebSocket 客户端服务（如不提供，则报错）
     */
    @Bean
    @ConditionalOnMissingBean
    public WebSocketClientService webSocketClientService() {
        throw new NoSuchBeanDefinitionException(WebSocketClientService.class);
    }

    @PostConstruct
    public void postConstruct() {
        log.debug(
            "[newAdmin] - Auto Configuration 'Messaging-WebSocket' completed initialization.");
    }
}
