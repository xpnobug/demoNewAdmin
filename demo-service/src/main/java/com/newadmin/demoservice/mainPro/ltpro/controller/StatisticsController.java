package com.newadmin.demoservice.mainPro.ltpro.controller;

import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.demoservice.mainPro.ltpro.service.StatisticsService;
import com.newadmin.demoservice.mainPro.ltpro.vo.Statistics;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/getStatisticsById")
    public JsonObject getStatisticsById(String userId) {
        Statistics statistics = statisticsService.getStatistics(userId);
        return new JsonObject(statistics);
    }
}
