package com.newadmin.demolog.log.httptrace.autoconfigure;

import com.newadmin.democonfig.constant.PropertiesConstants;
import com.newadmin.demolog.log.core.enums.Include;
import com.newadmin.demolog.log.util.SpringWebUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 日志配置属性
 *
 * @author couei
 * @author Charles7c
 * @since 1.1.0
 */
@ConfigurationProperties(PropertiesConstants.LOG)
public class LogProperties {

    /**
     * 是否启用日志
     */
    private boolean enabled = true;

    /**
     * 是否打印日志，开启后可打印访问日志（类似于 Nginx access log）
     * <p>
     * 不记录日志也支持开启打印访问日志
     * </p>
     */
    private Boolean isPrint = false;

    /**
     * 包含信息
     */
    private Set<Include> includes = Include.defaultIncludes();

    /**
     * 放行路由
     */
    private List<String> excludePatterns = new ArrayList<>();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(Boolean print) {
        isPrint = print;
    }

    public Set<Include> getIncludes() {
        return includes;
    }

    public void setIncludes(Set<Include> includes) {
        this.includes = includes;
    }

    public List<String> getExcludePatterns() {
        return excludePatterns;
    }

    public void setExcludePatterns(List<String> excludePatterns) {
        this.excludePatterns = excludePatterns;
    }

    /**
     * 是否匹配放行路由
     *
     * @param uri 请求 URI
     * @return 是否匹配
     */
    public boolean isMatch(String uri) {
        return this.getExcludePatterns().stream()
            .anyMatch(pattern -> SpringWebUtils.isMatch(pattern, uri));
    }
}
