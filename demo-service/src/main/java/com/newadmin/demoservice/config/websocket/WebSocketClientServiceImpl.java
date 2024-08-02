package com.newadmin.demoservice.config.websocket;

import com.newadmin.demoservice.config.websocket.core.WebSocketClientService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * 当前登录用户 Provider
 */
@Component
public class WebSocketClientServiceImpl implements WebSocketClientService {

    @Override
    public String getClientId(ServletServerHttpRequest request) {
        HttpServletRequest servletRequest = request.getServletRequest();
        String token = servletRequest.getParameter("token");
//        LoginUser loginUser = LoginHelper.getLoginUser(token);

        return "fixed-client-id"; // 或者 return null;
    }
}
