package com.newadmin.demoservice.mainPro.mai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newadmin.demoservice.mainPro.mai.entity.ImgMai;

/**
 * @author 86136
 */
public interface ImgMaiService extends IService<ImgMai> {

    IPage<ImgMai> selectPage(Page<ImgMai> page, ImgMai imgMai);
}
