package com.newadmin.demoservice.config.satoken.autoconfigure;

/**
 * SaToken 安全配置属性
 */
public class SaTokenSecurityProperties {

    /**
     * 排除（放行）路径配置
     */
    private String[] excludes = new String[0];

    public String[] getExcludes() {
        return excludes;
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }
}