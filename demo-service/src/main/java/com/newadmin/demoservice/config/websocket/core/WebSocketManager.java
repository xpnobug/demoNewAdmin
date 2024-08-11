package com.newadmin.demoservice.config.websocket.core;

import org.springframework.web.socket.WebSocketSession;

public class WebSocketManager {

    // 用静态变量来存储 WebSocketSession
    private static WebSocketSession session;

    public static void storeSession(WebSocketSession webSocketSession) {
        session = webSocketSession;
    }

    public static WebSocketSession getSession() {
        return session;
    }
}
