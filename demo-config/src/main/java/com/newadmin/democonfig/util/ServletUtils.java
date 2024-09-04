package com.newadmin.democonfig.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletUtils {

    private ServletUtils() {
    }

    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    public static String getBrowser(HttpServletRequest request) {
        return null == request ? null : getBrowser(request.getHeader("User-Agent"));
    }

    public static String getBrowser(String userAgentString) {
        UserAgent userAgent = UserAgentUtil.parse(userAgentString);
        String var10000 = userAgent.getBrowser().getName();
        return var10000 + " " + userAgent.getVersion();
    }

    public static String getOs(HttpServletRequest request) {
        return null == request ? null : getOs(request.getHeader("User-Agent"));
    }

    public static String getOs(String userAgentString) {
        UserAgent userAgent = UserAgentUtil.parse(userAgentString);
        return userAgent.getOs().getName();
    }

    public static Map<String, String> getHeaderMap(HttpServletResponse response) {
        Collection<String> headerNames = response.getHeaderNames();
        Map<String, String> headerMap = MapUtil.newHashMap(headerNames.size(), true);
        Iterator var3 = headerNames.iterator();

        while (var3.hasNext()) {
            String name = (String) var3.next();
            headerMap.put(name, response.getHeader(name));
        }

        return headerMap;
    }

    private static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) Objects.requireNonNull(
            RequestContextHolder.getRequestAttributes());
    }
}