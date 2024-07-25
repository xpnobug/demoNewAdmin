

package com.newadmin.demoservice.config.captcha.graphic.core;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ReflectUtil;
import com.newadmin.demoservice.config.captcha.graphic.autoconfigure.GraphicCaptchaProperties;
import com.wf.captcha.base.Captcha;
import java.awt.Font;

public class GraphicCaptchaService {

    private final GraphicCaptchaProperties properties;

    public GraphicCaptchaService(GraphicCaptchaProperties properties) {
        this.properties = properties;
    }

    public Captcha getCaptcha() {
        Captcha captcha = (Captcha) ReflectUtil.newInstance(
            this.properties.getType().getCaptchaImpl(),
            new Object[]{this.properties.getWidth(), this.properties.getHeight()});
        captcha.setLen(this.properties.getLength());
        if (CharSequenceUtil.isNotBlank(this.properties.getFontName())) {
            captcha.setFont(
                new Font(this.properties.getFontName(), 0, this.properties.getFontSize()));
        }

        return captcha;
    }
}
