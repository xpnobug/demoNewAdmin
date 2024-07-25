package com.newadmin.demoservice.mainPro.chatai.domain;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatGPTMsg implements Serializable {

    private String role;

    private String content;

}
