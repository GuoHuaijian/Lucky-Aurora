package com.aurora.system.domain;

import lombok.Data;

/**
 * describe: 用户登录对象
 *
 * @author Guo Huaijian
 * @date 2021/10/15
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Data
public class LoginBody {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid = "";
}
