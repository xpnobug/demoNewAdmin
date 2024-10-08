package com.newadmin.demoservice.base.controller;

import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.demolog.log.core.annotation.Log;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiDictionaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author couei
 * @since 2024-06-10
 */
@Log(ignore = true)
@RestController
@RequestMapping("/dict")
public class ReaiDictionaryItemController {

    @Autowired
    private ReaiDictionaryItemService reaiDictionaryItemService;

    /**
     * 获取字典项
     * @param dictCode
     * @return
     */
    @GetMapping("/dictItem")
    public JsonObject getDictItem(@RequestParam String dictCode) {
        return new JsonObject(reaiDictionaryItemService.dictMap(dictCode));
    }

    /**
     * 获取字典项
     * @param itemCode
     * @return
     */
    @GetMapping("/getDictByItemCode")
    public JsonObject getDictByItemCode(@RequestParam String itemCode) {
        return new JsonObject(reaiDictionaryItemService.getDictByItemCode(itemCode));
    }
}
