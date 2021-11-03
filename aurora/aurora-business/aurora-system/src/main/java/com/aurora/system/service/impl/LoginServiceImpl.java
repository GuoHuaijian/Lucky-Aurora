package com.aurora.system.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.aurora.common.core.constant.Constants;
import com.aurora.common.core.exception.CaptchaException;
import com.aurora.common.core.exception.CaptchaExpireException;
import com.aurora.common.core.exception.ServiceException;
import com.aurora.common.core.manager.AsyncManager;
import com.aurora.common.core.utils.DateUtil;
import com.aurora.common.core.utils.MessageUtil;
import com.aurora.common.core.utils.ServletUtil;
import com.aurora.common.core.utils.StringUtil;
import com.aurora.common.core.utils.ip.IpUtil;
import com.aurora.common.redis.RedisCache;
import com.aurora.common.security.utils.SecurityUtil;
import com.aurora.system.common.factory.LogAsyncFactory;
import com.aurora.system.domain.LoginBody;
import com.aurora.system.domain.SysMenu;
import com.aurora.system.domain.SysUser;
import com.aurora.system.domain.AuthInfo;
import com.aurora.system.domain.router.Router;
import com.aurora.system.service.LoginService;
import com.aurora.system.service.SysConfigService;
import com.aurora.system.service.SysMenuService;
import com.aurora.system.service.SysUserService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/9
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private OAuth2ProtectedResourceDetails resourceDetails;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysMenuService menuService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysConfigService configService;

    /**
     * 登录
     *
     * @param loginBody
     * @return
     */
    @Override
    public String login(LoginBody loginBody) {
        boolean captchaOnOff = configService.selectCaptchaOnOff();
        String userName = loginBody.getUsername();
        // 验证码开关
        if (captchaOnOff) {
            validateCaptcha(userName, loginBody.getCode(), loginBody.getUuid());
        }
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
        paramMap.put("password", loginBody.getPassword());
        paramMap.put("username", userName);
        AuthInfo authInfo;
        try {
            // 获取token
            String result = HttpUtil.post(tokenUri, paramMap);
            authInfo = JSONObject.parseObject(result, AuthInfo.class);
        } catch (Exception e) {
            log.debug(e.getMessage());
            AsyncManager.me().execute(LogAsyncFactory.recordLoginLog(userName, Constants.LOGIN_FAIL,
                    StringUtil.substring("获取token异常", 0, 200)));
            throw new ServiceException("获取token异常");
        }
        String token = authInfo.getAccess_token();
        AsyncManager.me().execute(LogAsyncFactory.recordLoginLog(userName, Constants.LOGIN_SUCCESS, MessageUtil.message("user.login.success")));
        recordLoginInfo(userName);
        return token;
    }

    /**
     * 获取用户详细信息
     *
     * @return
     */
    @Override
    public Map<String, Object> getInfo() {
        Long userId = SecurityUtil.getUserId();
        SysUser user = userService.selectUserById(userId);
        Set<String> roles = SecurityUtil.getUserRoles();
        Set<String> auths = SecurityUtil.getUserAuths();
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

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            AsyncManager.me().execute(LogAsyncFactory.recordLoginLog(username, Constants.LOGIN_FAIL,
                    MessageUtil.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(LogAsyncFactory.recordLoginLog(username, Constants.LOGIN_FAIL, MessageUtil.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(String userName) {
        SysUser user = new SysUser();
        user.setUserName(userName);
        user.setLoginIp(IpUtil.getIpAddr(ServletUtil.getRequest()));
        user.setLoginTime(DateUtil.getNowDate());
        userService.updateUserProfile(user);
    }
}
