package com.newadmin.demoservice.base.common;

import com.newadmin.democore.project.ProjectProperties;
import com.newadmin.democore.util.validate.ValidationUtils;
import com.newadmin.demolog.log.core.annotation.Log;
import com.newadmin.demoservice.mainPro.filepro.resp.FileUploadResp;
import com.newadmin.demoservice.mainPro.filepro.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.x.file.storage.core.FileInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "公共 API")
@Log(ignore = true)
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/common")
public class CommonController {

    private final ProjectProperties projectProperties;
    private final FileService fileService;

    @Operation(summary = "上传文件", description = "上传文件")
    @PostMapping("/file")
    public FileUploadResp upload(@NotNull(message = "文件不能为空") MultipartFile file) {
        ValidationUtils.throwIf(projectProperties.isProduction(), "演示环境不支持上传文件");
        ValidationUtils.throwIf(file::isEmpty, "文件不能为空");
        FileInfo fileInfo = fileService.upload(file);
        return FileUploadResp.builder().url(fileInfo.getUrl()).build();
    }
}
