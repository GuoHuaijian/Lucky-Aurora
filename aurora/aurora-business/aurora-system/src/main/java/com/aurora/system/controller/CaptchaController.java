package com.aurora.system.controller;

import cn.hutool.core.codec.Base64;
import com.aurora.common.core.constant.Constants;
import com.aurora.common.core.utils.IdUtil;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.redis.RedisCache;
import com.aurora.system.service.SysConfigService;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * describe: 验证码
 *
 * @author Guo Huaijian
 * @date 2021/10/15
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RequestMapping("system")
@RestController
public class CaptchaController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    /**
     * 验证码类型
     */
    @Value("${aurora.captchaType}")
    private String captchaType;

    @Autowired
    private SysConfigService configService;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public Result getCode() {
        Result result = Result.success();
        boolean captchaOnOff = configService.selectCaptchaOnOff();
        result.put("captchaOnOff", captchaOnOff);
        if (!captchaOnOff) {
            return result;
        }

        // 保存验证码信息
        String uuid = IdUtil.simpleUUID();
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;

        String capStr, code = null;
        BufferedImage image = null;

        // 生成验证码
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return Result.error(e.getMessage());
        }

        result.put("uuid", uuid);
        result.put("img", Base64.encode(os.toByteArray()));
        return result;
    }
}
