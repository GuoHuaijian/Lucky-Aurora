package com.aurora.common.core.exception;

/**
 * describe: 自定义用户校验异常
 *
 * @author Guo Huaijian
 * @date 2020/12/31
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
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
