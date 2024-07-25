package com.newadmin.demoservice.mainPro.mai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newadmin.demoservice.mainPro.mai.entity.ImgMai;
import com.newadmin.demoservice.mainPro.mai.mapper.ImgMaiMapper;
import com.newadmin.demoservice.mainPro.mai.service.ImgMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (ImgMai)表服务实现类
 *
 * @author couei
 * @since 2024-03-04 21:04:48
 */
@Service("imgMaiService")
public class ImgMaiServiceImpl extends ServiceImpl<ImgMaiMapper, ImgMai> implements ImgMaiService {

    @Autowired
    private ImgMaiMapper imgMaiMapper;

    @Override
    public IPage<ImgMai> selectPage(Page<ImgMai> page, ImgMai imgMai) {
        //根据分类查询
        QueryWrapper<ImgMai> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cate_name", imgMai.getCateName());
        //根据时间
        queryWrapper.orderByDesc("created_at");
        return imgMaiMapper.selectPage(page, queryWrapper);
    }
}
