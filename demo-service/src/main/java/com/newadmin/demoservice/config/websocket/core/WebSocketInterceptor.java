package com.newadmin.demoservice.config.websocket.core;

import com.newadmin.demoservice.config.websocket.autoconfigure.WebSocketProperties;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * WebSocket 拦截器
 */
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    // WebSocket 配置属性
    private final WebSocketProperties webSocketProperties;
    // WebSocket 客户端服务
    private final WebSocketClientService webSocketClientService;

    // 构造函数，注入 WebSocket 配置属性和客户端服务
    public WebSocketInterceptor(WebSocketProperties webSocketProperties,
        WebSocketClientService webSocketClientService) {
        this.webSocketProperties = webSocketProperties;
        this.webSocketClientService = webSocketClientService;
    }

    // 在握手之前进行处理
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request,
//        ServerHttpResponse response,
//        WebSocketHandler wsHandler,
//        Map<String, Object> attributes) {
//        // 获取客户端 ID
//        String clientId = webSocketClientService.getClientId((ServletServerHttpRequest) request);
//        // 将客户端 ID 放入属性中
//        attributes.put(webSocketProperties.getClientIdKey(), clientId);
//        // 返回 true 表示继续握手
//        return true;
//    }

    // 在握手之后进行处理
    @Override
    public void afterHandshake(ServerHttpRequest request,
        ServerHttpResponse response,
        WebSocketHandler wsHandler,
        Exception exception) {
        // 调用父类方法处理握手之后的逻辑
        super.afterHandshake(request, response, wsHandler, exception);
    }
}
