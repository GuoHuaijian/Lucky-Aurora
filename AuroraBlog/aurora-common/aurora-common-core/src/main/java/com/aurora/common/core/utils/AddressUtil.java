package com.aurora.common.core.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/7 14:21
 * @Version 1.0
 */
@Slf4j
public class AddressUtil {

    private static final String AIL_YUN_ADDRESS = "http://ip.aliyun.com/outGetIpInfo";

    /**
     * 根据ip获取地址
     *
     * @param ip
     * @return
     */
    public static String getAddress(String ip) {
        String address;
        try {
            HashMap<String, Object> paramMap = new HashMap<>(2);
            paramMap.put("ip", ip);
            paramMap.put("accessKey", "alibaba-inc");
            String str = HttpUtil.post(AIL_YUN_ADDRESS, paramMap);
            JSONObject obj = JSONObject.parseObject(str);
            JSONObject addressObject = (JSONObject) obj.get("data");
            Integer code = (Integer) obj.get("code");
            //code的值的含义为0：成功，1：服务器异常，2：请求参数异常，3：服务器繁忙，4：个人qps超出
            if (code.equals(0)) {
                address = addressObject.get("country") + "-" + addressObject.get("region") + "-"
                        + addressObject.get("city") + "-" + addressObject.get("area");
            } else {
                switch (code) {
                    case 1:
                        address = "服务器异常";
                        break;
                    case 2:
                        address = "请求参数异常";
                        break;
                    case 3:
                        address = "服务器繁忙";
                        break;
                    case 4:
                        address = "个人qps超出";
                        break;
                    default:
                        address = "";
                }
            }
        } catch (Exception e) {
            log.debug("获取IP地址异常" + e);
            address = "获取IP地址异常：" + e.getMessage();
        }
        return address;
    }
}
