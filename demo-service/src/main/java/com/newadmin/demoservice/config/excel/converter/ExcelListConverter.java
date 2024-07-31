package com.newadmin.demoservice.config.excel.converter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.newadmin.democore.constant.StringConstants;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Easy Excel List 集合转换器
 *
 * <p>
 * 仅适合 List<基本类型> <=> xxx,xxx 转换
 * </p>
 *
 * @author Charles7c
 * @since 2.0.2
 */
@Component
public class ExcelListConverter implements Converter<List> {

    @Override
    public Class supportJavaTypeKey() {
        return List.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public List convertToJavaData(ReadCellData<?> cellData,
        ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        String stringValue = cellData.getStringValue();
        return StrUtil.split(stringValue, StringConstants.COMMA);
    }

    @Override
    public WriteCellData<Object> convertToExcelData(List value,
        ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        WriteCellData<Object> writeCellData = new WriteCellData<>(
            CollUtil.join(value, StringConstants.COMMA));
        writeCellData.setType(CellDataTypeEnum.STRING);
        return writeCellData;
    }
}
