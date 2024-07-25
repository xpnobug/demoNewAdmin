package com.newadmin.demoservice.base.controller;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.newadmin.democommon.web.json.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ip")
public class IpController {

    @GetMapping("/getIp")
    public JsonObject getIp(HttpServletRequest request) {
        String clientIP = JakartaServletUtil.getClientIP(request);
        return new JsonObject(clientIP);
    }
}
