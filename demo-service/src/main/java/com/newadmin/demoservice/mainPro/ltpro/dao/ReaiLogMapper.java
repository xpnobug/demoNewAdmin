package com.newadmin.demoservice.mainPro.ltpro.dao;

import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author couei
 * @since 2024-05-23
 */
@Mapper
public interface ReaiLogMapper {

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户信息
     */
    @Select("SELECT * FROM reai_users WHERE username = #{username}")
    ReaiUsers selectByUsername(@Param("username") String username);
}
