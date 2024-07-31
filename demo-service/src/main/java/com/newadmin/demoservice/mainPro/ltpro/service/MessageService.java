
package com.newadmin.demoservice.mainPro.ltpro.service;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.query.MessageQuery;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.req.MessageReq;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.resp.MessageResp;
import java.util.List;

/**
 * 消息业务接口
 */
public interface MessageService {

    /**
     * 分页查询列表
     *
     * @param query 查询条件
     * @param page  分页查询条件
     * @return 分页列表信息
     */
    List<MessageResp> page(MessageQuery query, Page page);

    /**
     * 新增
     *
     * @param req        新增信息
     * @param userIdList 接收人列表
     */
    void add(MessageReq req, List<String> userIdList);

    /**
     * 删除
     *
     * @param ids ID 列表
     */
    void delete(List<String> ids);
}