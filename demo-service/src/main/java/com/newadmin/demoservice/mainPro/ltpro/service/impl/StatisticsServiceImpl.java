package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import com.newadmin.demoservice.mainPro.ltpro.query.StatisticsQuery;
import com.newadmin.demoservice.mainPro.ltpro.service.StatisticsService;
import com.newadmin.demoservice.mainPro.ltpro.vo.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsQuery statisticsQuery;

    @Override
    public Statistics getStatistics(String userId) {
        //1.获取用户发布的文章数量
        long articleCount = statisticsQuery.getUserArticleCount(userId);
        //2.获取用户关注的用户数量
        long followCount = statisticsQuery.getUserFollowCount(userId);
        //3.获取用户的粉丝数量
        long followerCount = statisticsQuery.getUserFansCount(userId);

        //返回统计结果
        Statistics statistics = new Statistics();
        statistics.put(Statistics.PUBLISH_ARTICLE_COUNT, articleCount);
        statistics.put(Statistics.FOLLOW_COUNT, followCount);
        statistics.put(Statistics.FANS_COUNT, followerCount);

        return statistics;
    }

}
