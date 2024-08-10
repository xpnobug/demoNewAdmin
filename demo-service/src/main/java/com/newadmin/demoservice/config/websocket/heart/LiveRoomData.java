package com.newadmin.demoservice.config.websocket.heart;

import java.util.Date;

public class LiveRoomData {

    private int liveRoomId;
    private String socketId;
    private Date createdAt;
    private Date expiredAt;
    private String clientIp;

    /**
     * 构造方法
     *
     * @param liveRoomId 直播间 ID
     * @param socketId   WebSocket 的唯一标识符
     * @param createdAt  创建时间戳，可选
     * @param expiredAt  过期时间戳，可选
     * @param clientIp   客户端 IP 地址，可选
     */
    public LiveRoomData(int liveRoomId, String socketId, Date createdAt, Date expiredAt,
        String clientIp) {
        this.liveRoomId = liveRoomId;
        this.socketId = socketId;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.clientIp = clientIp;
    }

    /**
     * 过滤对象的方法，仅返回部分字段作为示例
     *
     * @return 过滤后的字符串
     */
    public String toFilteredString() {
        return String.format("liveRoomId=%d, socketId=%s, clientIp=%s", liveRoomId, socketId,
            clientIp);
    }
}

