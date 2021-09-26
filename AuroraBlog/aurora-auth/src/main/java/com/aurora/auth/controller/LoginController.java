package com.aurora.auth.controller;

import cn.hutool.http.HttpUtil;
import com.aurora.auth.service.LoginService;
import org.apache.http.Header;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * describe: 登陆
 *
 * @Author Guo
 * @Date 2021/9/6 12:40
 * @Version 1.0
 */
@RequestMapping("login")
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("user")
    public String login(String username, String password) {
        // 获取code
        String codeUrl = "http://localhost:8081/oauth/authorize";
        HashMap<String, Object> map = new HashMap<>();
        map.put("client_id","aurora_client");
        map.put("response_type","code");
        map.put("edirect_uri","www.baidu.com");
        map.put("username", username);
        map.put("password", password);
        String s1 = HttpUtil.get(codeUrl, map);
        String s = doPost(codeUrl, map);
        System.out.println("============="+s1);
        System.out.println(s);
//        String s = HttpUtil.get(codeUrl, map);
//        System.out.println("=============");
//        System.out.println(s);
//        Map<String, Object> jsonMap = (Map) JSONObject.parse(s);
//        System.out.println(jsonMap);
//        String token = loginService.createToken(username, password);
        return "token";
    }

    public static String doPost(String url, Map<String, Object> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, (String) param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }

            // 执行http请求
            response = httpClient.execute(httpPost);
            // 从从定向地址中截取code
            if (response.getStatusLine().getStatusCode() == 302) {
                Header header = response.getFirstHeader("Location");
                String location = header.getValue();
                resultString = location.substring(location.indexOf("=") + 1, location.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

}
