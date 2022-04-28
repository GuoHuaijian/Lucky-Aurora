package com.aurora.common.core.utils.ip;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/9/7
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Slf4j
public class AddressUtil {

    private static final String AIL_YUN_ADDRESS = "http://ip.aliyun.com/outGetIpInfo";

    // 未知地址
    public static final String UNKNOWN = "XX XX";

    /**
     * 根据ip获取地址
     *
     * @param ip
     * @return
     */
    public static String getAddress(String ip) {
        // 内网不查询
        if (IpUtil.internalIp(ip)) {
            return "内网IP";
        }
        String address = UNKNOWN;
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
                // 国家
                String country = (String) addressObject.get("country");
                // 省（自治区或直辖市）
                String region = (String) addressObject.get("region");
                // 市（县）
                String city = (String) addressObject.get("city");
                // 县
                String area = (String) addressObject.get("area");
                // 运营商
                String isp = (String) addressObject.get("isp");
                address = StrUtil.format("{}|{}|{}|{}|{}", country, region, city, area, isp);
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
                log.debug("获取IP地址异常:" + address);
                address = UNKNOWN;
            }
        } catch (Exception e) {
            log.debug("获取IP地址异常" + e);
        }
        return address;
    }
}
