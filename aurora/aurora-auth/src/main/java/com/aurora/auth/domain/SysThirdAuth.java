package com.aurora.auth.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
@TableName(value = "sys_third_auth")
public class SysThirdAuth implements Serializable {
    /**
     * 主建
     */
    @TableId(value = "auth_id", type = IdType.AUTO)
    private Integer authId;

    /**
     * 第三方系统的唯一ID
     */
    @TableField(value = "uuid")
    private String uuid;

    /**
     * 第三方用户来源
     */
    @TableField(value = "source")
    private String source;

    /**
     * 用户的授权令牌
     */
    @TableField(value = "access_token")
    private String accessToken;

    /**
     * 第三方用户的授权令牌的有效期
     */
    @TableField(value = "expire_in")
    private Integer expireIn;

    /**
     * 刷新令牌
     */
    @TableField(value = "refresh_token")
    private String refreshToken;

    /**
     * 第三方用户的 open id
     */
    @TableField(value = "open_id")
    private String openId;

    /**
     * 第三方用户的 ID
     */
    @TableField(value = "uid")
    private String uid;

    /**
     * 个别平台的授权信息
     */
    @TableField(value = "access_code")
    private String accessCode;

    /**
     * 第三方用户的 union id
     */
    @TableField(value = "union_id")
    private String unionId;

    /**
     * 第三方用户授予的权限
     */
    @TableField(value = "scope")
    private String scope;

    /**
     * 个别平台的授权信息
     */
    @TableField(value = "token_type")
    private String tokenType;

    /**
     * id token
     */
    @TableField(value = "id_token")
    private String idToken;

    /**
     * 小米平台用户的附带属性
     */
    @TableField(value = "mac_algorithm")
    private String macAlgorithm;

    /**
     * 小米平台用户的附带属性
     */
    @TableField(value = "mac_key")
    private String macKey;

    /**
     * 用户的授权code
     */
    @TableField(value = "code")
    private String code;

    /**
     * Twitter平台用户的附带属性
     */
    @TableField(value = "oauth_token")
    private String oauthToken;

    /**
     * Twitter平台用户的附带属性
     */
    @TableField(value = "oauth_token_secret")
    private String oauthTokenSecret;

    private static final long serialVersionUID = 1L;
}
