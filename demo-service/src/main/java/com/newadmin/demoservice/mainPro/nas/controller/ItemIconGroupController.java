package com.newadmin.demoservice.mainPro.nas.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demoservice.mainPro.nas.dto.SortItemsRequest;
import com.newadmin.demoservice.mainPro.nas.entity.ItemIconGroup;
import com.newadmin.demoservice.mainPro.nas.entity.User;
import com.newadmin.demoservice.mainPro.nas.service.ItemIconGroupService;
import com.newadmin.demoservice.mainPro.nas.service.impl.BaseServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2024-03-31
 */
@RestController
@RequestMapping("/panel/itemIconGroup")
public class ItemIconGroupController {

    @Autowired
    private ItemIconGroupService itemIconGroupService;
    @Autowired
    private BaseServiceImpl baseService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @PostMapping("/getList")
    public JsonPageObject selectPageAll(Page page, HttpServletRequest request) {
        User currentUserByToken = baseService.getCurrentUserByToken(request);
        QueryWrapper<ItemIconGroup> queryWrapper = new QueryWrapper<>();

        //如果是访问者，则查询管理人开放的所有数据
        if (currentUserByToken == null) {
            List<ItemIconGroup> list = itemIconGroupService.lambdaQuery()
                .eq(ItemIconGroup::getUserId, 1)
                .ne(ItemIconGroup::getIsShow, 1)
                .orderByAsc(ItemIconGroup::getSort)
                .list();
//            queryWrapper.orderByAsc("sort");
//            queryWrapper.eq("user_id", 1);
            //筛选is_show不为1的数据
//            queryWrapper.ne("is_show", 1);
            return new JsonPageObject(page, list);
        } else {
            //根据sort进行排序
            queryWrapper.orderByAsc("sort");
            queryWrapper.eq("user_id", currentUserByToken.getId());
            queryWrapper.ne("is_show", 1);
            return new JsonPageObject(page, itemIconGroupService.list(queryWrapper));
        }
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public JsonObject selectOne(@PathVariable Serializable id) {
        return new JsonObject(itemIconGroupService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param itemIconGroup 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public JsonObject insert(@RequestBody ItemIconGroup itemIconGroup) {
        return new JsonObject(itemIconGroupService.save(itemIconGroup));
    }

    /**
     * 修改数据
     *
     * @param itemIconGroup 实体对象
     * @return 修改结果
     */
    @PutMapping
    public JsonObject update(@RequestBody ItemIconGroup itemIconGroup) {
        return new JsonObject(itemIconGroupService.updateById(itemIconGroup));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public JsonObject delete(@RequestBody List<Long> idList) {
        return new JsonObject(itemIconGroupService.removeByIds(idList));
    }

    /**
     * 通过主键修改数据
     *
     * @param itemIconGroup
     * @return
     */
    @PostMapping("/edit")
    public JsonObject edit(@RequestBody ItemIconGroup itemIconGroup, HttpServletRequest request) {

        // 获取当前登录用户信息
        User currentUserByToken = baseService.getCurrentUserByToken(request);
        if (itemIconGroup.getId() == null) {
            // 添加
            itemIconGroup.setUserId(currentUserByToken.getId());
            itemIconGroup.setIsShow(2);
            itemIconGroupService.save(itemIconGroup);
            // 获取添加之后的id
            Long generatedId = itemIconGroup.getId();
            return new JsonObject(itemIconGroupService.getById(generatedId));

        } else {
            return new JsonObject(itemIconGroupService.updateById(itemIconGroup));
        }
    }

    /**
     * 排序
     *
     * @param request
     * @return
     */
    @PostMapping("/saveSort")
    public JsonObject saveSort(@RequestBody SortItemsRequest request) {
        ItemIconGroup[] sortItems = request.getSortItems();
        return new JsonObject(itemIconGroupService.updateBatchById(Arrays.asList(sortItems)));
    }

    @PostMapping("/deletes")
    public JsonObject deletes(@RequestBody List<Long> ids) {
        return new JsonObject(itemIconGroupService.removeByIds(ids));
    }
}
