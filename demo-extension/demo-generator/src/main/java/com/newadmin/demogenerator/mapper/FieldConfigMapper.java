package com.newadmin.demogenerator.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newadmin.demogenerator.model.entity.FieldConfigDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 字段配置 Mapper
 *
 * @author couei
 * @author Charles7c
 * @since 2023/4/12 23:56
 */
public interface FieldConfigMapper extends BaseMapper<FieldConfigDO> {

    /**
     * 根据表名称查询
     *
     * @param tableName 表名称
     * @return 字段配置信息
     */
    @Select("SELECT * FROM gen_field_config WHERE table_name = #{tableName} ORDER BY field_sort ASC")
    List<FieldConfigDO> selectListByTableName(@Param("tableName") String tableName);
}
