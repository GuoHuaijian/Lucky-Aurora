package com.aurora.file.service.impl;

import com.aurora.file.config.MinioConfig;
import com.aurora.file.service.FileService;
import com.aurora.file.utils.FileUploadUtil;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * describe: 文件存储
 *
 * @Author Guo
 * @Date 2021/9/10
 * @Version 1.0
 */
@Service
public class MinioFileServiceImpl implements FileService {

    @Resource
    private MinioConfig minioConfig;

    @Resource
    private MinioClient client;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String fileName = FileUploadUtil.extractFilename(file);
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(minioConfig.getBucketName())
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build();
        client.putObject(args);
        return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
    }
}
