package com.aurora.file.service.rpc;

import com.aurora.common.core.utils.file.FileUtil;
import com.aurora.file.service.FileService;
import com.aurora.rpc.file.RemoteFileService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * describe: 文件上传接口
 *
 * @author Guo Huaijian
 * @date 2021/10/25
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Slf4j
@DubboService(version = "1.0.0")
public class RemoteFileServiceImpl implements RemoteFileService {

    @Resource
    private FileService fileService;

    /**
     * 文件上传
     */
    @Override
    public Map<String, String> upload(MultipartFile file) {
        try {
            // 上传并返回访问地址
            String url = fileService.uploadFile(file);
            HashMap<String, String> fileInfo = Maps.newHashMap();
            fileInfo.put("name", FileUtil.getName(url));
            fileInfo.put("url", url);
            return fileInfo;
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return Maps.newHashMap();
        }
    }
}
