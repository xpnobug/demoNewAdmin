package com.newadmin.demoservice.mainPro.filepro.service;

import com.newadmin.demoservice.mainPro.filepro.resp.FileStatisticsResp;
import java.util.List;
import org.dromara.x.file.storage.core.FileInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件业务接口
 */
public interface FileService {

    /**
     * 上传到默认存储
     *
     * @param file 文件信息
     * @return 文件信息
     */
    default FileInfo upload(MultipartFile file) {
        return upload(file, null);
    }

    void beforeDelete(List<String> ids);

    /**
     * 上传到指定存储
     *
     * @param file        文件信息
     * @param storageCode 存储编码
     * @return 文件信息
     */
    FileInfo upload(MultipartFile file, String storageCode);

    /**
     * 根据存储 ID 列表查询
     *
     * @param storageIds 存储 ID 列表
     * @return 文件数量
     */
    long countByStorageIds(List<String> storageIds);

    /**
     * 查询文件资源统计信息
     *
     * @return 资源统计信息
     */
    FileStatisticsResp statistics();

    void fill(Object obj);
}