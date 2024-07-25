package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.newadmin.democommon.definition.BeanEntityDef;
import com.newadmin.democommon.service.DefaultService;
import com.newadmin.democommon.service.ValueMap;
import com.newadmin.democommon.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democommon.sqlbuild.SelectBuilder;
import com.newadmin.democommon.utils.Page;
import com.newadmin.demoservice.config.core.utils.CheckUtils;
import com.newadmin.demoservice.mainPro.ltpro.entity.MessageDO;
import com.newadmin.demoservice.mainPro.ltpro.entity.MessageUserDO;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.query.MessageQuery;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.req.MessageReq;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.MessageResp;
import com.newadmin.demoservice.mainPro.ltpro.service.MessageService;
import com.newadmin.demoservice.mainPro.ltpro.service.MessageUserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 消息业务实现
 */
@Service
@RequiredArgsConstructor
public class MessageServiceImpl extends DefaultService implements MessageService {

    public static final String TABLE_NAME = "reai_message";
    private final MessageUserService messageUserService;

    @Override
    public List<MessageResp> page(MessageQuery query, Page page) {
        ValueMap params = new ValueMap();
        // 将查询条件参数加入到params中
        params.put(MessageUserDO.USER_ID, query.getUserId());
        params.put(MessageUserDO.IS_READ, query.getIsRead());

        // 获取message表的实体表示对象
        BeanEntityDef message = super.getEntityDef(TABLE_NAME);
        // 获取user_message表的实体表示对象
        BeanEntityDef userMessage = super.getEntityDef(MessageUserServiceImpl.TABLE_NAME);

        // 构建查询语句
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.bindFields("t1", message.getFieldList());
        selectBuilder.bindFields("t2", userMessage.getFieldList());

        selectBuilder.from("t1", message)
            .innerJoinOn("t2", userMessage, MessageUserDO.MESSAGE_ID)
            .where()
            .and("t2.USER_ID", ConditionType.EQUALS, MessageUserDO.USER_ID)
            .and("t2.is_read", ConditionType.EQUALS, MessageUserDO.IS_READ)
            .orderBy().desc("t1.create_time");

        // 返回分页结果
        return super.listForBean(selectBuilder.build(), page, MessageResp::new);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MessageReq req, List<String> userIdList) {
        // 如果userIdList为空，则抛出异常
        CheckUtils.throwIf(() -> CollUtil.isEmpty(userIdList), "消息接收人不能为空");
        // 将请求对象转换为MessageDO对象
        MessageDO message = BeanUtil.copyProperties(req, MessageDO.class);
        // 添加消息到message表
        super.add(TABLE_NAME, message);
        // 添加消息接收人
        messageUserService.add(message.getMessageId(), userIdList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<String> ids) {
        // 将 messageIds 转换为字符串数组
        String[] idsArray = ids.toArray(new String[0]);
        // 删除message表中的消息
        super.delete(TABLE_NAME, idsArray);
        // 删除user_message表中对应的消息
        messageUserService.deleteByMessageIds(ids);
    }

}