package com.aurora.auth.service.impl;

import com.aurora.auth.domain.SysThirdAuth;
import com.aurora.auth.mapper.SysThirdAuthMapper;
import com.aurora.auth.service.SysThirdAuthService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/11/2
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Service
public class SysThirdAuthServiceImpl extends ServiceImpl<SysThirdAuthMapper, SysThirdAuth> implements SysThirdAuthService {

    /**
     * 根据第三方uuid和第三方平台获取得到的授权信息
     *
     * @param uuid   第三方系统的唯一ID
     * @param source 第三方用户来源
     * @return
     */
    @Override
    public SysThirdAuth getThirdAuth(String uuid, String source) {
        SysThirdAuth thirdAuth = this.getOne(new LambdaQueryWrapper<SysThirdAuth>().eq(SysThirdAuth::getUuid, uuid).eq(SysThirdAuth::getSource,
                source));
        return thirdAuth;
    }
}
