package com.newadmin.demoservice.config.websocket.dao;

import com.newadmin.demoservice.config.websocket.core.WebSocketClient;
import com.newadmin.demoservice.config.websocket.enmu.WsMsgTypeEnum;
import com.newadmin.demoservice.config.websocket.heart.WsHeartbeatType;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

/**
 * WebSocket 会话 DAO 默认实现
 */
public class WebSocketSessionDaoDefaultImpl implements WebSocketSessionDao {

    private static final Map<String, WebSocketSession> SESSION_MAP = new ConcurrentHashMap<>();
    private static final Logger log = LoggerFactory.getLogger(WebSocketSessionDaoDefaultImpl.class);

    // 定义心跳间隔时间，单位为毫秒
    private static final long HEARTBEAT_INTERVAL = 5000;

    // Timer 用于定时发送心跳包
    private Timer heartbeatTimer;

    private WebSocketClient wsClient;



    @Override
    public void add(String key, WebSocketSession session) {
        SESSION_MAP.put(key, session);
    }

    @Override
    public void delete(String key) {
        SESSION_MAP.remove(key);
    }

    @Override
    public WebSocketSession get(String key) {
        return SESSION_MAP.get(key);
    }

    // 初始化心跳定时器
    @Override
    public void initHeartbeat() {
        heartbeatTimer = new Timer();
        // 设置定时任务，0 毫秒后开始，每隔 HEARTBEAT_INTERVAL 毫秒执行一次
        heartbeatTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sendHeartbeat(); // 定时发送心跳包
            }
        }, 0, HEARTBEAT_INTERVAL);
    }

    // 发送心跳包的方法
    private void sendHeartbeat() {
        // 创建心跳消息对象
        WsHeartbeatType heartbeat = new WsHeartbeatType();
        // 设置请求ID（这里使用随机字符串）
        heartbeat.setRequestId(getRandomString(8));
        // 设置消息类型为心跳
        heartbeat.setMsgType(WsMsgTypeEnum.heartbeat);
        // 设置心跳消息的数据
        heartbeat.setData(new WsHeartbeatType.Data(
            wsClient.getSocketId(),    // 获取 socketId
            wsClient.getLiveRoomId(),  // 获取直播房间ID
            wsClient.isRoomLiving()    // 获取房间是否在直播状态
        ));
        // 通过 WebSocket 客户端发送心跳消息
        wsClient.send(heartbeat);
    }

    // 处理收到的心跳消息
    @Override
    public void handleHeartbeat(WsHeartbeatType data) {
        try {
            // 更新用户加入房间的信息
            updateUserJoinedRoom(
                data.getData().getSocketId(),
                data.getData().getLiveRoomId(),
                data.getData().isRoomLiving(),
                getSocketRealIp(wsClient)
            );
        } catch (Exception e) {
            // 捕获并打印异常信息
            log.error("处理心跳消息时出错: ", e);
        }
    }

    // 生成随机字符串的方法
    private String getRandomString(int length) {
        // 生成随机字符串的逻辑
        return "randomString"; // 简化实现
    }

    // 获取客户端实际IP的方法
    private String getSocketRealIp(WebSocketClient socket) {
        // 获取客户端实际IP的逻辑
        return "127.0.0.1"; // 简化实现
    }

    // 更新用户加入房间信息的方法
    private void updateUserJoinedRoom(String socketId, int liveRoomId, boolean roomLiving,
        String clientIp) {
        // 打印用户加入房间的信息
        log.info("用户加入房间: socketId={}, 房间ID={}, 是否在直播={}, 客户端IP={}", socketId,
            liveRoomId, roomLiving, clientIp);
        // 如果房间正在直播，更新直播状态
        if (!roomLiving) {

        }
    }

}
