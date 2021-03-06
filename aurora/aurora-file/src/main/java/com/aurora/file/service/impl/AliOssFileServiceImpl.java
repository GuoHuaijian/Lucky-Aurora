package com.aurora.file.service.impl;

import com.aliyun.oss.OSSClient;
import com.aurora.file.config.AliOssConfig;
import com.aurora.file.service.FileService;
import com.aurora.file.utils.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/10 15:20
 * @Version 1.0
 */
@Service
public class AliOssFileServiceImpl implements FileService {

    @Resource
    private AliOssConfig aliOssConfig;

    @Resource
    private OSSClient ossClient;


    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String fileName = FileUploadUtil.extractFilename(file);
        String objectName = aliOssConfig.getObjectName() + File.separator + fileName;
        ossClient.putObject(aliOssConfig.getBucketName(), objectName, file.getInputStream());
        // 关闭OSSClient。
        ossClient.shutdown();
        String url = aliOssConfig.getEndpoint() + File.separator + objectName;
        return url;
    }
}
