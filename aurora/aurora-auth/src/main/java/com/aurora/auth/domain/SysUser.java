package com.aurora.auth.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/11/2
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class SysUser implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户电话
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 用户头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 用户状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 性别 0男 1女
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 用户类型 0后台 1门户 3第三方
     */
    @TableField(value = "user_type")
    private Integer userType;

    /**
     * 登录ip
     */
    @TableField(value = "login_ip")
    private String loginIp;

    /**
     * 登录时间
     */
    @TableField(value = "login_time")
    private Date loginTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;
}
