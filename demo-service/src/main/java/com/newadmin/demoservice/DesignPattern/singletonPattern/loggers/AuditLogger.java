package com.newadmin.demoservice.DesignPattern.singletonPattern.loggers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuditLogger {

    private static final Logger logger = LogManager.getLogger("AuditLogger");

    public static void logLogin(String username) {
        logger.info("User logged in: {}", username);
    }

    public static void logPermissionChange(String username, String permission) {
        logger.info("User permission changed: user={}, permission={}", username, permission);
    }

    public static void logImportantOperation(String username, String operation) {
        logger.info("Important operation performed: user={}, operation={}", username, operation);
    }

    private static void performOtherOperations() {
        // 模拟其他操作
        System.out.println("Performing other operations...");
    }

    public static void main(String[] args) {
        // 模拟用户登录
        String username = "john_doe";
        AuditLogger.logLogin(username);

        // 模拟权限变更
        String newPermission = "ADMIN";
        AuditLogger.logPermissionChange(username, newPermission);

        // 模拟重要操作
        String operation = "Deleted user account";
        AuditLogger.logImportantOperation(username, operation);

        // 其他应用程序逻辑
        performOtherOperations();
    }
}
