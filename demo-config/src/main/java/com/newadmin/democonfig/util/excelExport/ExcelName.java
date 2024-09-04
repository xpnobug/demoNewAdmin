package com.newadmin.democonfig.util.excelExport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 86136 用于方法（METHOD）和字段（FIELD）上，并且在运行时（RUNTIME）保留。它有一个属性 name，默认为空字符串。
 */

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelName {

    /**
     * 处理 Excel 文件时，指定它们在 Excel 表格中的名称。通常情况下，通过将这个注解应用到代码中的方法或字段， 可以在处理 Excel 数据时，以某种方式将 Java 代码的元素与
     * Excel 表格中的对应元素关联起来
     *
     * @return
     */

    String name() default "";

}