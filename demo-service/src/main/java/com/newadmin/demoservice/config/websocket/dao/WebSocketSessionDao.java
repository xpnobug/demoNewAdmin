package com.newadmin.demoservice.config.websocket.dao;

import com.newadmin.demoservice.config.websocket.heart.WsHeartbeatType;
import org.springframework.web.socket.WebSocketSession;

/**
 * WebSocket 会话 DAO
 */
public interface WebSocketSessionDao {

    /**
     * 添加会话
     *
     * @param key     会话 Key
     * @param session 会话信息
     */
    void add(String key, WebSocketSession session);

    /**
     * 删除会话
     *
     * @param key 会话 Key
     */
    void delete(String key);

    /**
     * 获取会话
     *
     * @param key 会话 Key
     * @return 会话信息
     */
    WebSocketSession get(String key);

    // 初始化心跳定时器
    void initHeartbeat();

    // 处理收到的心跳消息
    void handleHeartbeat(WsHeartbeatType data);
}
