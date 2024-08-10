package com.newadmin.demoservice.config.websocket.service;

import com.newadmin.demoservice.config.websocket.heart.LiveRoomData;
import java.util.Date;

public class LiveRoomService {

    /**
     * 设置直播间正在直播的方法
     *
     * @param liveRoomId 直播间 ID
     * @param socketId   WebSocket 的唯一标识符
     * @param createdAt  创建时间戳，可选
     * @param expiredAt  过期时间戳，可选
     * @param clientIp   客户端 IP 地址，可选
     * @return 布尔值，表示操作是否成功
     */
    public boolean setLiveRoomIsLiving(int liveRoomId, String socketId, Date createdAt,
        Date expiredAt, String clientIp) {
        try {
            // 构建 Redis 键的前缀和键
            String prefix = "REDIS_PREFIX.roomIsLiveing";
            String key = String.valueOf(liveRoomId);

            // 创建一个 LiveRoomData 对象，用于传递数据
            LiveRoomData data = new LiveRoomData(liveRoomId, socketId, createdAt, expiredAt,
                clientIp);

            // 调用 Redis 控制器的 setExVal 方法，并传递必要的参数
//            boolean res = RedisUtils.setExVal(prefix, key, data, 30);

            // 返回结果
            return true;
        } catch (Exception e) {
            // 记录错误信息
//            log.severe("设置直播间正在直播时发生错误: " + e.getMessage());
            return false;
        }
    }

}
