package com.newadmin.demoservice.mainPro.chatai.entity;

import com.newadmin.demoservice.mainPro.chatai.domain.ChatGPTMsg;
import java.util.List;

// 请求体类，用于构建请求体
public class ChatRequest {

    private String model; // 使用的模型名称
    private List<ChatGPTMsg> messages; // 消息列表

    // 构造函数
    public ChatRequest(String model, List<ChatGPTMsg> messages) {
        this.model = model;
        this.messages = messages;
    }

    // 获取和设置方法
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChatGPTMsg> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatGPTMsg> messages) {
        this.messages = messages;
    }
}
