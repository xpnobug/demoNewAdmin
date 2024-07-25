package com.newadmin.demoservice.config.websocket;

import com.newadmin.demoservice.config.websocket.core.WebSocketClientService;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.dto.LoginUser;
import com.newadmin.demoservice.mainPro.ltpro.helper.LoginHelper;
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
        LoginUser loginUser = LoginHelper.getLoginUser(token);
        return token;
    }
}
