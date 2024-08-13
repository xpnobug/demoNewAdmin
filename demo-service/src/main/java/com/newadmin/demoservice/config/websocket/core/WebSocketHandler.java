package com.newadmin.demoservice.config.websocket.core;

import cn.hutool.core.convert.Convert;
import com.newadmin.demoservice.config.websocket.autoconfigure.WebSocketProperties;
import com.newadmin.demoservice.config.websocket.dao.WebSocketSessionDao;
import com.newadmin.demoservice.mainPro.livepro.service.impl.SocketLiveImpl;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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

    private final SocketLiveImpl socketLiveImpl;
    private ScheduledExecutorService heartbeatExecutor;

    // 构造函数，注入 WebSocket 配置属性和会话 DAO
    public WebSocketHandler(WebSocketProperties webSocketProperties,
        WebSocketSessionDao webSocketSessionDao, SocketLiveImpl socketLiveImpl) {
        this.webSocketProperties = webSocketProperties;
        this.webSocketSessionDao = webSocketSessionDao;
        this.socketLiveImpl = socketLiveImpl;
    }

    // 处理文本消息
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
        throws Exception {
        // 获取客户端 ID
        String clientId = this.getClientId(session);
        // 日志记录收到的消息
//        log.info("WebSocket接收消息。clientId: {}, message: {}.", clientId, message.getPayload());

        // 根据消息类型执行不同的处理逻辑
        socketLiveImpl.handleMethod(message, session);
        // 调用父类方法处理消息
        super.handleTextMessage(session, message);
    }

    // 处理连接建立事件
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        // 获取客户端 ID
        String clientId = this.getClientId(session);
        // 添加连接会话到 DAO
        webSocketSessionDao.add(clientId, session);
        // 日志记录成功连接
        // 发送包含连接信息的消息
        String connectionInfo = "0{\"sid\": \"" + session.getId()
            + "\", \"upgrades\": [], \"pingInterval\": 25000, \"pingTimeout\": 20000, \"maxPayload\": 100000000}";
        session.sendMessage(new TextMessage(connectionInfo));
        // 启动心跳机制
        startHeartbeat(session);
        log.info("WebSocket客户端连接成功。clientId: {}.", clientId);
    }

    private void startHeartbeat(WebSocketSession session) {
        // 创建一个定时任务，每隔 25 秒发送一次心跳消息
        heartbeatExecutor = Executors.newSingleThreadScheduledExecutor();
        heartbeatExecutor.scheduleAtFixedRate(() -> {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage("2"));
                } catch (IOException e) {
                    log.error("发送心跳消息失败。", e);
                }
            }
        }, 0, 25, TimeUnit.SECONDS);
    }

    // 处理连接关闭事件
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // 获取客户端 ID
        String clientId = this.getClientId(session);
//         从 DAO 中删除连接会话
        webSocketSessionDao.delete(clientId);
        // 日志记录连接关闭
        log.info("WebSocket客户端连接关闭。clientId: {}.", status);
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

    private String getClientId(WebSocketSession session) {
        // 从会话属性中获取客户端 ID
        return Convert.toStr(session.getAttributes().get(webSocketProperties.getClientIdKey()));
    }

}
