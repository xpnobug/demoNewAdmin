package com.newadmin.demoservice.websocket.handle;

import com.newadmin.demoservice.mainPro.qqbots.base.config.BotEvent;
import java.io.IOException;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
        throws IOException {
        // 获取收到的消息内容
        String payload = message.getPayload();
//        System.out.println("Received message: " + payload);
        // 调用AI接口获取回复消息
//        String aiMsg = ChatGptAiUtil.chatGptAi(payload);
        String aiMsg = BotEvent.getGpt3(payload);
        // 发送回复的消息
        session.sendMessage(new TextMessage(aiMsg));

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 连接建立后的处理逻辑
        System.out.println("Connection established");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status)
        throws Exception {
        // 连接关闭后的处理逻辑
        System.out.println("Connection closed");
    }
}
