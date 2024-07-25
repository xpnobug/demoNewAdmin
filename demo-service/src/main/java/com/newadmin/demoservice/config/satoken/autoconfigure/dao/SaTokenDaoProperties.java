package com.newadmin.demoservice.config.satoken.autoconfigure.dao;

import com.newadmin.demoservice.config.satoken.enums.SaTokenDaoType;

/**
 * SaToken 持久层配置属性
 */
public class SaTokenDaoProperties {

    /**
     * 持久层类型
     */
    private SaTokenDaoType type = SaTokenDaoType.DEFAULT;

    public SaTokenDaoType getType() {
        return type;
    }

    public void setType(SaTokenDaoType type) {
        this.type = type;
    }
}
