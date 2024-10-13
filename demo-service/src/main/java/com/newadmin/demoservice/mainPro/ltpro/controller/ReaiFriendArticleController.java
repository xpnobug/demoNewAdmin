package com.newadmin.demoservice.mainPro.ltpro.controller;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiFriendArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "朋友圈管理 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/friend/article")
public class ReaiFriendArticleController {

    private final ReaiFriendArticleService friendArticleService;

    /**
     * 查询朋友圈动态
     *
     * @param page
     * @param userId
     * @return
     */
    @Operation(summary = "查询朋友圈动态", description = "查询朋友圈动态")
    @GetMapping("/friendCircleList")
    public JsonPageObject selectFriendCircleList(Page page, @RequestParam("userId") String userId) {
        return new JsonPageObject(page, friendArticleService.friendCircleList(page, userId));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @Operation(summary = "删除动态", description = "删除动态")
    @DeleteMapping("/delete")
    public JsonObject delete(@RequestParam String idList) {
        return new JsonObject(friendArticleService.deleteArticle(idList));
    }

}
