package com.newadmin.demoservice.websocket.config;

import com.newadmin.demoservice.websocket.handle.MyWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration // 声明这是一个配置类
@EnableWebSocket // 启用 WebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 注册 WebSocket 处理程序，并设置 WebSocket 端点为 /websocket，允许所有来源
        registry.addHandler(new MyWebSocketHandler(), "/websockets").setAllowedOrigins("*");
    }
}
