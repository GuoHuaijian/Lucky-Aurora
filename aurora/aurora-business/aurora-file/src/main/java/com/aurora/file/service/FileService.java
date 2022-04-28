package com.aurora.file.service;

import org.springframework.web.multipart.MultipartFile;


/**
 * describe: 文件上传接口
 *
 * @author Guo Huaijian
 * @date 2021/9/10
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
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
