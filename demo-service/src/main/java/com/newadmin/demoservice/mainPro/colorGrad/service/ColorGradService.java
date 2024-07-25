package com.newadmin.demoservice.mainPro.colorGrad.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newadmin.demoservice.mainPro.colorGrad.entity.ColorGrad;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author couei
 * @since 2024-04-14
 */
public interface ColorGradService extends IService<ColorGrad> {

    void insertColorGrad(ColorGrad colorGrad);
}
