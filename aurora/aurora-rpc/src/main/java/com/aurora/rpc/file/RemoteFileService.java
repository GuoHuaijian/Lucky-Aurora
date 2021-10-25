package com.aurora.rpc.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * describe: 文件上传接口
 *
 * @Author Guo Huaijian
 * @Date 2021/10/25
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface RemoteFileService {

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    Map<String, String> upload(MultipartFile file);
}
