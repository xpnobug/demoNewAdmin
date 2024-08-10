package com.newadmin.demoservice.config.websocket.core;

import com.newadmin.demoservice.config.websocket.heart.WsHeartbeatType;

public class WebSocketClient {

    private String roomId;
    private String socketId;

    // 构造方法
    public WebSocketClient(String roomId, String socketId) {
        this.roomId = roomId;
        this.socketId = socketId;
    }

    // 获取 socketId
    public String getSocketId() {
        return socketId;
    }

    // 获取直播房间ID
    public int getLiveRoomId() {
        return Integer.parseInt(roomId);
    }

    // 判断房间是否在直播
    public boolean isRoomLiving() {
        return true; // 模拟是否在直播的状态
    }

    // 发送消息的方法
    public void send(WsHeartbeatType heartbeat) {
        // 打印发送的心跳消息
        System.out.println("发送心跳消息: " + heartbeat);
    }
}