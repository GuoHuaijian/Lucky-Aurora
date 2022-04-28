package com.aurora.common.core.exception;

/**
 * describe: 用户信息异常类
 *
 * @author Guo Huaijian
 * @date 2021/10/15
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
