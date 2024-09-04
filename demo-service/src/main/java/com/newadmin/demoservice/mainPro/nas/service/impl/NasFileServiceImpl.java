package com.newadmin.demoservice.mainPro.nas.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.StrUtil;
import com.newadmin.democore.kduck.query.QuerySupport;
import com.newadmin.democore.kduck.service.DefaultService;
import com.newadmin.democore.kduck.service.ParamMap;
import com.newadmin.democore.kduck.sqlbuild.ConditionBuilder.ConditionType;
import com.newadmin.democore.kduck.sqlbuild.SelectBuilder;
import com.newadmin.democore.kduck.utils.Page;
import com.newadmin.democore.kduck.web.json.JsonObject;
import com.newadmin.democonfig.oss.domain.OssReq;
import com.newadmin.democonfig.oss.domain.OssResp;
import com.newadmin.demoservice.mainPro.ltpro.query.FileQuery;
import com.newadmin.demoservice.mainPro.nas.entity.File;
import com.newadmin.demoservice.mainPro.nas.service.NasFileService;
import com.newadmin.demoservice.util.FileTypeUtil;
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
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author couei
 * @since 2024-03-31
 */
@Service
@RequiredArgsConstructor
public class NasFileServiceImpl extends DefaultService implements NasFileService {

    public static final String TABLE_NAME = "file";

    public MinioClient createMinioClient() {
        return MinioClient.builder()
            .endpoint("http://182.92.201.19:9000")
            .credentials("8BQOfbbRiAOC6BoDFew5", "tOVWnBE63Fhixyz4hpgFpk0HJnW6ejP0znZj4ZGW")
            .build();
    }

    public String generateAutoPath(OssReq req) {
        String uid = Optional.ofNullable(req.getUid()).map(String::valueOf).orElse("000000");
        cn.hutool.core.lang.UUID uuid = cn.hutool.core.lang.UUID.fastUUID();
        String suffix = FileNameUtil.getSuffix(req.getFileName());
        String yearAndMonth = DateUtil.format(new Date(), DatePattern.NORM_MONTH_PATTERN);
        return req.getFilePath() + StrUtil.SLASH + yearAndMonth + StrUtil.SLASH + uid
            + StrUtil.SLASH + uuid + StrUtil.DOT + suffix;
    }

    private String getDownloadUrl(String bucket, String pathFile) {
        String endpoint = "https://alist.reaicc.com";
        return endpoint + StrUtil.SLASH + bucket + StrUtil.SLASH + pathFile;
    }

    @Override
    public List<File> getFileList(String articleId, Page page) {
        Map<String, Object> paramMap = ParamMap.create("articleId", articleId).toMap();
        QuerySupport query = super.getQuery(FileQuery.class, paramMap);
        return super.listForBean(query, page, File::new);
    }

    @Override
    public List<File> getFileListById(List<String> articleId) {
        Map<String, Object> paramMap = ParamMap.create("articleId", articleId).toMap();
        SelectBuilder selectBuilder = new SelectBuilder(paramMap);
        selectBuilder.from("", super.getEntityDef(TABLE_NAME))
            .where()
            .and("article_id", ConditionType.IN, "articleId");
        return super.listForBean(selectBuilder.build(), File::new);
    }

    @Override
    public OssResp upload(MultipartFile file, OssReq req) {
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

    //添加
    @Override
    public JsonObject addImgOrIcon(OssReq req) {
        File file = new File();
        file.setFileName(req.getFileName());
        file.setSrc(req.getDownloadUrl());
        file.setUserId(Long.valueOf(req.getUid()));
        file.setCreatedAt(new Date());
        file.setUpdatedAt(new Date());
        Serializable add = super.add(TABLE_NAME, file);
        return new JsonObject(add.toString());
    }

    @Override
    public JsonObject addImg(File file) {
        file.setFileType(FileTypeUtil.fileTypeCheck(file.getSrc()));
        return new JsonObject(super.add(TABLE_NAME, file).toString());
    }

    @Override
    public JsonObject delectFile(Long fid) {
        super.delete(TABLE_NAME, File.ID, new String[]{String.valueOf(fid)});
        return null;
    }
}
