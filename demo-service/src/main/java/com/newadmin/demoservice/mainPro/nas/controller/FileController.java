//package com.newadmin.demoservice.nas.controller;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.newadmin.democommon.oss.domain.OssReq;
//import com.newadmin.democommon.oss.domain.OssResp;
//import com.newadmin.demoservice.nas.entity.User;
//import com.newadmin.demoservice.nas.service.UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import java.io.Serializable;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import com.newadmin.democommon.web.json.JsonObject;
//import com.newadmin.democommon.web.json.JsonPageObject;
//import com.newadmin.democommon.utils.Page;
//import java.util.List;
//import com.newadmin.demoservice.nas.entity.File;
//import com.newadmin.demoservice.nas.service.FileService;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// * <p>
// * 前端控制器
// * </p>
// *
// * @author couei
// * @since 2024-03-31
// */
//@RestController
//@RequestMapping("/file")
//public class FileController {
//
//    @Autowired
//    private FileService fileService;
//    @Autowired
//    private UserService userService;
//
//    /**
//     * 分页查询所有数据
//     *
//     * @param page 分页对象
//     * @return 所有数据
//     */
//    @PostMapping("/getList")
//    public JsonPageObject selectPageAll(Page page, HttpServletRequest request) {
//        User currentUserByToken = userService.getCurrentUserByToken(request);
//
//        List<File> fileInfo = fileService.list(
//            new QueryWrapper<File>().eq("user_id", currentUserByToken.getId()));
//        return new JsonPageObject(page, fileInfo);
//    }
//
//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @GetMapping("{id}")
//    public JsonObject selectOne(@PathVariable Serializable id) {
//        return new JsonObject(fileService.getById(id));
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param file 实体对象
//     * @return 新增结果
//     */
//    @PostMapping("/add")
//    public JsonObject insert(@RequestBody File file) {
//        return new JsonObject(fileService.save(file));
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param file 实体对象
//     * @return 修改结果
//     */
//    @PutMapping
//    public JsonObject update(@RequestBody File file) {
//        return new JsonObject(fileService.updateById(file));
//    }
//
//    /**
//     * 删除数据
//     *
//     * @param idList 主键结合
//     * @return 删除结果
//     */
//    @DeleteMapping
//    public JsonObject delete(@RequestBody List<Long> idList) {
//        return new JsonObject(fileService.removeByIds(idList));
//    }
//
//    /**
//     * @param file 文件
//     * @Description 上传文件
//     */
//    @PostMapping("/uploadImg")
//    public JsonObject uploadFile(@RequestParam("imgfile") MultipartFile file,
//        HttpServletRequest request) {
//
//        //上传文件
//        OssReq req = new OssReq();
//        req.setFileName(file.getOriginalFilename());
//        req.setFilePath(file.getContentType());
//        req.setUid("1");
//        req.setAutoPath(true);
//        OssResp upload = fileService.upload(file, req);
//        req.setDownloadUrl(upload.getDownloadUrl());
//        fileService.addImgOrIcon(req);
//        return new JsonObject(upload);
//    }
//
//    @PostMapping("/deletes")
//    public JsonObject deleteByIds(@RequestBody List<Long> idList) {
//        return new JsonObject(fileService.removeByIds(idList));
//    }
//}
