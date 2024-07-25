package com.newadmin.demoservice.mainPro.chatai.entity;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

// ChatGPT响应类，表示API响应的结构
@Getter
@Setter
public class ChatGptResponse {

    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;

    // Getters 和 Setters

    // Choice 类，表示每个选择
    @Getter
    @Setter
    public static class Choice {

        private int index;
        private Message message;
        private String finish_reason;

        // Getters 和 Setters
    }

    // Message 类，表示每条消息
    @Getter
    @Setter
    public static class Message {

        private String role;
        private String content;

        // Getters 和 Setters
    }

    // Usage 类，表示使用情况
    @Getter
    @Setter
    public static class Usage {

        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;

        // Getters 和 Setters
    }
}
