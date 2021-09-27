package com.aurora.common.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * describe:放行url
 *
 * @Author Guo Huaijian
 * @Date 2021/1/1
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "security.oauth2.ignore")
public class IgnoreUrlProperties{

//    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

    public List<String> urls = new ArrayList<>();

    private String hello;



//    public static void main(String[] args) {
//        String url = "ni/,/hao/**";
//        String s = ReUtil.replaceAll(url, PATTERN, "*");
//        System.out.println(s);
//    }

}
