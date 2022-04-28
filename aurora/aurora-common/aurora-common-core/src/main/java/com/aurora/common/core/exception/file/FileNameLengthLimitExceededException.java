package com.aurora.common.core.exception.file;

/**
 * describe: 文件名称超长限制异常类
 *
 * @author Guo Huaijian
 * @date 2021/10/11
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
public class FileNameLengthLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super("upload.filename.exceed.length", new Object[]{defaultFileNameLength});
    }
}
