package com.newadmin.demoservice.mainPro.livepro.service;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.demoservice.mainPro.livepro.model.query.LiveRoomQuery;
import com.newadmin.demoservice.mainPro.livepro.model.req.LiveRoomReq;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveRoomDetailResp;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveRoomResp;
import java.util.List;

/**
 * 直播间业务接口
 *
 * @author couei
 * @since 2024/08/05 21:29
 */
public interface LiveRoomService {

    /**
     * 分页查询直播间列表
     *
     * @param query 查询条件
     * @param page  分页查询条件
     * @return 直播间分页列表信息
     */
    List<LiveRoomResp> page(LiveRoomQuery query, Page page);

    /**
     * 查询直播间详情
     *
     * @param id 直播间id
     * @return 直播间详情
     */
    LiveRoomDetailResp getDetail(Integer isShow, String id);

    /**
     * 新增直播间
     *
     * @param req 新增参数
     * @return 新增
     */
    String add(LiveRoomResp req);

    /**
     * 修改直播间
     *
     * @param req 修改参数
     * @return 修改
     */
    void update(LiveRoomReq req);

    /**
     * 删除直播间
     *
     * @param id 直播间id
     * @return 删除
     */
    void delete(String id);
}