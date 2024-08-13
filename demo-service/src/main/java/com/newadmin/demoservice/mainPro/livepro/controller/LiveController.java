package com.newadmin.demoservice.mainPro.livepro.controller;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demolog.log.core.annotation.Log;
import com.newadmin.demoservice.mainPro.livepro.model.query.LiveQuery;
import com.newadmin.demoservice.mainPro.livepro.service.LiveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 直播记录管理 API
 *
 * @author couei
 * @since 2024/08/05 22:02
 */
@Tag(name = "直播记录管理 API")
@RestController
@RequestMapping("/live")
@RequiredArgsConstructor
public class LiveController {

    private final LiveService liveService;

    @Operation(summary = "分页查询直播记录列表", description = "分页查询直播记录列表")
    @GetMapping("/list")
    public JsonPageObject page(
        @Parameter(in = ParameterIn.QUERY, description = "分页参数")
        Page page,
        @Parameter(in = ParameterIn.QUERY, description = "查询参数")
        LiveQuery query) {
        return new JsonPageObject(page, liveService.page(query, page));
    }

    @Operation(summary = "获取直播记录详情", description = "获取直播记录详情")
    @GetMapping("/getById")
    public JsonObject get(@PathVariable String id) {
        return new JsonObject(liveService.getDetail(id));
    }

//    @Operation(summary = "创建直播记录", description = "创建直播记录")
//    @PostMapping("/insert")
//    public JsonObject create(
//        @Parameter(in = ParameterIn.QUERY, description = "创建参数")
//        LiveReq req) {
//        return new JsonObject(liveService.add(req));
//    }
//
//    @Operation(summary = "更新直播记录", description = "更新直播记录")
//    @PutMapping("/upd/{id}")
//    public JsonObject update(
//        @Parameter(in = ParameterIn.PATH, description = "主键")
//        @PathVariable String id,
//        @Parameter(in = ParameterIn.QUERY, description = "更新参数")
//        LiveReq req) {
//        liveService.update(req);
//        return new JsonObject();
//    }

    @Operation(summary = "删除直播记录", description = "删除直播记录")
    @DeleteMapping("/delById")
    public JsonObject delete(
        @Parameter(in = ParameterIn.PATH, description = "主键")
        @PathVariable String id) {
        liveService.delete(id);
        return new JsonObject();
    }

    @Operation(summary = "根据房间ID查询直播", description = "根据房间ID查询直播")
    @GetMapping("/findLiveRoom/{roomId}")
    public JsonObject findLiveRoom(@PathVariable String roomId) {
        return new JsonObject(liveService.findByRoomId(roomId));
    }

    //live_room_online_user
    @Log(ignore = true)
    @Operation(summary = "根据房间ID查询在线用户", description = "根据房间ID查询在线用户")
    @GetMapping("/liveRoomOnlineUser")
    public JsonObject findLiveRoomOnlineUser(@RequestParam String liveRoomId) {
        return new JsonObject(liveService.findLiveRoomOnlineUser(liveRoomId));
    }

}