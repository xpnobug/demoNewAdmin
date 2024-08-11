package com.newadmin.demoservice.mainPro.livepro.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.demoservice.config.srs.annotation.SrsProperties;
import com.newadmin.demoservice.mainPro.livepro.model.entity.SrsRequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
public class SrsController {

    private final SrsProperties srsProperties;
    private final RestTemplate restTemplate;

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
    public JsonObject onPublish() {
//        getApiV1StreamsDetail(body.getStreamId());
        return new JsonObject(null, 0,
            "[on_publish] all success, pass");
    }

}
