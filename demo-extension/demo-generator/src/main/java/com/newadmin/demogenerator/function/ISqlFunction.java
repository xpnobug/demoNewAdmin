package com.newadmin.demogenerator.function;

import java.io.Serializable;

/**
 * SQL 函数接口
 *
 * @author couei
 * @author Charles7c
 * @since 1.4.1
 */
public interface ISqlFunction {

    /**
     * find_in_set 函数
     *
     * @param value 值
     * @param set   集合
     * @return 函数实现
     */
    String findInSet(Serializable value, String set);
}
