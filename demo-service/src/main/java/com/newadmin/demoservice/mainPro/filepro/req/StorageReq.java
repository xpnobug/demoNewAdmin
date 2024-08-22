/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.newadmin.demoservice.mainPro.filepro.req;

import com.newadmin.demoservice.mainPro.ltpro.common.constant.RegexConstants;
import com.newadmin.demoservice.mainPro.ltpro.common.enums.DisEnableStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serial;
import lombok.Data;

/**
 * 存储请求信息
 */
@Data
@Schema(description = "存储请求信息")
public class StorageReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Schema(description = "名称", example = "存储1")
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 编码
     */
    @Schema(description = "编码", example = "local")
    @NotBlank(message = "编码不能为空")
    @Pattern(regexp = RegexConstants.GENERAL_CODE, message = "编码长度为 2-30 个字符，支持大小写字母、数字、下划线，以字母开头")
    private String code;

    /**
     * 类型
     */
    @Schema(description = "类型", example = "2")
    @NotNull(message = "类型非法")
    private Integer type;

    /**
     * 访问密钥
     */
    @Schema(description = "访问密钥", example = "")
    private String accessKey;

    /**
     * 私有密钥
     */
    @Schema(description = "私有密钥", example = "")
    private String secretKey;

    /**
     * 终端节点
     */
    @Schema(description = "终端节点", example = "")
    private String endpoint;

    /**
     * 桶名称
     */
    @Schema(description = "桶名称", example = "C:/continew-admin/data/file/")
    private String bucketName;

    /**
     * 域名
     */
    @Schema(description = "域名", example = "http://localhost:8000/file")
    @NotBlank(message = "域名不能为空")
    private String domain;

    /**
     * 排序
     */
    @Schema(description = "排序", example = "1")
    private Integer sort;

    /**
     * 描述
     */
    @Schema(description = "描述", example = "存储描述")
    private String description;

    /**
     * 是否为默认存储
     */
    @Schema(description = "是否为默认存储", example = "true")
    @NotNull(message = "是否为默认存储不能为空")
    private Boolean isDefault;

    /**
     * 状态
     */
    @Schema(description = "状态", example = "1")
    private DisEnableStatusEnum status;
}