package com.aurora.file.service.impl;

import com.aurora.file.service.FileService;
import com.aurora.file.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * describe: 本地文件存储
 *
 * @Author Guo
 * @Date 2021/9/10
 * @Version 1.0
 */
@Primary
@Service
public class LocalFileServiceImpl implements FileService {
    /**
     * 资源映射路径 前缀
     */
    @Value("${file.prefix}")
    public String localFilePrefix;

    /**
     * 域名或本机访问地址
     */
    @Value("${file.domain}")
    public String domain;

    /**
     * 上传文件存储在本地的根路径
     */
    @Value("${file.path}")
    private String localFilePath;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String name = FileUploadUtil.upload(localFilePath, file);
        String url = domain + localFilePrefix + name;
        return url;
    }
}
