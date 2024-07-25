package com.newadmin.demoservice.mainPro.chatai;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newadmin.demoservice.mainPro.chatai.domain.ChatGPTMsg;
import com.newadmin.demoservice.mainPro.chatai.domain.build.ChatGPTMsgBuilder;
import com.newadmin.demoservice.mainPro.chatai.entity.ChatGptResponse;
import com.newadmin.demoservice.mainPro.chatai.entity.ChatRequest;
import com.newadmin.demoservice.mainPro.chatai.enums.ChatGPTModelEnum;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatGptAiUtil {

    // 定义API密钥和URL
    private static final String API_KEY = "sk-2NVwIgo9JmVJUXSM0a7a48BeD28648Be9b95A82c64144fE6";
    private static final String API_URL = "https://api.sydney-ai.com/v1/chat/completions";  // 假设这是代理接口

    private static final Logger logger = LoggerFactory.getLogger(ChatGptAiUtil.class);

    public static void main(String[] args) {
        // 创建OkHttpClient实例
        OkHttpClient client = new OkHttpClient();
        // 创建Scanner实例用于获取用户输入
        Scanner scanner = new Scanner(System.in);
        // 创建ObjectMapper实例用于处理JSON序列化和反序列化
        ObjectMapper objectMapper = new ObjectMapper();

        // 使用ChatGPTMsgBuilder创建初始系统消息，系统消息用来定义AI助手的行为
        ChatGPTMsg systemMessage = ChatGPTMsgBuilder.systemPrompt();

        // 使用ArrayList创建消息列表并添加系统消息
        List<ChatGPTMsg> messages = new ArrayList<>();
        messages.add(systemMessage);

        // 开始对话循环
        chatLoop(client, scanner, objectMapper, messages);
    }

    /**
     * 对话循环方法
     */
    private static void chatLoop(OkHttpClient client, Scanner scanner, ObjectMapper objectMapper,
        List<ChatGPTMsg> messages) {
        while (true) {
            // 动态获取用户输入的提示
            System.out.print("请输入你的提示 (输入 '退出' 结束对话): ");
            String prompt = scanner.nextLine();

            // 检查用户是否输入 '退出'
            if (prompt.equalsIgnoreCase("退出")) {
                logger.info("对话已结束。");
                break;
            }

            // 使用ChatGPTMsgBuilder添加用户消息到消息列表
            ChatGPTMsg userMessage = ChatGPTMsgBuilder.userMsg(prompt);
            messages.add(userMessage);

            // 创建请求体
            ChatRequest chatRequest = new ChatRequest(ChatGPTModelEnum.GPT_3_5_TURBO.getName(),
                messages);

            try {
                // 序列化请求体为JSON
                String json = objectMapper.writeValueAsString(chatRequest);
                // 发送请求并处理响应
                sendRequest(client, json, objectMapper, messages);
            } catch (IOException e) {
                logger.error("JSON序列化失败: {}", e.getMessage());
            }
        }
    }

    /**
     * 发送请求并处理响应
     */
    private static String sendRequest(OkHttpClient client, String json, ObjectMapper objectMapper,
        List<ChatGPTMsg> messages) {
        // 创建RequestBody
        RequestBody body = RequestBody.create(json,
            MediaType.get("application/json; charset=utf-8"));

        // 构建HTTP请求
        Request request = new Request.Builder()
            .url(API_URL)
            .header("Authorization", "Bearer " + API_KEY)
            .post(body)
            .build();

        // 设置超时
        client = new OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.HOURS)
            .writeTimeout(1, TimeUnit.HOURS)
            .readTimeout(1, TimeUnit.HOURS)
            .build();

        // 发送请求并处理响应
        try (Response response = client.newCall(request).execute()) {
            // 检查响应是否成功
            if (!response.isSuccessful()) {
                logger.warn("Unexpected code: {}", response.code());
                logger.warn("Response body: {}", response.body().string());
                return null;
            }

            // 获取响应体
            String responseBody = response.body().string();

            // 检查响应体是否为空
            if (responseBody == null || responseBody.isEmpty()) {
                logger.warn("Response body is null or empty");
                return null;
            }

            // 解析JSON响应
            ChatGptResponse chatGptResponse = objectMapper.readValue(responseBody,
                ChatGptResponse.class);
            // 提取助手的回复内容
            String assistantReply = chatGptResponse.getChoices().get(0).getMessage().getContent();

            // 输出助手的回复
            System.out.println("Assistant: " + assistantReply);

            // 使用ChatGPTMsgBuilder添加AI助手的回复到消息列表
            ChatGPTMsg assistantMessage = ChatGPTMsgBuilder.assistantMsg(assistantReply);
            messages.add(assistantMessage);
            return assistantReply;
        } catch (IOException e) {
            if (e instanceof java.net.SocketTimeoutException) {
                logger.error("Request timed out: {}", e.getMessage());
            } else {
                logger.error("Request failed: {}", e.getMessage());
            }
        }

        return null;
    }

    public static String chatGptAi(String content) {
        // 创建OkHttpClient实例
        OkHttpClient client = new OkHttpClient();
        // 创建ObjectMapper实例用于处理JSON序列化和反序列化
        ObjectMapper objectMapper = new ObjectMapper();
        // 使用ChatGPTMsgBuilder创建初始系统消息，系统消息用来定义AI助手的行为
        ChatGPTMsg systemMessage = ChatGPTMsgBuilder.systemPrompt();
        // 使用ArrayList创建消息列表并添加系统消息
        List<ChatGPTMsg> messages = new ArrayList<>();
        messages.add(systemMessage);
        // 开始对话循环
        return chatSend(client, objectMapper, messages, content);
    }

    private static String chatSend(OkHttpClient client, ObjectMapper objectMapper,
        List<ChatGPTMsg> messages, String content) {
        // 使用ChatGPTMsgBuilder添加用户消息到消息列表
        ChatGPTMsg userMessage = ChatGPTMsgBuilder.userMsg(content);
        messages.add(userMessage);
        // 创建请求体
        ChatRequest chatRequest = new ChatRequest(ChatGPTModelEnum.GPT_3_5_TURBO.getName(),
            messages);

        try {
            // 序列化请求体为JSON
            String json = objectMapper.writeValueAsString(chatRequest);
            // 发送请求并处理响应
            return sendRequest(client, json, objectMapper, messages);
        } catch (IOException e) {
            logger.error("JSON序列化失败: {}", e.getMessage());
        }

        return null;
    }
}
