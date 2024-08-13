package com.newadmin.demoservice.mainPro.livepro.service;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.demoservice.mainPro.livepro.model.query.LiveQuery;
import com.newadmin.demoservice.mainPro.livepro.model.query.LiveRoomQuery;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveDetailResp;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveResp;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveRoomDetailResp;
import java.util.List;

/**
 * 直播记录业务接口
 *
 * @author couei
 * @since 2024/08/05 22:02
 */
public interface LiveService {

    /**
     * 分页查询直播记录列表
     *
     * @param query 查询条件
     * @param page  分页查询条件
     * @return 直播记录分页列表信息
     */
    List<LiveResp> page(LiveQuery query, Page page);

    /**
     * 查询直播记录详情
     *
     * @param id 直播记录id
     * @return 直播记录详情
     */
    LiveDetailResp getDetail(String id);

    /**
     * 新增直播记录
     *
     * @param req 新增参数
     * @return 新增
     */
    String add(LiveDetailResp req);
//
//    /**
//     * 修改直播记录
//     *
//     * @param req 修改参数
//     * @return 修改
//     */
//    void update(LiveReq req);

    LiveRoomQuery getLiveRoomByUserId(String userId);

    /**
     * 删除直播记录
     *
     * @param id 直播记录id
     * @return 删除
     */
    void delete(String id);

    void updateLiveRoom(String id, String socketId);

    LiveRoomDetailResp findByRoomId(String roomId);

    Object findLiveRoomOnlineUser(String roomId);
}