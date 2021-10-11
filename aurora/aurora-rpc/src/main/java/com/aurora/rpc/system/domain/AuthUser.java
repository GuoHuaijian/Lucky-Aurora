package com.aurora.rpc.system.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/6 11:33
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;


    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户状态
     */
    private Boolean status;

    private static final long serialVersionUID = 1L;
}
