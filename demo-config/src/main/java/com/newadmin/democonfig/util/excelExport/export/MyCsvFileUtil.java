package com.newadmin.democonfig.util.excelExport.export;

import com.newadmin.democonfig.util.excelExport.ExcelName;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

/**
 * @author 86136
 */
@Slf4j
public class MyCsvFileUtil {

    public static final String FILE_SUFFIX = ".csv";
    public static final String CSV_DELIMITER = ",";
    public static final String CSV_TAIL = "\r\n";
    protected static final String DATE_STR_FILE_NAME = "yyyyMMddHHmmssSSS";

    /**
     * 将字符串转成csv文件
     */
    public static void createCsvFile(String savePath, String contextStr) throws IOException {

        File file = new File(savePath);
        //创建文件
        file.createNewFile();
        //创建文件输出流
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        //将指定字节写入此文件输出流
        fileOutputStream.write(contextStr.getBytes("gbk"));
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    /**
     * 写文件
     *
     * @param fileName
     * @param content
     */
    public static void writeFile(String fileName, String content) {
        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        try {
            fos = new FileOutputStream(fileName, true);
            writer = new OutputStreamWriter(fos, "GBK");
            writer.write(content);
            writer.flush();
        } catch (Exception e) {
            log.error("写文件异常|{}", e);
        } finally {
            if (fos != null) {
                IOUtils.closeQuietly(fos);
            }
            if (writer != null) {
                IOUtils.closeQuietly(writer);
            }
        }
    }

    /**
     * 构建文件名称
     *
     * @param dataList
     * @return
     */
    public static String buildCsvFileFileName(List dataList) {
        return dataList.get(0).getClass().getSimpleName() + new SimpleDateFormat(
            DATE_STR_FILE_NAME).format(new Date()) + FILE_SUFFIX;
    }

    /**
     * 构建excel 标题行名
     *
     * @param dataList
     * @return
     */
    public static String buildCsvFileTableNames(List dataList) {
        Map<String, Object> map = toMap(dataList.get(0));
        StringBuilder tableNames = new StringBuilder();
        for (String key : map.keySet()) {
            tableNames.append(key).append(MyCsvFileUtil.CSV_DELIMITER);
        }
        return tableNames.append(MyCsvFileUtil.CSV_TAIL).toString();
    }

    /**
     * 构建excel内容
     *
     * @param dataLists
     * @return
     */
    public static String buildCsvFileBodyMap(List dataLists) {
        //不管你传什么玩意，我都给你反射一手，搞成Map
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Object o : dataLists) {
            Map<String, Object> map = toMap(o);
            mapList.add(new LinkedHashMap<>(map));
        }
        //然后利用csv格式，对着map嘎嘎一顿拼接数据
        StringBuilder lineBuilder = new StringBuilder();
        for (Map<String, Object> rowData : mapList) {
            for (String key : rowData.keySet()) {
                Object value = rowData.get(key);
                if (Objects.nonNull(value)) {
                    //判断字段数据中是否存在 ,
                    //如果存在, 则用""将字段值包裹起来
                    if (value.toString().contains(MyCsvFileUtil.CSV_DELIMITER)) {
                        //双引号转义
                        value = "\"" + value + "\"";
                    }
                    lineBuilder.append(value).append(MyCsvFileUtil.CSV_DELIMITER);
                } else {
                    lineBuilder.append("--").append(MyCsvFileUtil.CSV_DELIMITER);
                }
            }
            lineBuilder.append(MyCsvFileUtil.CSV_TAIL);
        }
        return lineBuilder.toString();
    }

    /**
     * 类转map
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> toMap(T entity) {
        Class<? extends Object> bean = entity.getClass();
        Field[] fields = bean.getDeclaredFields();
        //HashMap 不保留插入顺序，而 LinkedHashMap 会按照元素插入的顺序来维护键值对的顺序。
        //LinkedHashMap 内部使用双向链表来维护元素的顺序，而 HashMap 则使用哈希表来存储键值对
        Map<String, Object> map = new LinkedHashMap<>(fields.length);
        for (Field field : fields) {
            try {
                if (!"serialVersionUID".equals(field.getName())) {
                    String methodName =
                        "get" + field.getName().substring(0, 1).toUpperCase() + field.getName()
                            .substring(1);
                    Method method = bean.getDeclaredMethod(methodName);
                    Object fieldValue = method.invoke(entity);
                    map.put(field.getName(), fieldValue);
                }
            } catch (Exception e) {
                log.warn("toMap() Exception={}", e.getMessage());
            }
        }
        return map;
    }

    /**
     * 新的反射解析拿字段属性注解值函数：
     *
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> List<String> resolveExcelTableName(T entity) {
        List<String> tableNamesList = new ArrayList<>();
        Class<? extends Object> bean = entity.getClass();
        Field[] fields = bean.getDeclaredFields();
        Map<String, Object> map = new HashMap<>(fields.length);
        for (Field field : fields) {
            try {
                if (!"serialVersionUID".equals(field.getName())) {
                    String tableTitleName = field.getName();
                    ExcelName myFieldAnn = field.getAnnotation(ExcelName.class);
                    String annName = myFieldAnn.name();
                    if (StringUtils.hasLength(annName)) {
                        tableTitleName = annName;
                    }
                    tableNamesList.add(tableTitleName);
                }
            } catch (Exception e) {
                String tableTitleName = field.getName();
                tableNamesList.add(tableTitleName);
                log.warn("toMap() Exception={}", e.getMessage());
            }
        }
        return tableNamesList;
    }

    /**
     * 根据解析出来的注解值列名拼接 表格标题名格式
     *
     * @param dataList
     * @return
     */
    public static String buildCsvFileTableNamesNew(List<String> dataList) {
        StringBuilder tableNames = new StringBuilder();
        for (String name : dataList) {
            tableNames.append(name).append(MyCsvFileUtil.CSV_DELIMITER);
        }
        return tableNames.append(MyCsvFileUtil.CSV_TAIL).toString();
    }
}