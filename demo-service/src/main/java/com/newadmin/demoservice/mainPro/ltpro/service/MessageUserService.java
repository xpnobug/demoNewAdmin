package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.MessageUnreadResp;
import java.util.List;

/**
 * 消息和用户关联业务接口
 */
public interface MessageUserService {

    /**
     * 根据用户 ID 查询未读消息数量
     *
     * @param userId   用户 ID
     * @param isDetail 是否查询详情
     * @return 未读消息信息
     */
    MessageUnreadResp countUnreadMessageByUserId(String userId, Boolean isDetail);

    /**
     * 新增
     *
     * @param messageId  消息 ID
     * @param userIdList 用户 ID 列表
     */
    void add(String messageId, List<String> userIdList);

    /**
     * 将消息标记已读
     *
     * @param ids 消息ID（为空则将所有消息标记已读）
     */
    void readMessage(List<String> ids);

    /**
     * 根据消息 ID 删除
     *
     * @param messageIds 消息 ID 列表
     */
    void deleteByMessageIds(List<String> messageIds);
}