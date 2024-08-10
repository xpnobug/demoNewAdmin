package com.newadmin.demoservice.mainPro.livepro.controller;

import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.demoservice.mainPro.livepro.model.resp.LiveRoomResp;
import com.newadmin.demoservice.mainPro.livepro.service.LiveRoomService;
import com.newadmin.demoservice.mainPro.livepro.service.LiveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 直播间管理 API
 *
 * @author couei
 * @since 2024/08/05 21:29
 */
@Tag(name = "直播间管理 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user_live_room")
public class LiveRoomController {

    private final LiveService liveService;
    private final LiveRoomService liveRoomService;

    //    @Operation(summary = "分页查询直播间列表", description = "分页查询直播间列表")
//    @GetMapping("/list")
//    public JsonPageObject page(
//        @Parameter(in = ParameterIn.QUERY, description = "分页参数")
//        Page page,
//        @Parameter(in = ParameterIn.QUERY, description = "查询参数")
//        LiveRoomQuery query) {
//        return new JsonPageObject(page, liveRoomService.page(query, page));
//    }
//
    @Operation(summary = "获取直播间详情", description = "获取直播间详情")
    @GetMapping("/find_by_userId/{userId}")
    public JsonObject get(@PathVariable String userId) {
        return new JsonObject(liveService.getLiveRoomByUserId(userId));
    }

    //
    @Operation(summary = "创建直播间", description = "创建直播间")
    @PostMapping("/create")
    public JsonObject create(@RequestBody LiveRoomResp req) {
        return new JsonObject(liveRoomService.add(req));
    }
//
//    @Operation(summary = "更新直播间", description = "更新直播间")
//    @PutMapping("/upd/{id}")
//    public JsonObject update(
//        @Parameter(in = ParameterIn.PATH, description = "主键")
//        @PathVariable String id,
//        @Parameter(in = ParameterIn.QUERY, description = "更新参数")
//        LiveRoomReq req) {
//        liveRoomService.update(req);
//        return new JsonObject();
//    }
//
//    @Operation(summary = "删除直播间", description = "删除直播间")
//    @DeleteMapping("/del/{id}")
//    public JsonObject delete(
//        @Parameter(in = ParameterIn.PATH, description = "主键")
//        @PathVariable String id) {
//        liveRoomService.delete(id);
//        return new JsonObject();
//    }

}