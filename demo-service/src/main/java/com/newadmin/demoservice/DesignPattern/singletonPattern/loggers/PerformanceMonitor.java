package com.newadmin.demoservice.DesignPattern.singletonPattern.loggers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PerformanceMonitor {

    private static final Logger logger = LogManager.getLogger(PerformanceMonitor.class);

    public static void main(String[] args) {
        PerformanceMonitor monitor = new PerformanceMonitor();
        monitor.processData();
    }

    public void processData() {
        long startTime = System.currentTimeMillis();
        logger.info("Start processing data");

        // 模拟数据处理
        try {
            Thread.sleep(2000); // 模拟耗时操作
        } catch (InterruptedException e) {
            logger.error("Processing interrupted", e);
        }

        long endTime = System.currentTimeMillis();
        logger.info("Finished processing data in {} ms", (endTime - startTime));
    }
}
