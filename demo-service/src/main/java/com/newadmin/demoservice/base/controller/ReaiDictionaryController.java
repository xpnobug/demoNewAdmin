package com.newadmin.demoservice.base.controller;

import com.newadmin.demoservice.mainPro.ltpro.service.ReaiDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author couei
 * @since 2024-06-10
 */
@RestController
@RequestMapping("/reaiDictionary")
public class ReaiDictionaryController {

    @Autowired
    private ReaiDictionaryService reaiDictionaryService;

}
