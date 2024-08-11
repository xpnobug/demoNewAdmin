package com.newadmin.demoservice.mainPro.livepro.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.demoservice.config.srs.annotation.SrsProperties;
import com.newadmin.demoservice.mainPro.livepro.model.entity.PublishRequest;
import com.newadmin.demoservice.mainPro.livepro.model.entity.SrsRequestBody;
import com.newadmin.demoservice.mainPro.livepro.model.entity.UserLiveRoomDO;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveDetailResp;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveRoomDetailResp;
import com.newadmin.demoservice.mainPro.livepro.service.LiveService;
import com.newadmin.demoservice.mainPro.livepro.service.impl.LiveServiceImpl;
import com.newadmin.demoservice.mainPro.livepro.service.impl.SocketLiveImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
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
import org.ehcache.impl.internal.concurrent.ConcurrentHashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

/**
 * srs API
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
    private Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    @Operation(summary = "webRtc推流", description = "webRtc推流")
    @PostMapping("/rtcV1Publish")
    public JsonObject rtcV1Publish(
        @org.springframework.web.bind.annotation.RequestBody SrsRequestBody requestBody)
        throws IOException {
        // 获取推流地址和端口
        String pushUrl = srsProperties.getPullUrl();
        Integer post = srsProperties.getPost();

        // 构建请求 URL
        String url = String.format("http://182.92.201.19:1985/rtc/v1/publish/", pushUrl, post);

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

        // 执行请求并获取响应
        Response response = client.newCall(request).execute();

        // 获取响应体字符串
        String responseBody = response.body().string();

        // 使用 Jackson 解析响应体字符串为 JsonNode
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(responseBody);

        // 返回 JsonObject 响应
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

        // 执行请求并获取响应
        Response response = client.newCall(request).execute();

        // 获取响应体字符串
        String responseBody = response.body().string();

        // 使用 Jackson 解析响应体字符串为 JsonNode
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(responseBody);

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

        // 执行请求并获取响应
        Response response = client.newCall(request).execute();

        // 获取响应体字符串
        String responseBody = response.body().string();

        // 使用 Jackson 解析响应体字符串为 JsonNode
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(responseBody);

        // 返回 JsonObject 响应
        return new JsonObject(jsonNode);
    }

    @Operation(summary = "on_publish", description = "on_publish")
    @PostMapping("/on_publish")
    public JsonObject onPublish(
        @org.springframework.web.bind.annotation.RequestBody PublishRequest request) {
        // 记录请求日志
        logger.info("on_publish: {}", request);

        // 从request对象中提取相关字段
        String serverId = request.getServer_id();
        String serviceId = request.getService_id();
        String action = request.getAction();
        String clientId = request.getClient_id();
        String ip = request.getIp();
        String vhost = request.getVhost();
        String app = request.getApp();
        String tcUrl = request.getTcUrl();
        String stream = request.getStream();
        String param = request.getParam();
        String streamUrl = request.getStream_url();
        String streamId = request.getStream_id();

        // 提取直播房间ID，去除.m3u8后缀并匹配房间ID
        String roomIdStr = stream.replace(".m3u8", "");
        Matcher matcher = Pattern.compile("^roomId_(\\d+)$").matcher(roomIdStr);
        String roomId = null;
        if (matcher.find()) {
            roomId = matcher.group(1);  // 提取数字部分作为房间ID
        }
        logger.info("liveRoomId: {}", roomId);

        // 查询当前房间ID的用户直播信息
        ValueMap params = new ValueMap();
        params.put("liveRoomId", roomId);
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef("user_live_room"))
            .where()
            .and("live_room_id", ConditionType.EQUALS, UserLiveRoomDO.LIVE_ROOM_ID);
        UserLiveRoomDO userLiveRoom = super.getForBean(selectBuilder.build(), UserLiveRoomDO::new);

        SelectBuilder liveSelectBuilder = new SelectBuilder(params);
        liveSelectBuilder.from("", super.getEntityDef("live"))
            .where()
            .and("live_room_id", ConditionType.EQUALS, UserLiveRoomDO.LIVE_ROOM_ID);
        LiveDetailResp liveInfo = super.getForBean(liveSelectBuilder.build(), LiveDetailResp::new);

        // 创建并初始化直播详细信息响应对象
        LiveDetailResp liveDetailResp = new LiveDetailResp();
        liveDetailResp.setUserId(userLiveRoom.getUserId());
        liveDetailResp.setLiveRoomId(roomId);
        liveDetailResp.setSocketId("-1"); // 默认Socket ID
        liveDetailResp.setTrackAudio(1); // 音频轨道设置
        liveDetailResp.setTrackVideo(1); // 视频轨道设置
        liveDetailResp.setFlagId(clientId);

        // 设置SRS服务器相关信息
        liveDetailResp.setSrsServerId(serverId);
        liveDetailResp.setSrsServiceId(serviceId);
        liveDetailResp.setSrsAction(action);
        liveDetailResp.setSrsClientId(clientId);
        liveDetailResp.setSrsIp(ip);
        liveDetailResp.setSrsVhost(vhost);
        liveDetailResp.setSrsApp(app);
        liveDetailResp.setSrsTcurl(tcUrl);
        liveDetailResp.setSrsStream(stream);
        liveDetailResp.setSrsParam(param);
        liveDetailResp.setSrsStreamUrl(streamUrl);
        liveDetailResp.setSrsStreamId(streamId);

        // 设置创建和更新时间
        liveDetailResp.put("updatedTime", new Date());
        liveDetailResp.put("createdTime", new Date());

        String liveId = null;
        // 如果直播信息不存在，添加新的记录，否则更新现有记录
        if (liveInfo == null) {
            liveId = liveService.add(liveDetailResp);
        } else {
            liveDetailResp.put("id", liveInfo.getId());
            super.update(LiveServiceImpl.TABLE_NAME, liveDetailResp);
        }

        // 获取或缓存房间详细信息
        LiveRoomDetailResp detail = null;
        if (roomId != null) {
            detail = socketLive.getCachedOrFetchRoomDetail(roomId);
        }

        // 获取 WebSocketSession
        WebSocketSession session = sessions.get("roomId");
        logger.info("session: {}", session);
        if (session != null && session.isOpen()) {
            logger.info("session is open");
            // 创建并发送房间正在直播的消息
            ValueMap living = new ValueMap();
            living.put("anchor_socket_id", ""); // 主播的Socket ID
            living.put("live_room", detail); // 房间详细信息

            try {
                String livingJson = OBJECT_MAPPER.writeValueAsString(living); // 转换为JSON字符串
                String roomLiving = "42[\"roomLiving\", " + livingJson + "]"; // 构造消息内容
                socketLive.sendMessage(session, roomLiving); // 通过WebSocket发送消息
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e); // 捕获并抛出JSON处理异常
            }
        }
        // 返回成功响应
        return new JsonObject(liveId, 0, "[on_publish] all success, pass");
    }

}
