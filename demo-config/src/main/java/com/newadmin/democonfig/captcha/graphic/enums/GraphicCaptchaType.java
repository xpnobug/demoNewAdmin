
package com.newadmin.democonfig.captcha.graphic.enums;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.ChineseGifCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;

public enum GraphicCaptchaType {
    ARITHMETIC(ArithmeticCaptcha.class),
    CHINESE(ChineseCaptcha.class),
    CHINESE_GIF(ChineseGifCaptcha.class),
    GIF(GifCaptcha.class),
    SPEC(SpecCaptcha.class);

    private final Class<? extends Captcha> captchaImpl;

    private GraphicCaptchaType(Class captchaImpl) {
        this.captchaImpl = captchaImpl;
    }

    public Class<? extends Captcha> getCaptchaImpl() {
        return this.captchaImpl;
    }
}
