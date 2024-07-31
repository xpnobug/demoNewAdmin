package com.newadmin.demoservice.mainPro.nas.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demoservice.mainPro.nas.dto.ItemIconDto;
import com.newadmin.demoservice.mainPro.nas.dto.SortRequest;
import com.newadmin.demoservice.mainPro.nas.entity.ItemIcon;
import com.newadmin.demoservice.mainPro.nas.entity.SiteInfo;
import com.newadmin.demoservice.mainPro.nas.entity.User;
import com.newadmin.demoservice.mainPro.nas.service.ItemIconService;
import com.newadmin.demoservice.mainPro.nas.service.UserService;
import com.newadmin.demoservice.util.SiteInfoUtil;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/panel/itemIcon")
public class ItemIconController {

    @Autowired
    private ItemIconService itemIconService;
    @Autowired
    private UserService baseService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @GetMapping("/pageList")
    public JsonPageObject selectPageAll(Page page) {
        return new JsonPageObject(page, itemIconService.list());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public JsonObject selectOne(@PathVariable Serializable id) {
        return new JsonObject(itemIconService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param itemIcon 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public JsonObject insert(@RequestBody ItemIcon itemIcon) {
        return new JsonObject(itemIconService.save(itemIcon));
    }

    /**
     * 修改数据
     *
     * @param itemIcon 实体对象
     * @return 修改结果
     */
    @PutMapping
    public JsonObject update(@RequestBody ItemIcon itemIcon) {
        return new JsonObject(itemIconService.updateById(itemIcon));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public JsonObject delete(@RequestBody List<Long> idList) {
        return new JsonObject(itemIconService.removeByIds(idList));
    }

    /**
     * 通过分组id查询所有图标
     *
     * @param itemIcon
     * @return
     */
    @PostMapping("/getListByGroupId")
    public JsonPageObject getListByGroupId(@RequestBody ItemIcon itemIcon, Page page,
        HttpServletRequest request) {
        User currentUserByToken = baseService.getCurrentUserByToken(request);
        QueryWrapper<ItemIcon> queryWrapper = new QueryWrapper<>();
        if (currentUserByToken == null) {
            queryWrapper.eq("item_icon_group_id", itemIcon.getItemIconGroupId());
            queryWrapper.eq("user_id", 1);
            queryWrapper.orderByAsc("sort");
            return new JsonPageObject(page, itemIconService.list(queryWrapper));
        } else {
            queryWrapper.eq("item_icon_group_id", itemIcon.getItemIconGroupId());
            queryWrapper.eq("user_id", currentUserByToken.getId());
            queryWrapper.orderByAsc("sort");
            List<ItemIcon> list = itemIconService.list(queryWrapper);
            return new JsonPageObject(page, list);
        }
    }

    @PostMapping("/edit")
    public JsonObject edit(@RequestBody ItemIconDto itemIconDto, HttpServletRequest request) {
        User currentUserByToken = baseService.getCurrentUserByToken(request);
        ItemIcon itemIcon = new ItemIcon();
        BeanUtils.copyProperties(itemIconDto, itemIcon);
        itemIcon.setUserId(currentUserByToken.getId());
        //转为json
        JSONObject entries = JSONUtil.parseObj(itemIconDto.getIcon());
        itemIcon.setIconJson(String.valueOf(entries));
        if (itemIcon.getId() == null) {
            return new JsonObject(itemIconService.save(itemIcon));
        } else {
            return new JsonObject(itemIconService.updateById(itemIcon));
        }
    }

    @PostMapping("/addMultiple")
    public JsonObject insertMultiple(@RequestBody List<ItemIconDto> itemIconDto,
        HttpServletRequest request) {
        User currentUserByToken = baseService.getCurrentUserByToken(request);
        List<ItemIcon> itemIcons = new ArrayList<>();
        for (ItemIconDto dto : itemIconDto) {
            ItemIcon itemIcon = new ItemIcon();
            BeanUtils.copyProperties(dto, itemIcon);
            itemIcon.setUserId(currentUserByToken.getId());
            //默认打开方式
            itemIcon.setOpenMethod(2);
            //转为json
            JSONObject entries = JSONUtil.parseObj(dto.getIcon());
            itemIcon.setIconJson(String.valueOf(entries));
            // 其他需要设置的属性
            itemIcons.add(itemIcon);
        }
        return new JsonObject(itemIconService.saveBatch(itemIcons));
    }

    @PostMapping("/saveSort")
    public JsonObject saveSort(@RequestBody SortRequest request) {
        ItemIcon[] sortItems = request.getSortItems();
        return new JsonObject(itemIconService.updateBatchById(Arrays.asList(sortItems)));
    }

    @PostMapping("/deletes")
    public JsonObject deletes(@RequestBody List<Long> idList) {
        return new JsonObject(itemIconService.removeByIds(idList));
    }

    /**
     * 根据url 获取网站基本信息 HDK
     *
     * @param request
     * @return
     */
    @PostMapping("/getSiteInfo")
    public JsonObject getSiteInfo(@RequestBody SiteInfo siteInfo, HttpServletRequest request) {
        //
        SiteInfo info = SiteInfoUtil.getSiteInfo(siteInfo.getUrl());
        return new JsonObject(info);
    }
}
