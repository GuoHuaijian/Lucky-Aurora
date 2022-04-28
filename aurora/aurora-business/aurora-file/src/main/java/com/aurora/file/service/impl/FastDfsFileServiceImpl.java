package com.aurora.file.service.impl;

import com.aurora.file.config.FileConfig;
import com.aurora.file.service.FileService;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * describe: FastDFS 文件存储
 *
 * @author Guo Huaijian
 * @date 2021/9/10
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Service
public class FastDfsFileServiceImpl implements FileService {

    @Resource
    private FileConfig config;

    @Resource
    private FastFileStorageClient storageClient;

    /**
     * FastDfs文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return config.getFastDfs().getDomain() + "/" + storePath.getFullPath();
    }
}
