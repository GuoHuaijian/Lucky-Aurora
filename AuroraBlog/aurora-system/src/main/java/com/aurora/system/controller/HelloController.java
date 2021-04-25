package com.aurora.system.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/2
 * @E-mail 564559079@qq.com
 * @Version 1.0
 */
@RestController
@RequestMapping("test")
public class HelloController {

    @PreAuthorize(value = "hasPermission('sys:user:list,sys:user:info','ddd')")
    @GetMapping("hi")
    public String get() {
        return "hello";
    }

    @PreAuthorize("@ss.hasPermi('aa')")
    @GetMapping("hello")
    public String get1() {
        return "hello";
    }
}
