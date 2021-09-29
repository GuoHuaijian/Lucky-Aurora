package com.aurora.common.core.exception;

/**
 * describe: 自定义用户校验异常
 *
 * @Author Guo Huaijian
 * @Date 2020/12/31
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public class UserNotFindException extends RuntimeException {

    public UserNotFindException() {
        super("用户信息不存在");
    }

    public UserNotFindException(String message) {
        super(message);
    }

    public UserNotFindException passwordError(String message) {
        return new UserNotFindException(message);
    }

    public UserNotFindException passwordError() {
        return new UserNotFindException("用户密码错误");
    }
}
