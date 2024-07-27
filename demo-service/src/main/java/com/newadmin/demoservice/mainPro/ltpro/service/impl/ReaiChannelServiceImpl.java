package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import com.newadmin.democommon.service.DefaultService;
import com.newadmin.democommon.service.ValueMap;
import com.newadmin.democommon.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democommon.sqlbuild.SelectBuilder;
import com.newadmin.democommon.utils.Page;
import com.newadmin.demoservice.mainPro.ltpro.entity.ReaiChannel;
import com.newadmin.demoservice.mainPro.ltpro.service.ReaiChannelService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ReaiChannel 服务实现类
 * </p>
 *
 * @author couei
 * @since 2024-07-27
 */
@Service
public class ReaiChannelServiceImpl extends DefaultService implements ReaiChannelService {

    // 定义表名常量
    public static final String TABLE_NAME = "reai_channel";

    /**
     * 列表查询方法
     *
     * @param page 分页信息
     * @return 查询结果列表
     */
    @Override
    public List<ReaiChannel> list(Page page) {
        // 创建参数映射
        ValueMap params = new ValueMap();
        // 创建查询构建器
        SelectBuilder selectBuilder = new SelectBuilder(params);
        // 构建查询语句
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)).where()
            .and("name", ConditionType.EQUALS, ReaiChannel.NAME) // 添加条件
            .orderBy().desc("create_time"); // 添加排序
        // 执行查询并返回结果
        return super.listForBean(selectBuilder.build(), page, ReaiChannel::new);
    }

    /**
     * 根据 ID 查询
     *
     * @param id 主键 ID
     * @return 查询结果
     */
    @Override
    public Object getById(String id) {
        // 创建参数映射并添加 ID 参数
        ValueMap params = new ValueMap();
        params.put(ReaiChannel.ID, id);
        // 创建查询构建器
        SelectBuilder selectBuilder = new SelectBuilder(params);
        // 构建查询语句
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)).where()
            .and("id", ConditionType.EQUALS, ReaiChannel.ID) // 添加条件
            .orderBy().desc("create_time"); // 添加排序
        // 执行查询并返回结果
        return getForBean(selectBuilder.build(), ReaiChannel::new);
    }

    /**
     * 保存数据
     *
     * @param reaiChannel 实体对象
     * @return 保存后的实体对象
     */
    @Override
    public ReaiChannel save(ReaiChannel reaiChannel) {
        // 保存数据到数据库
        super.add(TABLE_NAME, reaiChannel);
        // 返回保存后的对象
        return reaiChannel;
    }
}
