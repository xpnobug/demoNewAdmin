
package com.newadmin.democonfig.util;

import java.lang.reflect.Method;

/**
 * 表达式上下文
 *
 * @author couei
 * @since 2.2.0
 */
public class ExpressionInvokeContext {

    /**
     * 目标方法
     */
    private Method method;

    /**
     * 方法参数
     */
    private Object[] args;

    /**
     * 目标对象
     */
    private Object target;

    public ExpressionInvokeContext(Method method, Object[] args, Object target) {
        this.method = method;
        this.args = args;
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
