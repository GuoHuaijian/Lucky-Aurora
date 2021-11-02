package com.aurora.auth.service;

import com.aurora.auth.domain.SysThirdAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zhyd.oauth.model.AuthUser;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/11/2
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface SysThirdAuthService extends IService<SysThirdAuth> {

    /**
     * 根据第三方uuid和第三方平台获取得到的授权信息
     *
     * @param uuid   第三方系统的唯一ID
     * @param source 第三方用户来源
     * @return
     */
    SysThirdAuth getThirdAuth(String uuid, String source);

    /**
     * 新增第三方授权信息
     *
     * @param user
     * @return
     */
    SysThirdAuth addThirdAuth(AuthUser user);

    /**
     * 更新第三方授权信息
     *
     * @param authUser
     * @param authId
     */
    void updateThirdAuth(AuthUser authUser, Integer authId);
}
