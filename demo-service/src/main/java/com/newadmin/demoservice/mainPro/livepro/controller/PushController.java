package com.newadmin.demoservice.mainPro.livepro.controller;

import com.newadmin.democore.kduck.web.json.JsonObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "直播间管理 API")
@RestController
@RequiredArgsConstructor
public class PushController {

    @Operation(summary = "on_publish", description = "on_publish")
    @PostMapping("/on_publish")
    public JsonObject onPublish() {
//        getApiV1StreamsDetail(body.getStreamId());
        return new JsonObject("[on_publish] all success, pass", 0,
            "[on_publish] all success, pass");
    }
}
