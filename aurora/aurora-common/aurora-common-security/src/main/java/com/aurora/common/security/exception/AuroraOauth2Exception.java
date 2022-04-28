package com.aurora.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/16
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@JsonSerialize(using = AuroraOauth2ExceptionSerializer.class)
public class AuroraOauth2Exception extends OAuth2Exception {
    public AuroraOauth2Exception(String msg) {
        super(msg);
    }
}
