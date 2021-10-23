package com.aurora.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/28
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.aurora.*"}, exclude = {DataSourceAutoConfiguration.class})
public class AuroraGatewayApplication {
    // ----------------------------- 解决跨域 Begin -----------------------------

//    private static final String ALL = "*";
//    private static final String MAX_AGE = "3600L";
//
//    @Bean
//    public WebFilter corsFilter() {
//        return (ServerWebExchange ctx, WebFilterChain chain) -> {
//            ServerHttpRequest request = ctx.getRequest();
//            if (!CorsUtils.isCorsRequest(request)) {
//                return chain.filter(ctx);
//            }
//            HttpHeaders requestHeaders = request.getHeaders();
//            ServerHttpResponse response = ctx.getResponse();
//            HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
//            HttpHeaders headers = response.getHeaders();
//            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
//            headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders.getAccessControlRequestHeaders());
//            if (requestMethod != null) {
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
//            }
//            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
//            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, ALL);
//            headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
//            if (request.getMethod() == HttpMethod.OPTIONS) {
//                response.setStatusCode(HttpStatus.OK);
//                return Mono.empty();
//            }
//            return chain.filter(ctx);
//        };
//    }

    // ----------------------------- 解决跨域 End -----------------------------

    public static void main(String[] args) {
        SpringApplication.run(AuroraGatewayApplication.class, args);
    }

}
