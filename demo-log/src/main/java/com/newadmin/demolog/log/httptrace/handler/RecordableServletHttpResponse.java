package com.newadmin.demolog.log.httptrace.handler;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.newadmin.democonfig.constant.StringConstants;
import com.newadmin.democonfig.util.ServletUtils;
import com.newadmin.demolog.log.core.model.RecordableHttpResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

/**
 * 可记录的 HTTP 响应信息适配器
 *
 * @author Andy Wilkinson（Spring Boot Actuator）
 * @author couei
 * @author Charles7c
 */
public final class RecordableServletHttpResponse implements RecordableHttpResponse {

    private final HttpServletResponse response;

    private final int status;

    public RecordableServletHttpResponse(HttpServletResponse response, int status) {
        this.response = response;
        this.status = status;
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public Map<String, String> getHeaders() {
        return ServletUtils.getHeaderMap(response);
    }

    @Override
    public String getBody() {
        ContentCachingResponseWrapper wrapper = WebUtils
            .getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (null != wrapper) {
            return StrUtil.utf8Str(wrapper.getContentAsByteArray());
        }
        return StringConstants.EMPTY;
    }

    @Override
    public Map<String, Object> getParam() {
        String body = this.getBody();
        return CharSequenceUtil.isNotBlank(body) && JSONUtil.isTypeJSON(body) ? JSONUtil.toBean(
            body, Map.class) : null;
    }
}
