package com.newadmin.demoservice.mainPro.colorGrad.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newadmin.demoservice.mainPro.colorGrad.entity.ColorGrad;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author couei
 * @since 2024-04-14
 */
@Mapper
public interface ColorGradMapper extends BaseMapper<ColorGrad> {

    void insertColorGrad(ColorGrad colorGrad);
}
