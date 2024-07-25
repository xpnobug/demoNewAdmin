
package com.newadmin.demoservice.config.captcha.graphic.autoconfigure;

import com.newadmin.demoservice.config.captcha.graphic.enums.GraphicCaptchaType;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("new-admin.captcha.graphic")
public class GraphicCaptchaProperties {

    private boolean enabled = true;
    private GraphicCaptchaType type;
    private int length;
    private int width;
    private int height;
    private String fontName;
    private int fontSize;

    public GraphicCaptchaProperties() {
        this.type = GraphicCaptchaType.SPEC;
        this.length = 4;
        this.width = 111;
        this.height = 36;
        this.fontSize = 25;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public GraphicCaptchaType getType() {
        return this.type;
    }

    public void setType(GraphicCaptchaType type) {
        this.type = type;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFontName() {
        return this.fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}
