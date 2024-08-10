package com.newadmin.demoservice.config.websocket.heart;

import com.newadmin.demoservice.config.websocket.enmu.WsMsgTypeEnum;

public class WsHeartbeatType {

    private String requestId;
    private WsMsgTypeEnum msgType;
    private Data data;

    // 设置请求ID
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    // 设置消息类型
    public void setMsgType(WsMsgTypeEnum msgType) {
        this.msgType = msgType;
    }

    // 设置数据
    public void setData(Data data) {
        this.data = data;
    }

    // 获取数据
    public Data getData() {
        return data;
    }

    // 内部静态类 Data 用于存储心跳消息的数据
    public static class Data {

        private String socketId;
        private int liveRoomId;
        private boolean roomLiving;

        // 构造方法
        public Data(String socketId, int liveRoomId, boolean roomLiving) {
            this.socketId = socketId;
            this.liveRoomId = liveRoomId;
            this.roomLiving = roomLiving;
        }

        // 获取 socketId
        public String getSocketId() {
            return socketId;
        }

        // 获取直播房间ID
        public int getLiveRoomId() {
            return liveRoomId;
        }

        // 判断房间是否在直播
        public boolean isRoomLiving() {
            return roomLiving;
        }
    }
}