package com.newadmin.demoservice.mainPro.livepro.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.util.validate.CheckUtils;
import com.newadmin.demoservice.config.srs.annotation.SrsProperties;
import com.newadmin.demoservice.config.websocket.dao.WebSocketSessionDao;
import com.newadmin.demoservice.mainPro.livepro.model.entity.PublishRequest;
import com.newadmin.demoservice.mainPro.livepro.model.entity.SrsRequestBody;
import com.newadmin.demoservice.mainPro.livepro.model.entity.UserLiveRoomDO;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveDetailResp;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveResp;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveRoomDetailResp;
import com.newadmin.demoservice.mainPro.livepro.service.LiveService;
import com.newadmin.demoservice.mainPro.livepro.service.impl.LiveServiceImpl;
import com.newadmin.demoservice.mainPro.livepro.service.impl.SocketLiveImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

/**
 * SRS API控制器，负责处理SRS相关的API请求 主要涉及WebRTC推流、拉流和流的发布与取消发布
 *
 * @author couei
 * @since 2024/08/05 21:29
 */
@Tag(name = "直播间管理 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/srs")
public class SrsController extends DefaultService {

    private final SrsProperties srsProperties;
    private final LiveService liveService;
    private final SocketLiveImpl socketLive;

    private static final Logger logger = LogManager.getLogger(SrsController.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(); // 创建 ObjectMapper 实例
    private static final WebSocketSessionDao SESSION_DAO = SpringUtil.getBean(
        WebSocketSessionDao.class);

    @Operation(summary = "WebRTC推流", description = "处理WebRTC推流的请求")
    @PostMapping("/rtcV1Publish")
    public JsonObject rtcV1Publish(
        @org.springframework.web.bind.annotation.RequestBody SrsRequestBody requestBody)
        throws IOException {
        // 获取推流地址和端口
        String pushUrl = srsProperties.getPullUrl();
        Integer post = srsProperties.getPost();

        // 构建请求 URL
        String url = String.format("https://srs-pull.reaicc.com/rtc/v1/publish/", pushUrl, post);

        // 使用 OkHttpClient 发送 POST 请求
        JsonNode jsonNode = getJsonNode(requestBody, url);

        // 返回 JSON 响应
        return new JsonObject(jsonNode);
    }

    @Operation(summary = "webRtc拉流", description = "webRtc拉流")
    @PostMapping("/rtcV1Play")
    public JsonObject rtcV1Play(
        @org.springframework.web.bind.annotation.RequestBody SrsRequestBody requestBody)
        throws IOException {
        // 获取推流地址和端口
        String pushUrl = srsProperties.getPullUrl();
        Integer post = srsProperties.getPost();

        // 构建请求 URL
        String url = String.format("http://182.92.201.19:1985/rtc/v1/play/", pushUrl, post);

        // 构建 OkHttpClient
        JsonNode jsonNode = getJsonNode(requestBody, url);

        // 返回 JsonObject 响应
        return new JsonObject(jsonNode);
    }

    @GetMapping("/getApiV1StreamsDetail")
    public JsonObject getApiV1StreamsDetail(
        @org.springframework.web.bind.annotation.RequestBody SrsRequestBody requestBody)
        throws IOException {
        // 获取推流地址和端口
        String pushUrl = srsProperties.getPullUrl();
        Integer post = srsProperties.getPost();

        // 构建请求 URL
        String url = String.format("http://182.92.201.19:1985/api/v1/streams/", pushUrl, post);

        JsonNode jsonNode = getJsonNode(requestBody, url);

        // 返回 JsonObject 响应
        return new JsonObject(jsonNode);
    }

    /**
     * 获取json
     *
     * @param requestBody
     * @param url
     * @return
     * @throws IOException
     */
    private static JsonNode getJsonNode(SrsRequestBody requestBody, String url) throws IOException {
        // 构建 OkHttpClient
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // 设置请求体的媒体类型为 JSON
        MediaType mediaType = MediaType.parse("application/json");

        // 将 requestBody 转换为 JSON 字符串
        String jsonBody = new Gson().toJson(requestBody);

        // 创建请求体
        RequestBody body = RequestBody.create(mediaType, jsonBody);

        // 构建请求对象
        Request request = new Request.Builder()
            .url(url)
            .post(body) // 使用 POST 方法
            .addHeader("Content-Type", "application/json")
            .build();

        // 获取响应并解析为 JsonNode
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        return OBJECT_MAPPER.readTree(responseBody);
    }

    // 处理推流开始的请求
    @Operation(summary = "on_publish", description = "处理推流开始")
    @PostMapping("/on_publish")
    public JsonObject onPublish(
        @org.springframework.web.bind.annotation.RequestBody PublishRequest request) {
        logRequest("on_publish", request); // 记录请求日志

        String roomId = extractRoomId(request.getStream()); // 提取房间ID

        // 查询用户直播房间信息
        UserLiveRoomDO userLiveRoom = queryLiveRoomInfo(roomId, "user_live_room",
            UserLiveRoomDO::new);
        // 查询直播房间详细信息
        LiveDetailResp liveInfo = queryLiveRoomInfo(roomId, "live", LiveDetailResp::new);

        // 初始化直播详细信息
        LiveDetailResp liveDetailResp = initializeLiveDetail(request, roomId, userLiveRoom);

        // 更新或创建直播信息
        String liveId = updateOrCreateLiveInfo(liveInfo, liveDetailResp);

        // 通知房间正在直播
        notifyRoomLiving(roomId, liveDetailResp.getFlagId());

        // 返回成功响应
        return new JsonObject(liveId, 0, "[on_publish] all success, pass");
    }

    @Operation(summary = "处理停止推流的请求", description = "处理停止推流")
    @PostMapping("/on_unpublish")
    public JsonObject onUnpublish(
        @org.springframework.web.bind.annotation.RequestBody PublishRequest request) {
        logRequest("on_unpublish", request); // 记录请求日志

        String roomId = extractRoomId(request.getStream()); // 提取房间ID
        logger.info("liveRoomId: {}", roomId); // 打印房间ID日志

        // 验证房间ID和推流token是否有效
        if (!isValidRoom(roomId, request.getParam())) {
            return new JsonObject(0, 1, "[on_unpublish] fail, invalid room or token");
        }

        // 删除直播信息
        deleteLiveInfo(roomId);

        // 通知房间直播结束
        notifyRoomNoLive();

        // 返回成功响应
        return new JsonObject(0, 0, "[on_unpublish] success");
    }

    @Operation(summary = "处理拉流请求", description = "处理拉流")
    @PostMapping("/on_play")
    public JsonObject onPlay(
        @org.springframework.web.bind.annotation.RequestBody PublishRequest request) {
        logRequest("on_play", request); // 记录请求日志

        Instant startTime = Instant.now(); // 记录请求开始时间
        String roomId = extractRoomId(request.getStream()); // 提取房间ID

        // 验证房间ID是否存在
        if (!StringUtils.hasText(roomId)) {
            CheckUtils.throwIfNull(roomId, "房间id不存在");
            return new JsonObject(0, 1,
                "[on_play] fail, duration: " + duration(startTime) + " , roomId is not exist");
        }

        // 返回成功响应
        logger.info("[on_play] 耗时：{}，房间id：{}，所有验证通过，允许拉流", duration(startTime),
            roomId);

        return new JsonObject(0, 0,
            "[on_play] duration: " + duration(startTime) + ", all success, pass");
    }

    @Operation(summary = "处理停止拉流的请求", description = "处理停止拉流")
    @PostMapping("/on_stop")
    public JsonObject onStop(
        @org.springframework.web.bind.annotation.RequestBody PublishRequest request) {
        logRequest("on_stop", request); // 记录请求日志

        String roomId = extractRoomId(request.getStream()); // 提取房间ID

        // 验证房间ID是否存在
        if (!isValidRoom(roomId, request.getParam())) {
            return new JsonObject(0, 1, "[on_stop] fail, roomId is not exist");
        }

        // 返回成功响应
        return new JsonObject(0, 0, "[on_stop] all success, pass");
    }

    // 解析查询字符串为 Map
    private static Map<String, String> parseQueryString(String query) {
        Map<String, String> params = new HashMap<>();
        if (query == null || query.isEmpty()) {
            return params;
        }
        String[] pairs = query.substring(1).split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                params.put(
                    URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8),
                    URLDecoder.decode(keyValue[1],
                        StandardCharsets.UTF_8));
            }
        }
        return params;
    }

    // 记录请求日志
    private void logRequest(String action, PublishRequest request) {
        logger.info("{}: {}", action, request);
    }

    // 提取并验证直播房间ID
    private String extractRoomId(String stream) {
        Matcher matcher = Pattern.compile("^roomId_(\\d+)$").matcher(stream.replace(".m3u8", ""));
        return matcher.find() ? matcher.group(1) : null;
    }

    // 查询直播房间信息
    private <T> T queryLiveRoomInfo(String roomId, String tableName, Function<Map, T> bean) {
        ValueMap params = new ValueMap();
        params.put("liveRoomId", roomId);
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef(tableName)).where()
            .and("live_room_id", ConditionType.EQUALS, UserLiveRoomDO.LIVE_ROOM_ID);
        return super.getForBean(selectBuilder.build(), bean);
    }

    // 初始化直播详细信息
    private LiveDetailResp initializeLiveDetail(PublishRequest request, String roomId,
        UserLiveRoomDO userLiveRoom) {
        LiveDetailResp liveDetailResp = new LiveDetailResp();
        liveDetailResp.setUserId(userLiveRoom.getUserId()); // 设置用户ID
        liveDetailResp.setLiveRoomId(roomId); // 设置房间ID
        liveDetailResp.setSocketId("-1"); // 默认Socket ID
        liveDetailResp.setTrackAudio(1); // 音频轨道设置
        liveDetailResp.setTrackVideo(1); // 视频轨道设置
        liveDetailResp.setFlagId(request.getClient_id()); // 设置客户端ID

        // 设置SRS服务器相关信息
        setSrsInfo(liveDetailResp, request);
        // 设置创建和更新时间
        setTimestamps(liveDetailResp);

        return liveDetailResp;
    }

    // 设置SRS服务器相关信息
    private void setSrsInfo(LiveDetailResp liveDetailResp, PublishRequest request) {
        liveDetailResp.setSrsServerId(request.getServer_id()); // 设置SRS服务器ID
        liveDetailResp.setSrsServiceId(request.getService_id()); // 设置SRS服务ID
        liveDetailResp.setSrsAction(request.getAction()); // 设置SRS动作
        liveDetailResp.setSrsClientId(request.getClient_id()); // 设置SRS客户端ID
        liveDetailResp.setSrsIp(request.getIp()); // 设置SRS IP
        liveDetailResp.setSrsVhost(request.getVhost()); // 设置SRS虚拟主机
        liveDetailResp.setSrsApp(request.getApp()); // 设置SRS应用
        liveDetailResp.setSrsTcurl(request.getTcUrl()); // 设置SRS TCURL
        liveDetailResp.setSrsStream(request.getStream()); // 设置SRS流名称
        liveDetailResp.setSrsParam(request.getParam()); // 设置SRS参数
        liveDetailResp.setSrsStreamUrl(request.getStream_url()); // 设置SRS流URL
        liveDetailResp.setSrsStreamId(request.getStream_id()); // 设置SRS流ID
    }

    // 设置创建和更新时间
    private void setTimestamps(LiveDetailResp liveDetailResp) {
        liveDetailResp.put("updatedTime", new Date()); // 设置更新时间
        liveDetailResp.put("createdTime", new Date()); // 设置创建时间
    }

    // 更新或创建直播信息
    private String updateOrCreateLiveInfo(LiveDetailResp liveInfo, LiveDetailResp liveDetailResp) {
        if (liveInfo == null) {
            return liveService.add(liveDetailResp); // 如果信息不存在，添加新信息
        } else {
            liveDetailResp.put("id", liveInfo.getId()); // 如果信息存在，更新信息
            super.update(LiveServiceImpl.TABLE_NAME, liveDetailResp);
            return liveInfo.getId();
        }
    }

    // 通知房间正在直播
    private void notifyRoomLiving(String roomId, String clientId) {
        LiveRoomDetailResp detail = socketLive.getCachedOrFetchRoomDetail(roomId); // 获取或缓存房间详细信息
        WebSocketSession session = SESSION_DAO.get("66666"); // 获取WebSocket会话
        logger.info("session: {}", session);

        if (session != null && session.isOpen()) { // 如果会话有效且已打开
            ValueMap living = new ValueMap();
            living.put("anchor_socket_id", clientId); // 设置主播Socket ID
            living.put("live_room", detail); // 设置房间详细信息

            try {
                String livingJson = OBJECT_MAPPER.writeValueAsString(living); // 转换为JSON字符串
                String roomLiving = "42[\"roomLiving\", " + livingJson + "]"; // 构造消息内容
                socketLive.sendMessage(session, roomLiving); // 通过WebSocket发送消息
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e); // 捕获并抛出JSON处理异常
            }
        }
    }

    // 验证房间和推流token
    private boolean isValidRoom(String roomId, String param) {
        if (!StringUtils.hasText(roomId)) {
            return false;
        }

        Map<String, String> params = parseQueryString(param);
        String pushKey = params.get("pushkey");
        if (!StringUtils.hasText(pushKey)) {
            return false;
        }

        ValueMap liveRoomParam = new ValueMap();
        liveRoomParam.put(LiveRoomDetailResp.ID, roomId); // 房间id
        SelectBuilder liveRoomSelectBuilder = new SelectBuilder(liveRoomParam);
        liveRoomSelectBuilder.from("", super.getEntityDef("live_room"))
            .where().and("id", ConditionType.EQUALS, LiveRoomDetailResp.ID);
        LiveRoomDetailResp liveRoomInfo = super.getForBean(liveRoomSelectBuilder.build(),
            LiveRoomDetailResp::new);

        return liveRoomInfo != null && liveRoomInfo.getSecretKey().equals(pushKey);
    }

    // 删除直播信息
    private void deleteLiveInfo(String roomId) {
        super.delete("live", LiveResp.LIVE_ROOM_ID, new String[]{roomId});
    }

    // 通知房间直播结束
    private void notifyRoomNoLive() {
        WebSocketSession session = SESSION_DAO.get("66666");
        logger.info("session: {}", session);
        if (session != null && session.isOpen()) {
            socketLive.sendMessage(session, "42[\"roomNoLive\"]");
        }
    }

    // 计算持续时间
    private long duration(Instant startTime) {
        return Duration.between(startTime, Instant.now()).toMillis();
    }
}
