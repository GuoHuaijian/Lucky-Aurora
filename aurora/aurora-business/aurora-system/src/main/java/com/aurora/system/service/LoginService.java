package com.aurora.system.service;

import com.aurora.system.domain.router.Router;

import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/9
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface LoginService {

    /**
     * 获取token
     *
     * @param username
     * @param password
     * @return
     */
    Object login(String username, String password);

    /**
     * 获取用户详细信息
     *
     * @return
     */
    Map<String, Object> getInfo();

    /**
     * 获取前端路由
     *
     * @return
     */
    List<Router> getRouters();
}