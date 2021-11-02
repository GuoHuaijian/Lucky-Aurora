package com.aurora.system.common.factory;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.aurora.common.core.constant.Constants;
import com.aurora.common.core.utils.LogUtil;
import com.aurora.common.core.utils.ServletUtil;
import com.aurora.common.core.utils.SpringUtil;
import com.aurora.common.core.utils.ip.AddressUtil;
import com.aurora.common.core.utils.ip.IpUtil;
import com.aurora.system.domain.SysLoginLog;
import com.aurora.system.service.SysLoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.TimerTask;

/**
 * describe: 日志异步工厂（产生任务用）
 *
 * @Author Guo Huaijian
 * @Date 2021/10/15
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Slf4j
public class LogAsyncFactory {

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLoginLog(final String username, final String status, final String message,
                                           final Object... args) {
        final UserAgent userAgent = UserAgentUtil.parse(ServletUtil.getRequest().getHeader("User-Agent"));
        final String ip = IpUtil.getIpAddr(ServletUtil.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtil.getAddress(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtil.getBlock(ip));
                s.append(address);
                s.append(LogUtil.getBlock(username));
                s.append(LogUtil.getBlock(status));
                s.append(LogUtil.getBlock(message));
                // 打印信息到日志
                log.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOs().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLoginLog loginLog = new SysLoginLog();
                loginLog.setUserName(username);
                loginLog.setLoginIp(ip);
                loginLog.setLoginLocation(address);
                loginLog.setBrowser(browser);
                loginLog.setOs(os);
                loginLog.setMsg(message);
                loginLog.setLoginTime(new Date());
                // 日志状态
                if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
                    loginLog.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    loginLog.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtil.getBean(SysLoginLogService.class).insertLoginLog(loginLog);
            }
        };
    }
}
