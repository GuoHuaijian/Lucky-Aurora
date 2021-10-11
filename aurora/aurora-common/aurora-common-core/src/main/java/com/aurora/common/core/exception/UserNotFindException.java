package com.aurora.common.core.exception;

/**
 * describe: 自定义用户校验异常
 *
 * @Author Guo
 * @Date 2021/8/26 14:18
 * @Version 1.0
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
