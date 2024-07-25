package com.newadmin.demoservice.mainPro.nas.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ItemIconDto {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private String iconJson;
    private String title;
    private String url;
    private String lanUrl;
    private String description;
    private Integer openMethod;
    private Integer sort;
    private Long itemIconGroupId;
    private Long userId;
    private Icon icon;

    @Data
    public class Icon {

        private Integer itemType;
        private String backgroundColor;
        private String src;
        private String text;
        private String jbColor; //渐变
        // 省略getter和setter方法
    }
}
