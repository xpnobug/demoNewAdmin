package com.newadmin.demoservice.util;

import java.util.HashMap;
import java.util.Map;

public final class FileTypeUtil {

    public static String fileTypeCheck(String url) {
        // 创建一个Map来存储文件扩展名到类型的映射
        Map<String, String> extensionsToTypes = new HashMap<>();
        extensionsToTypes.put("jpg", "image");
        extensionsToTypes.put("png", "image");
        extensionsToTypes.put("gif", "image"); // 添加其他图片格式
        extensionsToTypes.put("mp3", "audio");
        extensionsToTypes.put("wav", "audio"); // 添加其他音频格式
        extensionsToTypes.put("mp4", "video");
        extensionsToTypes.put("webm", "video"); // 添加其他视频格式
        // ... 可以继续添加其他扩展名

        // 提取URL的文件扩展名
        String extension = getExtensionFromUrl(url);
        if (extension != null && extensionsToTypes.containsKey(extension.toLowerCase())) {
            return extensionsToTypes.get(extension.toLowerCase());
        }
        return ""; // 如果没有匹配项，返回空字符串或null，或者抛出异常
    }

    // 辅助方法：从URL中提取文件扩展名
    private static String getExtensionFromUrl(String url) {
        // 这里是一个简化的示例，仅用于演示。在实际应用中，您可能需要使用更复杂的逻辑来处理URL
        int lastDotIndex = url.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < url.length() - 1) {
            return url.substring(lastDotIndex + 1);
        }
        return null; // 如果没有找到扩展名，返回null
    }

}
