package com.newadmin.demoservice.mainPro.livepro.model.entity;

import lombok.Data;

@Data
public class SrsRequestBody {

    // 推流 API 标识
    private String api;

    // 客户端 IP 地址
    private String clientip;

    // SDP 信息
    private String sdp;

    // 推流地址
    private String streamurl;

    // 会话 ID
    private String tid;
}
