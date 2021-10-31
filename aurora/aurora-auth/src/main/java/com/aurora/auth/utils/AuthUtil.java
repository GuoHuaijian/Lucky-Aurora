package com.aurora.auth.utils;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.enums.scope.AuthGithubScope;
import me.zhyd.oauth.enums.scope.AuthWeiboScope;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeiboRequest;
import me.zhyd.oauth.utils.AuthScopeUtils;

import java.util.Arrays;

/**
 * describe: 授权请求工具类
 *
 * @Author Guo Huaijian
 * @Date 2021/10/29
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public class AuthUtil {

    /**
     * 根据具体的授权来源，获取授权请求
     *
     * @param source
     * @return
     */
    public static AuthRequest getAuthRequest(String source) {
        AuthRequest authRequest = null;
        switch (source.toLowerCase()) {
            case "github":
                authRequest = new AuthGithubRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8080/auth")
                        .scopes(AuthScopeUtils.getScopes(AuthGithubScope.values())).build());
                break;
            case "gitee":
                authRequest = new AuthGiteeRequest(AuthConfig.builder()
                        .clientId("aecd1f6cff7e4c27344c9c09b03807160dd7bfca2aaf5bac471775c8499de2ac")
                        .clientSecret("c2134f5af3379722dfc651e2ab12d88a8ecfe901b65e279ff42ecd19a362f845")
                        .redirectUri("http://127.0.0.1:8080/auth")
                        .build());
                break;
            case "weibo":
                authRequest = new AuthWeiboRequest(AuthConfig.builder()
                        .clientId("")
                        .clientSecret("")
                        .redirectUri("http://127.0.0.1:8080/auth")
                        .scopes(Arrays.asList(
                                AuthWeiboScope.EMAIL.getScope(),
                                AuthWeiboScope.FRIENDSHIPS_GROUPS_READ.getScope(),
                                AuthWeiboScope.STATUSES_TO_ME_READ.getScope()
                        ))
                        .build());
                break;
            default:
                break;
        }
        if (null == authRequest) {
            throw new AuthException("未获取到有效的Auth配置");
        }
        return authRequest;
    }
}
