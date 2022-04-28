package com.aurora.rpc.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * describe: 文件上传接口
 *
 * @author Guo Huaijian
 * @date 2021/10/25
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
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
