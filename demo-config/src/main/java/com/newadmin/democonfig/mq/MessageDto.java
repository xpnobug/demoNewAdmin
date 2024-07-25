package com.newadmin.democonfig.mq;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

@Data
public class MessageDto implements Serializable {

    @JsonProperty("content")
    private String content;
}
