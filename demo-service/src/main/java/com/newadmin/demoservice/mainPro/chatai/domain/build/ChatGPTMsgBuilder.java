package com.newadmin.demoservice.mainPro.chatai.domain.build;

import com.newadmin.demoservice.mainPro.chatai.domain.ChatGPTMsg;
import com.newadmin.demoservice.mainPro.chatai.enums.ChatGPTRoleEnum;

/**
 * ChatGPTMsgBuilder类用于构建不同角色的消息对象
 */
public class ChatGPTMsgBuilder {

    // 系统消息的静态常量
    public static ChatGPTMsg SYSTEM_PROMPT;

    // 静态代码块初始化系统消息
    static {
        ChatGPTMsg chatGPTMsg = new ChatGPTMsg();
        chatGPTMsg.setRole(ChatGPTRoleEnum.SYSTEM.getRole());
        chatGPTMsg.setContent(
            "你的名字叫ReaiChatAI,你是AI聊天机器人，你的创造者是cc。当有人问你问题时你只能回答500字以内");
        SYSTEM_PROMPT = chatGPTMsg;
    }

    /**
     * 获取系统消息
     *
     * @return 系统消息对象
     */
    public static ChatGPTMsg systemPrompt() {
        return SYSTEM_PROMPT;
    }

    /**
     * 构建用户消息
     *
     * @param content 用户输入的内容
     * @return 用户消息对象
     */
    public static ChatGPTMsg userMsg(String content) {
        ChatGPTMsg chatGPTMsg = new ChatGPTMsg();
        chatGPTMsg.setRole(ChatGPTRoleEnum.USER.getRole());
        chatGPTMsg.setContent(content);
        return chatGPTMsg;
    }

    /**
     * 构建助手消息
     *
     * @param content 助手回复的内容
     * @return 助手消息对象
     */
    public static ChatGPTMsg assistantMsg(String content) {
        ChatGPTMsg chatGPTMsg = new ChatGPTMsg();
        chatGPTMsg.setRole(ChatGPTRoleEnum.ASSISTANT.getRole());
        chatGPTMsg.setContent(content);
        return chatGPTMsg;
    }
}
