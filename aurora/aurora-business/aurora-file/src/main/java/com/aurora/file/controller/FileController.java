package com.aurora.file.controller;

import com.aurora.common.core.utils.file.FileUtil;
import com.aurora.common.core.web.domain.Result;
import com.aurora.file.domain.File;
import com.aurora.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


/**
 * describe: 文件请求处理
 *
 * @author Guo Huaijian
 * @date 2021/9/10
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RestController
@RequestMapping("file")
@Slf4j
public class FileController {

    @Resource
    private FileService fileService;

    /**
     * 文件上传请求
     */
    @PostMapping("upload")
    public Result<File> upload(@RequestParam("file") MultipartFile file) {
        try {
            // 上传并返回访问地址
            String url = fileService.uploadFile(file);
            File fileInfo = new File();
            fileInfo.setName(FileUtil.getName(url));
            fileInfo.setUrl(url);
            return Result.success(fileInfo);
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return Result.error(e.getMessage());
        }
    }
}
