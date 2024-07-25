package com.newadmin.demoservice.mainPro.qqbots.service.impl;

import cn.hutool.json.JSONObject;
import com.newadmin.demoservice.mainPro.qqbots.base.config.BotEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 《飞鸟集》
 *
 * @author 86136
 */
public class BotBirdsMsg {

    private static ScheduledExecutorService scheduler;
    private static boolean isFirstTime = true;

    public static void sendMsg(String content, String msgId, String sendMsgUrl, String id) {
        if (isFirstTime) {
            sendMessage(sendMsgUrl);
            System.out.println("第一次进入，执行初始化任务。");
        }

        // 创建一个新的定时线程池
        scheduler = Executors.newScheduledThreadPool(1);

        // 启动定时任务，每过一分钟执行一次
        scheduler.scheduleAtFixedRate(() -> sendMessage(sendMsgUrl), 0, 1, TimeUnit.MINUTES);
    }

    private static void sendMessage(String sendMsgUrl) {
        String birds = BotEvent.getBirds();
        JSONObject content = new JSONObject();
        content.put("content", birds);
        BotEvent.sendMsg(sendMsgUrl, content);
    }
}
