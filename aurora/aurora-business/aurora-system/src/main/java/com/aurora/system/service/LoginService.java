package com.aurora.system.service;

import com.aurora.system.domain.LoginBody;
import com.aurora.system.domain.router.Router;

import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/9
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param loginBody
     * @return
     */
    String login(LoginBody loginBody);

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
