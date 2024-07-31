package com.newadmin.demoservice.mainPro.nas.service;

import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democore.oss.domain.OssReq;
import com.newadmin.democore.oss.domain.OssResp;
import com.newadmin.demoservice.mainPro.nas.entity.File;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author couei
 * @since 2024-03-31
 */
public interface FileService {

    List<File> getFileList(String articleId, Page page);

    List<File> getFileListById(List<String> articleId);

    OssResp upload(MultipartFile file, OssReq req);

    //添加
    JsonObject addImgOrIcon(OssReq req);

    JsonObject addImg(File file);

    JsonObject delectFile(Long fid);
}
