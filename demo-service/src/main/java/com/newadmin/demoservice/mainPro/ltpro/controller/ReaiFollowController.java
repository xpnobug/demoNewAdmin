package com.newadmin.demoservice.mainPro.ltpro.controller;

import com.newadmin.democommon.web.json.JsonObject;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiFollow;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author couei
 * @since 2024-05-25
 */
@RestController
@RequestMapping("/follow")
public class ReaiFollowController {

    @Autowired
    private ReaiFollowService reaiFollowService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
//    @GetMapping("/pageList")
//    public JsonPageObject selectPageAll(Page page) {
//        return new JsonPageObject(page, reaiFollowService.list());
//    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
//    public JsonObject selectOne(@PathVariable Serializable id) {
//        return new JsonObject(reaiFollowService.getById(id));
//    }

    /**
     * 新增数据
     *
     * @param reaiFollow 实体对象
     * @return 新增结果
     */
    @PostMapping("/followUser")
    public JsonObject insert(@RequestBody ReaiFollow reaiFollow) {
        String add = reaiFollowService.add(reaiFollow);
        return new JsonObject(add, 200, "关注成功！");
    }

    /**
     * 修改数据
     *
     * @param reaiFollow 实体对象
     * @return 修改结果
     */
//    @PutMapping
//    public JsonObject update(@RequestBody ReaiFollow reaiFollow) {
//        return new JsonObject(reaiFollowService.updateById(reaiFollow));
//    }

    /**
     * 删除数据
     * @return 删除结果
     */
    @DeleteMapping("/clearFollow")
    public JsonObject delete(String id) {
        return new JsonObject(reaiFollowService.delById(id));
    }
}
