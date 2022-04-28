package com.aurora.system.controller;

import com.aurora.common.core.utils.StringUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.system.domain.RegisterBody;
import com.aurora.system.service.SysConfigService;
import com.aurora.system.service.impl.SysRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/10
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RestController
public class SysRegisterController extends AbstractController {
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private SysConfigService configService;

    @PostMapping("/register")
    public Result register(@RequestBody RegisterBody user) {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser")))) {
            return Result.error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtil.isEmpty(msg) ? Result.success() : Result.error(msg);
    }
}
