package com.newadmin.demoservice.base.upload;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;
import com.newadmin.democore.oss.domain.OssReq;
import com.newadmin.democore.oss.domain.OssResp;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BaseUploadService {

    private final MinioClientManager minioClientManager;

    public MinioClient createMinioClient() {
        return MinioClient.builder()
            .endpoint(minioClientManager.getEndpoint())
            .credentials(minioClientManager.getAccessKey(), minioClientManager.getSecretKey())
            .build();
    }

    public String generateAutoPath(OssReq req) {
        String uid = Optional.ofNullable(req.getUid()).map(String::valueOf).orElse("000000");
        cn.hutool.core.lang.UUID uuid = cn.hutool.core.lang.UUID.fastUUID();
        String suffix = FileNameUtil.getSuffix(req.getFileName());
        String yearAndMonth = DateUtil.format(new Date(), DatePattern.NORM_DATE_FORMAT);
        return req.getFilePath() + StrUtil.SLASH + yearAndMonth + StrUtil.SLASH + uid
            + StrUtil.SLASH + uuid + StrUtil.DOT + suffix;
    }

    private String getDownloadUrl(String bucket, String pathFile) {
        String endpoint = "https://alist.reaicc.com";
        return endpoint + StrUtil.SLASH + bucket + StrUtil.SLASH + pathFile;
    }

    public OssResp upload(MultipartFile file, OssReq req) {
//        manager.loadConfigFromExternalSource(); // 确保加载配置信息
        MinioClient minioClient = createMinioClient();
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件大小
        long fileSize = file.getSize();
        // 获取文件类型
        String fileType = file.getContentType();
        // 获取文件前缀
        String prefix = FileNameUtil.getPrefix(fileName);
        // 获取文件后缀
        String suffix = FileNameUtil.getSuffix(fileName);

        // 使用putObject上传一个文件到存储桶中。
        String absolutePath = req.isAutoPath() ? generateAutoPath(req)
            : req.getFilePath() + StrUtil.SLASH + req.getFileName();

        try {
            //返回临时带签名、过期时间一天、PUT请求方式的访问URL
            InputStream inputStream = file.getInputStream();
            minioClient.putObject(PutObjectArgs.builder()
                .bucket("nas")
                .object(absolutePath)
                .stream(inputStream, file.getSize(), -1)
                .contentType(file.getContentType())
                .build());
            return OssResp.builder()
//                .uploadUrl(url)
                .downloadUrl(getDownloadUrl("nas", absolutePath))
                .build();
        } catch (ServerException | InsufficientDataException | ErrorResponseException |
                 IOException | NoSuchAlgorithmException | InvalidKeyException |
                 InvalidResponseException | XmlParserException | InternalException e) {
            throw new RuntimeException(e);
        }
    }
}
