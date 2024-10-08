package com.newadmin.demoservice.mainPro.colorGrad.controller;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demoservice.mainPro.colorGrad.entity.ColorGrad;
import com.newadmin.demoservice.mainPro.colorGrad.service.ColorGradService;
import java.io.Serializable;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author couei
 * @since 2024-04-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/colorGrad")
public class ColorGradController {

    private final ColorGradService colorGradService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public JsonPageObject selectPageAll(Page page) {
        return new JsonPageObject(page, colorGradService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public JsonObject selectOne(@PathVariable Serializable id) {
        return new JsonObject(colorGradService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param colorGrad 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public JsonObject insert(ColorGrad colorGrad) {
        colorGradService.insertColorGrad(colorGrad);
        return new JsonObject(true);
    }

    /**
     * 修改数据
     *
     * @param colorGrad 实体对象
     * @return 修改结果
     */
    @PutMapping
    public JsonObject update(@RequestBody ColorGrad colorGrad) {
        return new JsonObject(colorGradService.updateById(colorGrad));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public JsonObject delete(@RequestBody List<Long> idList) {
        return new JsonObject(colorGradService.removeByIds(idList));
    }
}
