package com.newadmin.demoservice.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 唯一标识符生成器
 */

public class UniqueIdGenerator {

    // 生成基于IP地址、User-Agent和日期的唯一标识符
    public static String generateDailyUniqueId(String ipAddress, String userAgent) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        String combinedString = ipAddress + userAgent + date; // 将IP地址、User-Agent和日期组合
        return hashString(combinedString); // 对组合字符串进行哈希处理
    }

    // 对字符串进行哈希处理
    private static String hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate hash", e);
        }
    }
}