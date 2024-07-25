package com.newadmin.demoservice.config.generaCode;

import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.io.File;
import java.util.Map;

/**
 * 对自定义的类处理，即other里生成的类
 *
 * @author 86136
 */
public class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    @Override
    protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo,
        Map<String, Object> objectMap) {

        // 可以调用 tableInfo 的getFieldNames方法获得所有的列
        this.printTableColumn(tableInfo);

        // objectMap 里的key可以在ftl文件中直接引用
        // https://copyfuture.com/blogs-details/20210404114118659h
        String entityName = tableInfo.getEntityName();
        String otherPath = this.getPathInfo(OutputFile.controller);
        customFile.forEach((key, value) -> {
            // 拼接路径
            String fileName = String.format(File.separator + entityName + "%s", key);
            System.out.println(fileName);
            this.outputFile(new File(fileName), objectMap, value);
        });
    }

    /**
     * 获得所有的表列名
     *
     * @param tableInfo 表信息
     */
    private void printTableColumn(TableInfo tableInfo) {
        System.out.println("所有的列名：" + tableInfo.getFieldNames());
    }
}
