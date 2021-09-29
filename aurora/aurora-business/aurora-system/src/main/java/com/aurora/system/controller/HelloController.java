package com.aurora.system.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/2
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@RestController
@RequestMapping("test")
public class HelloController {

//    @PreAuthorize(value = "hasPermission('sys:user:list,sys:user:info','ddd')")
    @GetMapping("hi")
    public String get() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return "hello";
    }

    @PreAuthorize("@ss.hasAuthority('system:role:list')")
    @GetMapping("hello")
    public String get1() {
        return "hello";
    }

    @PreAuthorize("hasAuthority('system:role:list')")
    @GetMapping("hell")
    public String get2() {
        return "hello";
    }

}
