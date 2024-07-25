package com.newadmin.demoservice.config.websocket.core;

import cn.hutool.core.convert.Convert;
import com.newadmin.demoservice.config.websocket.autoconfigure.WebSocketProperties;
import com.newadmin.demoservice.config.websocket.dao.WebSocketSessionDao;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * WebSocket 处理器
 */
public class WebSocketHandler extends TextWebSocketHandler {

    // 日志记录器
    private static final Logger log = LoggerFactory.getLogger(WebSocketHandler.class);
    // WebSocket 配置属性
    private final WebSocketProperties webSocketProperties;
    // WebSocket 会话数据访问对象
    private final WebSocketSessionDao webSocketSessionDao;

    // 构造函数，注入 WebSocket 配置属性和会话 DAO
    public WebSocketHandler(WebSocketProperties webSocketProperties,
        WebSocketSessionDao webSocketSessionDao) {
        this.webSocketProperties = webSocketProperties;
        this.webSocketSessionDao = webSocketSessionDao;
    }

    // 处理文本消息
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
        throws Exception {
        // 获取客户端 ID
        String clientId = this.getClientId(session);
        // 日志记录收到的消息
        log.info("WebSocket接收消息。clientId: {}, message: {}.", clientId, message.getPayload());
        // 调用父类方法处理消息
        super.handleTextMessage(session, message);
    }

    // 处理连接建立事件
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // 获取客户端 ID
        String clientId = this.getClientId(session);
        // 添加连接会话到 DAO
        webSocketSessionDao.add(clientId, session);
        // 日志记录成功连接
        log.info("WebSocket客户端连接成功。clientId: {}.", clientId);
    }

    // 处理连接关闭事件
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // 获取客户端 ID
        String clientId = this.getClientId(session);
        // 从 DAO 中删除连接会话
        webSocketSessionDao.delete(clientId);
        // 日志记录连接关闭
        log.info("WebSocket客户端连接关闭。clientId: {}.", clientId);
    }

    // 处理传输错误事件
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception)
        throws IOException {
        // 获取客户端 ID
        String clientId = this.getClientId(session);
        // 如果会话是打开状态，则关闭会话
        if (session.isOpen()) {
            session.close();
        }
        // 从 DAO 中删除连接会话
        webSocketSessionDao.delete(clientId);
    }

    /**
     * 获取客户端 ID
     *
     * @param session 会话
     * @return 客户端 ID
     */
    private String getClientId(WebSocketSession session) {
        // 从会话属性中获取客户端 ID
        return Convert.toStr(session.getAttributes().get(webSocketProperties.getClientIdKey()));
    }
}
