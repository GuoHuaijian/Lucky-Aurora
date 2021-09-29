package com.aurora.common.security.annotation;

import com.aurora.common.security.config.AuroraSecurityAutoConfigure;
import com.aurora.common.security.config.ResourceServerConfig;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/27
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Inherited
@Documented
@EnableResourceServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Import({ResourceServerConfig.class, AuroraSecurityAutoConfigure.class})
public @interface EnableAuroraResourceServer {
}
