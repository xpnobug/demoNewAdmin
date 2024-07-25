package com.newadmin.demoservice.mainPro.mai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newadmin.demoservice.mainPro.mai.entity.ImgMai;
import org.apache.ibatis.annotations.Param;

/**
 * (ImgMai)表数据库访问层
 *
 * @author couei
 * @since 2024-03-04 21:04:46
 */
public interface ImgMaiMapper extends BaseMapper<ImgMai> {

    IPage<ImgMai> selectPage(Page<ImgMai> page, @Param("imgMai") ImgMai imgMai);
}
