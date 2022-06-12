package com.aurora.rpc.system.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2022/6/12
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Data
public class User implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户电话
     */
    private String phoneNumber;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 性别 0男 1女
     */
    private Integer sex;

    /**
     * 用户类型 0后台 1门户 3第三方
     */
    private Integer userType;
}
