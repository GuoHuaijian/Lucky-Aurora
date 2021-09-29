package com.aurora.file.service.impl;

import com.aurora.file.config.FileConfig;
import com.aurora.file.service.FileService;
import com.aurora.file.utils.FileUploadUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * describe: 本地文件存储
 *
 * @Author Guo Huaijian
 * @Date 2021/9/10
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Primary
@Service
public class LocalFileServiceImpl implements FileService {

    @Resource
    private FileConfig config;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        FileConfig.Local local = config.getLocal();
        String name = FileUploadUtil.upload(local.getPath(), file);
        String url = local.getDomain() + local.getPrefix() + name;
        return url;
    }
}
