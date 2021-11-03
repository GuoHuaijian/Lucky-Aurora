package com.aurora.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/11/03
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthInfo implements Serializable {

    /**
     * 授权令牌
     */
    private String access_token;

    /**
     * 授权类型
     */
    private String token_type;

    /**
     * 刷新令牌
     */
    private String refresh_token;

    /**
     * 令牌的有效期
     */
    private Integer expires_in;

    /**
     * 授予的权限
     */
    private String scope;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;
}
