package com.aurora.common.security.filter;

import com.aurora.common.security.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * describe: Token过滤器，用于验证Token是否合法
 *
 * @author Guo Huaijian
 * @date 2021/1/1
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        // 取出Token
        String token = SecurityUtil.getRequestToken(request);
        if (token != null && token.startsWith(SecurityUtil.tokenPrefix)) {
            List<String> urls = new ArrayList<>();
            urls.add("/system/login");
            urls.add("/system/captchaImage");
            boolean flag = urls.contains(request.getRequestURI());
            if (flag) {
                request.removeAttribute(SecurityUtil.tokenHeader);
            }
            log.info("请求token:{}", token);
        }
        filterChain.doFilter(request, response);
    }

}
