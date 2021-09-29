package com.aurora.file.service.impl;

import com.aurora.file.config.FileConfig;
import com.aurora.file.service.FileService;
import com.aurora.file.utils.FileUploadUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * describe: 七牛云文件存储
 *
 * @Author Guo
 * @Date 2021/9/10 14:22
 * @Version 1.0
 */
@Service
@Slf4j
public class QiNiuFileServiceImpl implements FileService {

    @Resource
    private FileConfig config;

    /**
     * 文件删除
     *
     * @param file 上传的文件
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file) {
        Configuration configuration = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(configuration);
        Auth auth = Auth.create(config.getQiNiu().getAccessKey(), config.getQiNiu().getSecretKey());
        String token = auth.uploadToken(config.getQiNiu().getBucket());
        Response response = null;
        //生成文件名
        String fileName = FileUploadUtil.extractFilename(file);
        String path = null;
        try {
            response = uploadManager.put(file.getInputStream(), fileName, token, null, null);
            DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            path = config.getQiNiu().getPath() + File.separator + defaultPutRet.key + File.separator + fileName;
            log.info("上传文件到七牛云服务器成功{}", path);
        } catch (QiniuException e) {
            Response r = e.response;
            log.error(r.toString());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            response.close();
        }
        return path;
    }
}
