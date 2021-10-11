package com.aurora.system;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;

@SpringBootTest
class AuroraSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 根据ip获取地址
     *
     * @param ip
     * @return
     */
    public static String getAddress(String ip) {
        String resout = "";
        try {
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("ip", ip);
            map.put("accessKey", "alibaba-inc");
            String str = HttpUtil.post("http://ip.aliyun.com/outGetIpInfo", map);
            System.out.println(str);
            JSONObject obj = JSONObject.parseObject(str);
            JSONObject obj2 = (JSONObject) obj.get("data");
            Integer code = (Integer) obj.get("code");
            //code的值的含义为，0：成功，1：服务器异常，2：请求参数异常，3：服务器繁忙，4：个人qps超出
            if (code.equals(0)) {
                resout = obj2.get("country") + "--" + obj2.get("area") + "--" + obj2.get("city") + "--" + obj2.get("isp");
            } else {
                resout = "IP地址有误";
            }
        } catch (Exception e) {
            e.printStackTrace();
            resout = "获取IP地址异常：" + e.getMessage();
        }
        System.out.println("result: " + resout);
        return resout;
    }


    public static void main(String[] args) throws IOException {
        getAddress("124.64.22.252");
    }

}
