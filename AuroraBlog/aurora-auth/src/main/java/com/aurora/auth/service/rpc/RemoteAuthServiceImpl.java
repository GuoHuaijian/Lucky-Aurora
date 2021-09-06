package com.aurora.auth.service.rpc;

import com.aurora.auth.service.LoginService;
import com.aurora.rpc.auth.RemoteAuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;


/**
 * describe: 登录方法实现
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@DubboService(version = "1.0.0", interfaceClass = RemoteAuthService.class)
@Slf4j
public class RemoteAuthServiceImpl implements RemoteAuthService {

    @Resource
    private LoginService loginService;

    /**
     * 获取token
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public String createToken(String username, String password) {
        String token = loginService.createToken(username, password);
        return token;
    }
}
