package com.newadmin.demoservice.base.controller;

import com.newadmin.democonfig.mq.producer.MessageProducer;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "RabbitMQController", description = "RabbitMQController")
@RestController
public class RabbitMQController {

    @Autowired
    private MessageProducer messageProducer;

    @Schema(description = "发送消息到MQ")
    @PostMapping("/sendMQMessage")
    public String sendMQMessage(@RequestParam String content) {
        messageProducer.sendMessageToMQ(content);
        return "ok";
    }
}