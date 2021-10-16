package com.aurora.common.core.exception;


/**
 * describe: 验证码错误异常类
 *
 * @Author Guo Huaijian
 * @Date 2021/10/15
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }
}
