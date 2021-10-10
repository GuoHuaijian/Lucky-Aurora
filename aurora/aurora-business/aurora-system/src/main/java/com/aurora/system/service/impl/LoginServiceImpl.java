package com.aurora.system.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.aurora.common.security.utils.SecurityUtil;
import com.aurora.system.domain.SysMenu;
import com.aurora.system.domain.SysUser;
import com.aurora.system.domain.router.Router;
import com.aurora.system.service.LoginService;
import com.aurora.system.service.SysMenuService;
import com.aurora.system.service.SysUserService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private OAuth2ProtectedResourceDetails resourceDetails;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysMenuService menuService;

    /**
     * 获取token
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public Object login(String username, String password) {
        String tokenUri = resourceDetails.getAccessTokenUri();
        String clientId = resourceDetails.getClientId();
        String clientSecret = resourceDetails.getClientSecret();
        String grantType = resourceDetails.getGrantType();
        List<String> scope = resourceDetails.getScope();
        HashMap<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("client_id", clientId);
        paramMap.put("client_secret", clientSecret);
        paramMap.put("grant_type", grantType);
        paramMap.put("scope", scope);
        paramMap.put("password", password);
        paramMap.put("username", username);
        String result = HttpUtil.post(tokenUri, paramMap);
        System.out.println(result);
        return JSONObject.parse(result);
    }

    /**
     * 获取用户详细信息
     *
     * @return
     */
    @Override
    public Map<String, Object> getInfo() {
        SysUser user = userService.getUserByName(SecurityUtil.getUsername());
        List<String> roles = userService.getRolesByUserId(SecurityUtil.getUserId());
        List<String> auths = userService.getAuthsByUserId(SecurityUtil.getUserId());
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("user", user);
        map.put("roles", roles);
        map.put("permissions", auths);
        return map;
    }

    /**
     * 获取前端路由
     *
     * @return
     */
    @Override
    public List<Router> getRouters() {
        Long userId = SecurityUtil.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return menuService.buildMenus(menus);
    }
}
