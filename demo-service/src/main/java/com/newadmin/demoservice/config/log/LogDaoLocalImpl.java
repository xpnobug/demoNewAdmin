package com.newadmin.demoservice.config.log;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.newadmin.democore.constant.StringConstants;
import com.newadmin.democore.util.ExceptionUtils;
import com.newadmin.democore.util.StrUtils;
import com.newadmin.demolog.log.core.dao.LogDao;
import com.newadmin.demolog.log.core.model.LogRecord;
import com.newadmin.demolog.log.core.model.LogRequest;
import com.newadmin.demolog.log.core.model.LogResponse;
import com.newadmin.demoservice.config.trace.TraceProperties;
import com.newadmin.demoservice.mainPro.ltpro.auth.model.req.AccountLoginReq;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.LogStatusEnum;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.SysConstants;
import com.newadmin.demoservice.mainPro.ltpro.entity.LogDO;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiUsersService;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import net.dreamlu.mica.core.result.R;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;

/**
 * 日志持久层接口本地实现类
 *
 * @author Charles7c
 * @since 2023/12/16 23:55
 */
@RequiredArgsConstructor
public class LogDaoLocalImpl implements LogDao {

    private final ReaiUsersService userService;
    private final TraceProperties traceProperties;

    @Async
    @Override
    public void add(LogRecord logRecord) {
        LogDO logDO = new LogDO();
        logDO.setDescription(logRecord.getDescription());
        String module = logRecord.getModule();
        logDO.setModule(
            StrUtils.blankToDefault(module, null,
                m -> m.replace("API", StringConstants.EMPTY).trim()));
        logDO.setCreateTime(
            LocalDateTime.ofInstant(logRecord.getTimestamp(), ZoneId.systemDefault()));
        logDO.setTimeTaken(logRecord.getTimeTaken().toMillis());
        // 请求信息
        LogRequest logRequest = logRecord.getRequest();
        logDO.setRequestMethod(logRequest.getMethod());
        URI requestUrl = logRequest.getUrl();
        String requestUri = requestUrl.getPath();
        logDO.setRequestUrl(requestUrl.toString());
        Map<String, String> requestHeaderMap = logRequest.getHeaders();
        logDO.setRequestHeaders(JSONUtil.toJsonStr(requestHeaderMap));
        String requestBody = logRequest.getBody();
        logDO.setRequestBody(requestBody);
        logDO.setIp(logRequest.getIp());
        logDO.setAddress(logRequest.getAddress());
        logDO.setBrowser(logRequest.getBrowser());
        logDO.setOs(StrUtil.subBefore(logRequest.getOs(), " or", false));
        // 响应信息
        LogResponse logResponse = logRecord.getResponse();
        Integer statusCode = logResponse.getStatus();
        logDO.setStatusCode(statusCode);
        Map<String, String> responseHeaders = logResponse.getHeaders();
        logDO.setResponseHeaders(JSONUtil.toJsonStr(responseHeaders));
        logDO.setTraceId(responseHeaders.get(traceProperties.getHeaderName()));
        String responseBody = logResponse.getBody();
        logDO.setResponseBody(responseBody);
        // 状态
        logDO.setStatus(statusCode >= HttpStatus.HTTP_BAD_REQUEST ? LogStatusEnum.FAILURE
            : LogStatusEnum.SUCCESS);
        if (StrUtil.isNotBlank(responseBody) && JSONUtil.isTypeJSON(responseBody)) {
            R result = JSONUtil.toBean(responseBody, R.class);
            if (!result.isSuccess()) {
                logDO.setStatus(LogStatusEnum.FAILURE);
                logDO.setErrorMsg(result.getMsg());
            }
            // 操作人
            if (requestUri.startsWith(SysConstants.LOGOUT_URI)) {
                Long loginId = Convert.toLong(result.getData(), -1L);
                logDO.setCreateUser(String.valueOf(-1 != loginId ? loginId : null));
            } else if (result.isSuccess() && requestUri.startsWith(SysConstants.LOGIN_URI)) {
                AccountLoginReq loginReq = JSONUtil.toBean(requestBody, AccountLoginReq.class);
                logDO.setCreateUser(
                    ExceptionUtils.exToNull(() -> userService.getByUsername(loginReq.getUsername())
                        .getUserId()));
            }
        }
        // 操作人
        String headerName = HttpHeaders.AUTHORIZATION;
        boolean isContains = CollUtil.containsAny(requestHeaderMap.keySet(),
            Set.of(headerName, headerName
                .toLowerCase()));
        if (!requestUri.startsWith(SysConstants.LOGOUT_URI) && MapUtil.isNotEmpty(requestHeaderMap)
            && isContains) {
            String authorization = requestHeaderMap.getOrDefault(headerName,
                requestHeaderMap.get(headerName
                    .toLowerCase()));
            String token = authorization.replace(SaManager.getConfig()
                .getTokenPrefix() + StringConstants.SPACE, StringConstants.EMPTY);
            logDO.setCreateUser(String.valueOf(Convert.toLong(StpUtil.getLoginIdByToken(token))));
        }
//        logMapper.insert(logDO);
    }
}
