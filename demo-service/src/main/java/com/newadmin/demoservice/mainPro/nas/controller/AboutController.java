package com.newadmin.demoservice.mainPro.nas.controller;

import com.newadmin.democore.kduck.web.json.JsonObject;
import java.util.HashMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AboutController {

    @PostMapping("about")
    public JsonObject about() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "NewAdmin");
        map.put("version", "1.0");
        map.put("description", "This is a demo service");
        return new JsonObject(map); // 返回一个简单的字符串
    }
}
