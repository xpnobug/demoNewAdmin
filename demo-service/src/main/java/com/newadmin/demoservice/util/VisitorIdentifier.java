package com.newadmin.demoservice.util;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 用于获取访问者的IP地址和User-Agent信息。
 */
public class VisitorIdentifier {

    // 获取客户端IP地址
    public static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            // 检查HTTP头中的X-FORWARDED-FOR，可能是经过代理的IP地址
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                // 如果没有经过代理，使用请求的远程地址
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    // 获取User-Agent
    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }
}
