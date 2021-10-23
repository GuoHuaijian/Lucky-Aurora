package com.aurora.file.service.impl;

import com.aurora.file.config.FileConfig;
import com.aurora.file.service.FileService;
import com.aurora.file.utils.FileUploadUtil;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * describe: Minio文件存储
 *
 * @Author Guo Huaijian
 * @Date 2021/9/10
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Primary
@Service
public class MinioFileServiceImpl implements FileService {

    @Resource
    private FileConfig config;

//    @Resource
//    private MinioClient minioClient;

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
                .bucket(config.getMinio().getBucketName())
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build();
        getMinioClient().putObject(args);
        return config.getMinio().getUrl() + "/" + config.getMinio().getBucketName() + "/" + fileName;
    }

    public MinioClient getMinioClient() {
        return MinioClient.builder().endpoint(config.getMinio().getUrl()).credentials(config.getMinio().getAccessKey(), config.getMinio().getSecretKey()).build();
    }
}
