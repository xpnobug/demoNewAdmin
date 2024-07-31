package com.newadmin.demoservice.mainPro.ltpro.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.newadmin.democore.kduck.definition.BeanEntityDef;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ParamMap;
import com.newadmin.democore.kduck.service.ValueMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.util.validate.CheckUtils;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.MessageTypeEnum;
import com.newadmin.demoservice.mainPro.ltpro.entity.MessageDO;
import com.newadmin.demoservice.mainPro.ltpro.entity.MessageUserDO;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.MessageTypeUnreadResp;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.MessageUnreadResp;
import com.newadmin.demoservice.mainPro.ltpro.service.MessageUserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 消息和用户关联业务实现
 */
@Service
@RequiredArgsConstructor
public class MessageUserServiceImpl extends DefaultService implements MessageUserService {

    public static final String TABLE_NAME = "reai_message_user";

    @Override
    public MessageUnreadResp countUnreadMessageByUserId(String userId, Boolean isDetail) {
        // 创建返回结果对象
        MessageUnreadResp result = new MessageUnreadResp();
        // 初始化总未读数量
        Long total = 0L;

        // 如果需要详细统计每种消息类型的未读数量
        if (Boolean.TRUE.equals(isDetail)) {
            // 创建详细列表
            List<MessageTypeUnreadResp> detailList = new ArrayList<>();

            // 遍历消息类型枚举
            for (MessageTypeEnum messageType : MessageTypeEnum.values()) {
                // 创建单个消息类型未读统计结果对象
                MessageTypeUnreadResp resp = new MessageTypeUnreadResp();
                // 设置消息类型
                resp.setType(messageType);
                // 查询该类型消息的未读数量
                Long count = this.selectUnreadCountByUserIdAndType(userId, messageType.getValue());
                // 设置未读数量
                resp.setCount(count);
                // 将统计结果添加到详细列表中
                detailList.add(resp);
                // 累加到总未读数量中
                total += count;
            }
            // 将详细统计结果设置到返回对象中
            result.setDetails(detailList);
        } else {
            // 否则，仅查询总的未读数量
            total = this.selectUnreadCountByUserIdAndType(userId, null);
        }

        // 设置总的未读数量到返回对象中
        result.setTotal(total);
        // 返回统计结果对象
        return result;
    }

    public Long selectUnreadCountByUserIdAndType(String userId, Integer messageType) {
        //获取当前登录的用户信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        Assert.notNull(tokenInfo.loginId, "用户未登录");
        userId = tokenInfo.loginId.toString();
        // 创建参数映射，并设置用户ID和消息类型
        ValueMap params = new ValueMap();
        params.put(MessageUserDO.USER_ID, userId);
        params.put(MessageUserDO.IS_READ, false);
        if (messageType != null) {
            params.put(MessageDO.TYPE, messageType);
        }

        // 获取 message 表和 user_message 表的实体表示对象
        BeanEntityDef message = super.getEntityDef(MessageServiceImpl.TABLE_NAME);
        BeanEntityDef userMessage = super.getEntityDef(TABLE_NAME);

        // 创建 SelectBuilder 并绑定字段
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.bindFields("t1", userMessage.getFieldList());
        selectBuilder.bindFields("t2", message.getFieldList());

        // 构建查询：从 user_message 表中选择记录，并左连接 message 表
        selectBuilder.from("t1", userMessage)
            .leftJoinOn("t2", message, "messageId") // 左连接条件
            .where()
            .and("t1.user_id", ConditionType.EQUALS, MessageUserDO.USER_ID) // 条件：用户ID匹配
            .and("t2.type", ConditionType.EQUALS, MessageDO.TYPE)
            .and("t1.is_read", ConditionType.EQUALS, MessageUserDO.IS_READ) // 条件：未读状态
            .orderBy().desc("t2.create_time"); // 按创建时间降序排序

        // 执行查询，获取未读消息列表
        List<MessageUserDO> userDOList = super.listForBean(selectBuilder.build(),
            MessageUserDO::new);

        // 返回未读消息的数量
        return (long) userDOList.size();
    }

    @Override
    public void add(String messageId, List<String> userIdList) {
        // 检查用户ID列表是否为空，如果为空则抛出异常
        CheckUtils.throwIfEmpty(userIdList, "消息接收人不能为空");

        // 将用户ID列表转换为消息用户关联对象列表
        List<MessageUserDO> messageUserList = userIdList.stream().map(userId -> {
            // 创建消息用户关联对象
            MessageUserDO messageUser = new MessageUserDO();
            // 设置用户ID
            messageUser.setUserId(userId);
            // 设置消息ID
            messageUser.setMessageId(messageId);
            // 设置未读状态
            messageUser.setIsRead(false);
            // 返回消息用户关联对象
            return messageUser;
        }).toList();

        // 批量添加消息用户关联对象到数据库
        super.batchAdd(TABLE_NAME, messageUserList);
    }

    @Override
    public void readMessage(List<String> ids) {
        //获取当前登录的用户信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        Assert.notNull(tokenInfo.loginId, "用户未登录");
        String userId = tokenInfo.loginId.toString();
        ValueMap params = new ValueMap();
        params.put(MessageUserDO.MESSAGE_ID, ids);
        params.put(MessageUserDO.IS_READ, false);
        if (ids == null) {
            params.put(MessageUserDO.USER_ID, userId);
        }

        // 构建查询条件：查询未读消息并且在指定的 ids 列表中
        SelectBuilder selectBuilder = new SelectBuilder(params);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME)) // 设置查询表
            .where() // 添加查询条件
            .and("is_read", ConditionType.EQUALS, MessageUserDO.IS_READ) // 条件：is_read 字段等于 false
            .and("user_id", ConditionType.EQUALS,
                MessageUserDO.USER_ID) // 条件：user_id 字段等于当前登录的用户ID)
            .and("message_id", ConditionType.IN,
                MessageUserDO.MESSAGE_ID); // 条件：message_id 在给定的 ids 列表中

        // 执行查询，获取未读消息的列表
        List<MessageUserDO> unreadMessages = super.listForBean(selectBuilder.build(),
            MessageUserDO::new);

        // 准备更新的字段和对应的值
        Map<String, Object> updateMap = ParamMap.create()
            .set(MessageUserDO.IS_READ, true) // 设置 is_read 字段为 true，表示消息已读
            .set(MessageUserDO.READ_TIME, new Date()) // 设置 read_time 字段为当前时间
            .toMap();

        // 如果找到未读消息，则执行更新操作
        if (!unreadMessages.isEmpty()) {
            unreadMessages.forEach(messageUser -> {
                // 准备更新的字段和对应的值
                updateMap.put(MessageUserDO.ID, messageUser.getId());
                super.update(TABLE_NAME, updateMap); // 更新数据库中符合条件的记录
            });
        }
    }

    @Override
    public void deleteByMessageIds(List<String> messageIds) {
        // 将 messageIds 转换为字符串数组
        String[] idsArray = messageIds.toArray(new String[0]);

        // 调用父类的 delete 方法，根据 messageId 删除记录
        super.delete(TABLE_NAME, MessageUserDO.MESSAGE_ID, idsArray);
    }
}