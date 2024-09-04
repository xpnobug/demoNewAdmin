
package com.newadmin.democonfig.security.crypto.enums;

import com.newadmin.democonfig.security.crypto.encryptor.AesEncryptor;
import com.newadmin.democonfig.security.crypto.encryptor.Base64Encryptor;
import com.newadmin.democonfig.security.crypto.encryptor.DesEncryptor;
import com.newadmin.democonfig.security.crypto.encryptor.IEncryptor;
import com.newadmin.democonfig.security.crypto.encryptor.PbeWithMd5AndDesEncryptor;
import com.newadmin.democonfig.security.crypto.encryptor.RsaEncryptor;

/**
 * 加密/解密算法枚举
 *
 * @author couei
 * @since 1.4.0
 */
public enum Algorithm {

    /**
     * AES
     */
    AES(AesEncryptor.class),

    /**
     * DES
     */
    DES(DesEncryptor.class),

    /**
     * PBEWithMD5AndDES
     */
    PBEWithMD5AndDES(PbeWithMd5AndDesEncryptor.class),

    /**
     * RSA
     */
    RSA(RsaEncryptor.class),

    /**
     * Base64
     */
    BASE64(Base64Encryptor.class),
    ;

    /**
     * 加密/解密处理器
     */
    private final Class<? extends IEncryptor> encryptor;

    Algorithm(Class<? extends IEncryptor> encryptor) {
        this.encryptor = encryptor;
    }

    public Class<? extends IEncryptor> getEncryptor() {
        return encryptor;
    }
}
