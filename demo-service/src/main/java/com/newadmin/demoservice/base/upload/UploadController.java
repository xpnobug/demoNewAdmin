package com.newadmin.demoservice.base.upload;

import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.oss.domain.OssReq;
import com.newadmin.democore.oss.domain.OssResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private BaseUploadService baseUploadService;

    /**
     * @param file 文件
     * @Description 上传文件
     */
    @PostMapping("/uploadImg")
    public JsonObject uploadFile(@RequestParam("imgfile") MultipartFile file) {
        //上传文件
        OssReq req = new OssReq();
        req.setFileName(file.getOriginalFilename());
        req.setFilePath(file.getContentType());
        req.setUid("1");
        req.setAutoPath(true);
        OssResp upload = baseUploadService.upload(file, req);
        req.setDownloadUrl(upload.getDownloadUrl());
        return new JsonObject(upload);
    }
}
