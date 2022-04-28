package com.aurora.common.security.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * describe:放行url
 *
 * @author Guo Huaijian
 * @date 2021/1/1
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "security.oauth2.ignore")
public class IgnoreUrlProperties {

    private static final Pattern PATTERN = Pattern.compile("\\{(.*?)\\}");

    public List<String> urls = new ArrayList<>();

    public String[] getUrlStr() {
        String[] str = urls.toArray(new String[urls.size()]);
        log.info("放行白名单:{}", Arrays.toString(str));
        return str;
    }
}
