package com.aurora.file.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aurora.file.config.FileConfig;
import com.aurora.file.service.FileService;
import com.aurora.file.utils.FileUploadUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * /**
 * describe: 阿里云文件上传
 *
 * @author Guo Huaijian
 * @date 2021/9/10
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Service
public class AliOssFileServiceImpl implements FileService {

    @Resource
    private FileConfig config;

    @Resource
    private OSSClient ossClient;

    @Bean
    public OSSClient getOSSClient() {
        return (OSSClient) new OSSClientBuilder().build(config.getAliOss().getEndpoint(), config.getAliOss().getAccessKeyId(),
                config.getAliOss().getAccessKeySecret());
    }


    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String fileName = FileUploadUtil.extractFilename(file);
        String objectName = config.getAliOss().getObjectName() + File.separator + fileName;
        ossClient.putObject(config.getAliOss().getBucketName(), objectName, file.getInputStream());
        // 关闭OSSClient。
        ossClient.shutdown();
        String url = config.getAliOss().getEndpoint() + File.separator + objectName;
        return url;
    }
}
