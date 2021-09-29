package com.aurora.file.service;

import org.springframework.web.multipart.MultipartFile;


/**
 * describe: 文件上传接口
 *
 * @Author Guo Huaijian
 * @Date 2021/9/10
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface FileService {

    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    String uploadFile(MultipartFile file) throws Exception;
}
