package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import com.newadmin.demoservice.mainPro.ltpro.service.ReaiArticleService;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiFollowService;
import com.newadmin.demoservice.mainPro.ltpro.service.StatisticsService;
import com.newadmin.demoservice.mainPro.ltpro.vo.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final ReaiArticleService articleService;
    private final ReaiFollowService followService;

    @Override
    public Statistics getStatistics(String userId) {
        //1.获取用户发布的文章数量
        int articleCount = articleService.articleList(userId).size();
        //2.获取用户关注的用户数量
        int followCount = followService.getFollowList(userId, null)
            .size();
        //3.获取用户的粉丝数量
        int followerCount = followService.getFollowList(null, userId).size();

        //返回统计结果
        Statistics statistics = new Statistics();
        statistics.put(Statistics.PUBLISH_ARTICLE_COUNT, articleCount);
        statistics.put(Statistics.FOLLOW_COUNT, followCount);
        statistics.put(Statistics.FANS_COUNT, followerCount);

        return statistics;
    }
}
