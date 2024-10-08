package com.newadmin.democonfig.exception;

import java.io.Serial;

/**
 * 自定义验证异常-错误请求
 *
 * @author couei
 * @since 1.0.0
 */
public class BadRequestException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
