package com.newadmin.demoservice.mainPro.ltpro.controller;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiArticle;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiArticleService;
import com.newadmin.demoservice.mainPro.ltpro.vo.ReaiArticleParamVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "文章管理 API")
@RestController
@RequestMapping("/article")
public class ReaiArticleController {

    @Autowired
    private ReaiArticleService reaiArticleService;

    /**
     * 分页查询所有数据（首页）
     *
     * @return 所有数据
     */
    @Operation(summary = "分页查询文章数据", description = "分页查询文章数据")
    @GetMapping("/pageList")
    public JsonObject selectPageAll() {
        return new JsonObject(reaiArticleService.getArticleList());
    }

    /**
     * 分页查询所有数据（社区列表）
     *
     * @param page
     * @return
     */
    @Operation(summary = "分页查询文章数据（社区列表）", description = "分页查询文章数据（社区列表）")
    @GetMapping("/list")
    public JsonPageObject selectAllList(Page page) {
        return new JsonPageObject(page, reaiArticleService.selectAllList(page));
    }

    @Operation(summary = "根据用户id获取文章数据", description = "根据用户id获取文章数据")
    @GetMapping("/listByUserId")
    public JsonPageObject selectPageAll(Page page, @RequestParam("userId") String userId) {
        List<ReaiArticle> articleList = reaiArticleService.getArticleByUserId(page, userId);
        return new JsonPageObject(page, articleList);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条文章数据", description = "通过主键查询单条文章数据")
    @GetMapping("{id}")
    public JsonObject selectOne(@PathVariable String id) {
        return new JsonObject(reaiArticleService.getArticleInfo(id));
    }

    /**
     * 新增数据
     *
     * @param reaiArticle 实体对象
     * @return 新增结果
     */
    @Operation(summary = "发布文章", description = "发布文章")
    @PostMapping("/add")
    public JsonObject insert(@RequestBody ReaiArticleParamVo reaiArticle) {
        return new JsonObject(reaiArticleService.addArticle(reaiArticle));
    }

    /**
     * 修改数据
     *
     * @param reaiArticle 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改文章", description = "修改文章")
    @PutMapping("/update")
    public JsonObject update(@RequestBody ReaiArticle reaiArticle) {
        return new JsonObject(reaiArticleService.updateArticle(reaiArticle));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @Operation(summary = "删除文章", description = "删除文章")
    @DeleteMapping("/delete")
    public JsonObject delete(@RequestParam String idList) {
        return new JsonObject(reaiArticleService.deleteArticle(idList));
    }


}
