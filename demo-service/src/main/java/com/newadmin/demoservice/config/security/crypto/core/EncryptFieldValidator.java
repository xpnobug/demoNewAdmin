package com.newadmin.demoservice.config.security.crypto.core;

import com.newadmin.demoservice.config.security.crypto.annotation.FieldEncrypt;
import com.newadmin.demoservice.config.security.crypto.autoconfigure.CryptoProperties;
import com.newadmin.demoservice.config.security.crypto.encryptor.IEncryptor;
import com.newadmin.demoservice.config.security.crypto.encryptor.RsaEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.lang.reflect.Field;

/**
 * 自定义验证器，用于处理字段加密
 */
@Component
public class EncryptFieldValidator implements Validator {

    @Autowired
    private CryptoProperties properties;

    @Autowired
    private IEncryptor encryptor;

    @Override
    public boolean supports(Class<?> clazz) {
        return true; // 适用于所有类
    }

    @Override
    public void validate(Object target, Errors errors) {
        encryptFields(target);
    }

    private void encryptFields(Object object) {
        if (object == null) {
            return;
        }

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FieldEncrypt.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(object);
                    if (value != null) {
//                        FieldEncrypt annotation = field.getAnnotation(FieldEncrypt.class);
//                        String password = annotation.password().isEmpty() ? properties.getPassword() : annotation.password();
                        String encryptedValue = encryptor.encrypt(value.toString(), null,
                            properties.getPublicKey());
                        System.out.println(
                            "Encrypting field: " + field.getName() + ", Original value: " + value
                                + ", Encrypted value: " + encryptedValue);
                        field.set(object, encryptedValue);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
