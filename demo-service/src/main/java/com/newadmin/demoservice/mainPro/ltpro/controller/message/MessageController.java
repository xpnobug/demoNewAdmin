package com.newadmin.demoservice.mainPro.ltpro.controller.message;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.kduck.web.json.JsonPageObject;
import com.newadmin.demoservice.mainPro.ltpro.entity.model.query.MessageQuery;
import com.newadmin.demoservice.mainPro.ltpro.helper.LoginHelper;
import com.newadmin.demoservice.mainPro.ltpro.service.MessageService;
import com.newadmin.demoservice.mainPro.ltpro.service.MessageUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息管理 API
 */
@Tag(name = "消息管理 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService baseService;
    private final MessageUserService messageUserService;

    @Operation(summary = "分页查询列表", description = "分页查询列表")
    @GetMapping
    public JsonPageObject page(MessageQuery query, @Validated Page page) {
//        query.setUserId(LoginHelper.getUserId());
        return new JsonPageObject(page, baseService.page(query, page));
    }

    @Operation(summary = "删除数据", description = "删除数据")
    @Parameter(name = "ids", description = "ID 列表", example = "1,2", in = ParameterIn.PATH)
    @DeleteMapping("/{ids}")
    public JsonObject delete(@PathVariable List<String> ids) {
        baseService.delete(ids);
        return new JsonObject("删除成功");
    }

    @Operation(summary = "标记已读", description = "将消息标记为已读状态")
    @Parameter(name = "ids", description = "消息ID列表", example = "1,2", in = ParameterIn.QUERY)
    @PatchMapping("/read")
    public JsonObject readMessage(@RequestParam(required = false) List<String> ids) {
        messageUserService.readMessage(ids);
        return new JsonObject();
    }

    @Operation(summary = "查询未读消息数量", description = "查询当前用户的未读消息数量")
    @Parameter(name = "isDetail", description = "是否查询详情", example = "true", in = ParameterIn.QUERY)
    @GetMapping("/unread")
    public JsonObject countUnreadMessage(@RequestParam(required = false) Boolean detail) {
        return new JsonObject(
            messageUserService.countUnreadMessageByUserId(LoginHelper.getUserId(), detail));
    }
}