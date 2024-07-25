package com.newadmin.demoservice.mainPro.colorGrad.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newadmin.demoservice.mainPro.colorGrad.dao.ColorGradMapper;
import com.newadmin.demoservice.mainPro.colorGrad.entity.ColorGrad;
import com.newadmin.demoservice.mainPro.colorGrad.service.ColorGradService;
import com.newadmin.demoservice.util.BookmarkParser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author couei
 * @since 2024-04-14
 */
@Service
public class ColorGradServiceImpl extends ServiceImpl<ColorGradMapper, ColorGrad> implements
    ColorGradService {

    @Autowired
    private ColorGradMapper colorGradMapper;

    @Override
    public void insertColorGrad(ColorGrad colorGrad) {
        List<ColorGrad> gradientColors = BookmarkParser.getGradientColors();
        for (ColorGrad gradientColor : gradientColors) {
            colorGradMapper.insertColorGrad(gradientColor);
        }
    }
}
