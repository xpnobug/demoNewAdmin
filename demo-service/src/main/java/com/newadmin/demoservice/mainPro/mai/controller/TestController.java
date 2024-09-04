package com.newadmin.demoservice.mainPro.mai.controller;

import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democonfig.util.excelExport.export.MyCsvFileUtil;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiDictionaryItem;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiDictionaryItemService;
import com.newadmin.demoservice.mainPro.mai.entity.ImgMai;
import com.newadmin.demoservice.mainPro.mai.service.ImgMaiService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 86136
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final ImgMaiService imgMaiService;
    private final ReaiDictionaryItemService dictionaryItemService;

    @RequestMapping("/hello")
    public JsonObject hello() {
        return new JsonObject();
    }

    /**
     * 万能 Excel 导出工具
     *
     * @param imgMai
     * @return
     */
    @GetMapping("/expoort")
    public JsonObject export(ImgMai imgMai) {
        //根据字段顺序查询
//        List<ImgMai> imgMaisList = imgMaiService.list(new QueryWrapper<>(imgMai));
        List<ReaiDictionaryItem> dictionaryItemList = dictionaryItemService.getDictionaryItemList(
            "home");
        //存放地址
        String filePath = "D:\\笔记\\" + MyCsvFileUtil.buildCsvFileFileName(dictionaryItemList);
        //创建表格行标题
        //判断如果类中没有设置字段名称
        String customTableName = MyCsvFileUtil.buildCsvFileTableNamesNew(
            MyCsvFileUtil.resolveExcelTableName(dictionaryItemList.get(0)));
//        String tableName = MyCsvFileUtil.buildCsvFileTableNames(imgMaisList);

        //创建文件
        MyCsvFileUtil.writeFile(filePath, customTableName);
        //写入数据
        String content = MyCsvFileUtil.buildCsvFileBodyMap(dictionaryItemList);
        //生成
        MyCsvFileUtil.writeFile(filePath, content);
        return new JsonObject("导出成功");
    }
}
