package com.newadmin.demoservice.base.controller;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.demolog.log.core.annotation.Log;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log(ignore = true)
@RestController
@RequestMapping("/ip")
public class IpController {

    @GetMapping("/getIp")
    public JsonObject getIp(HttpServletRequest request) {
        String clientIP = JakartaServletUtil.getClientIP(request);
        return new JsonObject(clientIP);
    }
}
