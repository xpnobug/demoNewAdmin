package com.newadmin.demoservice.mainPro.ltpro.controller;

import com.newadmin.demoservice.mainPro.ltpro.service.ReaiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author couei
 * @since 2024-05-23
 */
@RestController
@RequestMapping("/reaiLog")
public class ReaiLogController {

    @Autowired
    private ReaiLogService reaiLogService;

}
