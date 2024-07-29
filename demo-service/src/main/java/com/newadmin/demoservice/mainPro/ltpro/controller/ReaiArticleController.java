package com.newadmin.demoservice.mainPro.ltpro.controller;

import com.newadmin.democommon.utils.Page;
import com.newadmin.democommon.web.json.JsonObject;
import com.newadmin.democommon.web.json.JsonPageObject;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiArticle;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiArticleService;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author couei
 * @since 2024-05-16
 */
@RestController
@RequestMapping("/article")
public class ReaiArticleController {

    @Autowired
    private ReaiArticleService reaiArticleService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public JsonObject selectPageAll() {
        return new JsonObject(reaiArticleService.getArticleList());
    }

    @GetMapping("/list")
    public JsonPageObject selectAllList(Page page) {
        return new JsonPageObject(page, reaiArticleService.selectAllList(page));
    }

    @GetMapping("/listByUserId")
    public JsonPageObject selectPageAll(Page page, @RequestParam("userId") String userId) {
        List<ReaiArticle> articleList = reaiArticleService.getArticleByUserId(userId);
        List<ReaiArticle> collect = articleList.stream()
            .skip((long) (page.getCurrentPage() - 1) * page.getPageSize())
            .limit(page.getPageSize()).
            toList();
        page.setCount(articleList.size());
        return new JsonPageObject(page, collect);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public JsonObject selectOne(@PathVariable Serializable id) {
        return new JsonObject(reaiArticleService.getArticleInfo(id));
    }

    /**
     * 新增数据
     *
     * @param reaiArticle 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public JsonObject insert(@RequestBody ReaiArticle reaiArticle) {

        ReaiArticle info = reaiArticleService.addArticle(reaiArticle);
        return new JsonObject(info.getArticleId());
    }

    /**
     * 修改数据
     *
     * @param reaiArticle 实体对象
     * @return 修改结果
     */
    @PutMapping("/update")
    public JsonObject update(@RequestBody ReaiArticle reaiArticle) {
//        return new JsonObject(reaiArticleService.updateById(reaiArticle));

        return new JsonObject(reaiArticleService.updateArticle(reaiArticle));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    public JsonObject delete(@RequestParam String idList) {
        return new JsonObject(reaiArticleService.deleteArticle(idList));
    }

    /**
     * 查询好友动态
     *
     * @param page
     * @param userId
     * @return
     */
    @GetMapping("/friendCircleList")
    public JsonPageObject selectFriendCircleList(Page page, @RequestParam("userId") String userId) {
        return new JsonPageObject(page, reaiArticleService.friendCircleList(page, userId));
    }

}
