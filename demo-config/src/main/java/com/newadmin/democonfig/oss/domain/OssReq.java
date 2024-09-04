package com.newadmin.democonfig.oss.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: 上传url请求入参
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OssReq {

    //    @ApiModelProperty(value = "文件存储路径")
    private String filePath;
    private String downloadUrl;
    //    @ApiModelProperty(value = "文件名")
    private String fileName;
    //    @ApiModelProperty(value = "请求的uid")
    private String uid;
    //    @ApiModelProperty(value = "自动生成地址")
    @Builder.Default
    private boolean autoPath = true;
}
