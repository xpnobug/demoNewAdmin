package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.demoservice.mainPro.ltpro.vo.Recommend;
import java.util.List;

public interface RecommendService {

    /**
     * 展示推荐模块 最近加入，最受欢迎， 最近活跃的用户
     */
    List<Recommend> showRecommend();

    List<Recommend> showChannel();
}
