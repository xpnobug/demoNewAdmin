package com.newadmin.demoservice.mainPro.livepro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newadmin.democonfig.redisCommon.util.RedisUtils;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveDetailResp;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveResp;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveRoomDetailResp;
import com.newadmin.demoservice.mainPro.livepro.service.LiveRoomService;
import com.newadmin.demoservice.mainPro.livepro.service.LiveService;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.query.UserInfoQuery;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
@RequiredArgsConstructor
public class SocketLiveImpl {

    private final ReaiUsersService usersService;
    private final LiveRoomService liveRoomService;
    private final LiveService liveService;

    public void handleMethod(TextMessage message, WebSocketSession session) throws IOException {
        String payload = message.getPayload();
        LiveResp liveResp = new LiveResp();
        // 如果消息格式不正确，跳过处理
        if (payload.contains("[")) {
            String jsonString = payload.replaceAll("^\\d+", "").trim();
            JSONArray jsonArray = JSONArray.parseArray(jsonString);
            JSONObject json = jsonArray.getJSONObject(1);
            JSONObject data = json.getJSONObject("data");
            liveResp = BeanUtil.copyProperties(data, LiveResp.class);
            liveResp.put("browserUrl", json.get("browser_url"));
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode rootNode = objectMapper.readTree(jsonString);
//            JsonNode jsonNode = rootNode.get(1);
//            JsonNode dataNode = jsonNode.path("data");
//            // 以字符串形式获取 liveRoomId
//            String liveRoomId = dataNode.path("liveRoomId").asText();
//            liveResp = BeanUtil.copyProperties(dataNode, LiveResp.class);

            if (jsonArray.size() != 2) {
                return; // 如果不是 ["",{}] 格式，跳过处理
            }
        }
        //获取json第0 个元素
        if (payload.contains("join")) {
            payload = "join";
        }
        if (payload.contains("heartbeat")) {
            payload = "heartbeat";
        }
        if (payload.contains("srsCandidate")) {
            payload = "srsCandidate";
        }
        if (payload.contains("roomNoLive")) {
            payload = "roomNoLive";
        }
        handleMessage(payload, session, liveResp);

    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(); // 创建 ObjectMapper 实例

    public static String generateRandomSid() {
        // 生成随机的 UUID
        UUID uuid = UUID.randomUUID();
        // 将 UUID 转换为字符串，并去掉中间的短横线
        String sid = uuid.toString().replace("-", "");
        return sid;
    }

    private void handleMessage(String payload, WebSocketSession session, LiveResp liveResp)
        throws IOException {
        switch (payload) {
            case "heartbeat":
                // 处理心跳消息
                break;
            case "40":
                // 处理类型为 40 的消息
                sendMessage(session, "40{\n\"sid\": \"" + session.getId() + "\"\n}");
                break;
            case "join":
                // 处理用户加入房间的消息
                handleJoinMessage(session, liveResp);
                break;
//            case "srsCandidate":
//                // 处理开始直播的消息
//                handleStartLiveMessage(session, liveResp);
//                break;
            case "roomNoLive":
                // 处理房间没有直播的消息
                handleNoLiveMessage(session, liveResp);
                break;
            default:
                // 处理未知类型的消息
                break;
        }
    }

    private void handleNoLiveMessage(WebSocketSession session, LiveResp liveResp)
        throws IOException {
//        JSONObject liveRoom = (JSONObject) liveResp.get("live_room");
//        String id = liveRoom.get("id").toString();
        sendMessage(session, "42[\"roomNoLive\"]");
//        liveService.updateLiveRoom(id, "roomNoLive");
    }

//    private void handleStartLiveMessage(WebSocketSession session, LiveResp liveResp)
//        throws IOException {
//        // 格式化 Redis 缓存的键
////        LiveDetailResp liveInfo = getCachedOrFetchLiveDetail(liveResp.getLiveRoomId()); // 获取或缓存直播信息
////        ReaiUsers user = getCachedOrFetchUserInfo(liveInfo.getUserId()); // 获取或缓存用户信息
//        LiveRoomDetailResp detail = getCachedOrFetchRoomDetail(
//            liveResp.getLiveRoomId()); // 获取或缓存房间详细信息
//
//        // 创建并发送房间正在直播的消息
//        ValueMap living = new ValueMap();
//        living.put("anchor_socket_id", liveResp.getLiveRoomId()); // 主播 Socket ID
//        living.put("live_room", detail); // 房间详细信息
//        String livingJson = OBJECT_MAPPER.writeValueAsString(living); // 转换为 JSON 字符串
//        String roomLiving = "42[\"roomLiving\", " + livingJson + "]"; // 构造消息
////        liveService.updateLiveRoom(detail.getId(), session.getId());
//        sendMessage(session, roomLiving); // 发送消息
//    }

    private void handleJoinMessage(WebSocketSession session, LiveResp liveResp) throws IOException {
        // 格式化 Redis 缓存的键
        LiveDetailResp liveInfo = getCachedOrFetchLiveDetail(liveResp.getLiveRoomId()); // 获取或缓存直播信息
        if (liveInfo != null) {
            ReaiUsers user = getCachedOrFetchUserInfo(liveInfo.getUserId()); // 获取或缓存用户信息
            LiveRoomDetailResp detail = getCachedOrFetchRoomDetail(
                liveResp.getLiveRoomId()); // 获取或缓存房间详细信息

            // 创建并发送用户加入房间的消息
            UserInfoQuery userInfo = BeanUtil.copyProperties(user, UserInfoQuery.class); // 转换用户信息
            ValueMap valueMap = new ValueMap();
            valueMap.put("anchor_info", userInfo); // 主播信息
            valueMap.put("live_room", detail); // 房间详细信息
            valueMap.put("live_room_id", liveResp.getLiveRoomId()); // 房间 ID
            valueMap.put("socket_id", liveResp.getSocketId()); // Socket ID
            String userInfoJson = OBJECT_MAPPER.writeValueAsString(valueMap); // 转换为 JSON 字符串
            String joined = "42[\"joined\", " + userInfoJson + "]"; // 构造消息
            sendMessage(session, joined); // 发送消息

            // 处理特殊情况：浏览器 URL 包含 "push"
            String browserUrl = getBrowserUrlFromLiveResp(liveResp); // 获取浏览器 URL
            if (browserUrl.contains("pull")) {
                // 创建并发送房间正在直播的消息
                ValueMap living = new ValueMap();
                living.put("anchor_socket_id", liveResp.getLiveRoomId()); // 主播 Socket ID
                living.put("live_room", detail); // 房间详细信息
                living.put("socket_list", Arrays.asList(liveResp.getSocketId())); // Socket ID 列表
                String livingJson = OBJECT_MAPPER.writeValueAsString(living); // 转换为 JSON 字符串
                String roomLiving = "42[\"roomLiving\", " + livingJson + "]"; // 构造消息
                sendMessage(session, roomLiving); // 发送消息
            }
        }
    }

    // 从 Redis 中获取或缓存房间详细信息
    private LiveDetailResp getCachedOrFetchLiveDetail(String liveRoomId) {
        if (!liveRoomId.isEmpty()) {
            String liveInfoKey = RedisUtils.formatKey("liveInfo", liveRoomId);
            LiveDetailResp liveInfo = RedisUtils.get(liveInfoKey);
            if (liveInfo == null) {
                liveInfo = liveService.getDetail(liveRoomId);
                RedisUtils.set(liveInfoKey, liveInfo, Duration.ofMinutes(10)); // 设置缓存有效期为10分钟
            }
            return liveInfo;
        }
        return null;
    }

    // 从 Redis 中获取或缓存用户信息
    private ReaiUsers getCachedOrFetchUserInfo(String userId) {
        String userInfoKey = RedisUtils.formatKey("userInfo", userId);
        ReaiUsers user = RedisUtils.get(userInfoKey);
        if (user == null) {
            user = usersService.getUserInfo(userId);
            RedisUtils.set(userInfoKey, user, Duration.ofMinutes(10)); // 设置缓存有效期为10分钟
        }
        return user;
    }

    // 从 Redis 中获取或缓存房间详细信息
    public LiveRoomDetailResp getCachedOrFetchRoomDetail(String liveRoomId) {
        if (!liveRoomId.isEmpty()) {
            String liveRoomDetailKey = RedisUtils.formatKey("liveRoomDetail", liveRoomId);
            LiveRoomDetailResp detail = RedisUtils.get(liveRoomDetailKey);
            if (detail == null) {
                detail = liveRoomService.getDetail(0, liveRoomId);
                RedisUtils.set(liveRoomDetailKey, detail, Duration.ofMinutes(10)); // 设置缓存有效期为10分钟
            }
            return detail;
        }
        return null;
    }

    // 从 LiveResp 中获取浏览器 URL
    private String getBrowserUrlFromLiveResp(LiveResp liveResp) {
        Object browserUrlObject = liveResp.get("browserUrl");
        return (browserUrlObject != null) ? browserUrlObject.toString() : "";
    }

    // 发送 WebSocket 消息
    public void sendMessage(WebSocketSession session, String message) {
        try {
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
