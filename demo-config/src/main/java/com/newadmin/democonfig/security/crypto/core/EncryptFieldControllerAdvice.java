package com.newadmin.democonfig.security.crypto.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 控制器增强，处理字段加密
 */
@ControllerAdvice
public class EncryptFieldControllerAdvice {

    @Autowired
    private EncryptFieldValidator encryptFieldValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(encryptFieldValidator);
    }
}
