package com.aurora.common.core.exception;

/**
 * describe: 用户信息异常类
 *
 * @Author Guo Huaijian
 * @Date 2021/10/15
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
