package com.aurora.common.security.filter;

import com.aurora.common.security.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * describe: Token过滤器，用于验证Token是否合法
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        // 取出Token
        String token = SecurityUtil.getToken(request);
        if (token != null && token.startsWith(SecurityUtil.tokenPrefix)) {
            log.info("请求token:{}", token);
        }
        filterChain.doFilter(request, response);
    }

}
