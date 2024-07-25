package com.newadmin.demoservice.mainPro.chatai.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChatGPTModelEnum {
    GPT_3_5_TURBO("gpt-3.5-turbo", 60, 100000),
    GPT_4("gpt-4", 40, 80000),
    GPT_4_32K("gpt-4-32k", 20, 40000),
    GPT_4o("gpt-4o", 20, 40000);

    /**
     * 名字
     */
    private final String name;
    /**
     * 每分钟请求数
     */
    private final Integer RPM;
    /**
     * 每分钟令牌数
     */
    private final Integer TPM;
}

