package com.newadmin.demoservice.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.newadmin.demoservice.config.generaCode.EnhanceFreemarkerTemplateEngine;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 86136
 */
public class CodeGeneration {

    public static void main(String[] args) {
        generation("demo_newpro",
            new String[]{"live_room"});
    }

    /**
     * 根据表名生成相应结构代码
     *
     * @param databaseName 数据库名
     * @param tableName    表名
     */
    public static void generation(String databaseName, String... tableName) {
        FastAutoGenerator.create("jdbc:mysql://182.92.201.19:3306/" + databaseName
                    + "?&useSSL=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",
                "root", "1919101871.Cxp")
            .globalConfig(builder -> {
                builder.author("couei")
                    //启用swagger
                    //.enableSwagger()
                    //禁止打开输出目录
                    .disableOpenDir()
                    //指定输出目录
                    .outputDir(System.getProperty("user.dir") + "/demo-service/src/main/java");
            })
            .packageConfig(builder -> {
                builder.entity("entity")//实体类包名
                    .parent(
                        "com.newadmin.demoservice.mainPro.livepro")//父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
                    .controller("controller")//控制层包名
                    .mapper("dao")
                    //mapper层包名
                    //.other("dto")//生成dto目录 可不用
                    .service("service")//service层包名
                    .serviceImpl("service.impl");//service实现类包名
                //自定义mapper.xml文件输出目录
//                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml,
//                        System.getProperty("user.dir")
//                            + "/demo-service/src/main/resources/mappers/"));
            })
            .strategyConfig(builder -> {
                //设置要生成的表名
                builder.addInclude(tableName)
                    //.addTablePrefix("sys_")//设置表前缀过滤
                    .entityBuilder()
                    .enableLombok()
                    .enableChainModel()
                    .naming(
                        NamingStrategy.underline_to_camel)//数据表映射实体命名策略：默认下划线转驼峰underline_to_camel
                    .columnNaming(
                        NamingStrategy.underline_to_camel)//表字段映射实体属性命名规则：默认null，不指定按照naming执行
                    .idType(IdType.AUTO)//添加全局主键类型
                    .formatFileName("%s")//格式化实体名称，%s取消首字母I,
                    .mapperBuilder()
                    .enableMapperAnnotation()//开启mapper注解
                    .enableBaseResultMap()//启用xml文件中的BaseResultMap 生成
                    .enableBaseColumnList()//启用xml文件中的BaseColumnList
                    .formatMapperFileName("%sMapper")//格式化Dao类名称
                    .formatXmlFileName("%sMapper")//格式化xml文件名称
                    .serviceBuilder()
                    .formatServiceFileName("%sService")//格式化 service 接口文件名称
                    .formatServiceImplFileName("%sServiceImpl")//格式化 service 接口文件名称
                    .controllerBuilder()
                    .enableRestStyle();
            })
            .injectionConfig(consumer -> {
                Map<String, String> customFile = new HashMap<>();
                // 配置自定义文件，key是文件名（不包含后缀），value是文件路径（相对于resources）
                customFile.put("controller", "templates/controller.java.ftl");

                consumer.customFile(customFile);
            })
            // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .templateEngine(new EnhanceFreemarkerTemplateEngine())
            .execute();
    }
}