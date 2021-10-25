package com.aurora.monitor.controller;

import com.aurora.common.core.web.domain.Result;
import com.aurora.monitor.domain.Server;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * describe: 服务器监控
 *
 * @Author Guo Huaijian
 * @Date 2021/10/25
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController {

    @PreAuthorize("hasAuthority('monitor:server:list')")
    @GetMapping()
    public Result getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return Result.success(server);
    }
}
