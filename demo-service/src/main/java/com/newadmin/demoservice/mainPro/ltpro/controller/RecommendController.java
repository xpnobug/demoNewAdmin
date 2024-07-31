package com.newadmin.demoservice.mainPro.ltpro.controller;

import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.demoservice.mainPro.ltpro.service.RecommendService;
import com.newadmin.demoservice.mainPro.ltpro.vo.Recommend;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    @GetMapping("/recommendList")
    public JsonObject list() {
        List<Recommend> reaiUsers = recommendService.showRecommend();
        return new JsonObject(reaiUsers);
    }
}
