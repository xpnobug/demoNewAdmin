package com.newadmin.demoservice.config.excel.converter;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ClassUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.newadmin.democore.constant.StringConstants;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.BaseEnum;

/**
 * Easy Excel 枚举接口转换器
 *
 * @author Charles7c
 * @see BaseEnum
 * @since 1.2.0
 */
public class ExcelBaseEnumConverter implements Converter<BaseEnum<Integer>> {

    @Override
    public Class<BaseEnum> supportJavaTypeKey() {
        return BaseEnum.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 转换为 Java 数据（读取 Excel）
     */
    @Override
    public BaseEnum convertToJavaData(ReadCellData<?> cellData,
        ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        return this.getEnum(BaseEnum.class, Convert.toStr(cellData.getData()));
    }

    /**
     * 转换为 Excel 数据（写入 Excel）
     */
    @Override
    public WriteCellData<String> convertToExcelData(BaseEnum<Integer> value,
        ExcelContentProperty contentProperty,
        GlobalConfiguration globalConfiguration) {
        if (null == value) {
            return new WriteCellData<>(StringConstants.EMPTY);
        }
        return new WriteCellData<>(value.getDescription());
    }

    /**
     * 通过 value 获取枚举对象，获取不到时为 {@code null}
     *
     * @param enumType    枚举类型
     * @param description 描述
     * @return 对应枚举 ，获取不到时为 {@code null}
     */
    private BaseEnum<Integer> getEnum(Class<?> enumType, String description) {
        Object[] enumConstants = enumType.getEnumConstants();
        for (Object enumConstant : enumConstants) {
            if (ClassUtil.isAssignable(BaseEnum.class, enumType)) {
                BaseEnum<Integer> baseEnum = (BaseEnum<Integer>) enumConstant;
                if (baseEnum.getDescription().equals(description)) {
                    return baseEnum;
                }
            }
        }
        return null;
    }
}
