package com.aurora.common.log.aspect;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.aurora.common.core.utils.ServletUtil;
import com.aurora.common.core.utils.StringUtil;
import com.aurora.common.core.utils.ip.AddressUtil;
import com.aurora.common.core.utils.ip.IpUtil;
import com.aurora.common.log.annotation.VLog;
import com.aurora.common.log.enums.LogStatus;
import com.aurora.common.log.service.AsyncLogService;
import com.aurora.rpc.system.domain.SysVisitLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * describe: 访问日志记录
 *
 * @author Guo Huaijian
 * @date 2021/10/11
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Aspect
@Component
@Slf4j
public class VisitLogAspect {

    @Resource
    private AsyncLogService asyncLogService;

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.aurora.common.log.annotation.VLog)")
    public void logPointCut() {
    }

    /**
     * 前置通知 用于拦截操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        handleLog(joinPoint, null);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e) {
        try {
            // 获得注解
            VLog vLog = getAnnotationLog(joinPoint);
            if (vLog == null) {
                return;
            }
            SysVisitLog visitLog = new SysVisitLog();
            visitLog.setStatus(LogStatus.SUCCESS.getCode());
            String ip = IpUtil.getIpAddr(ServletUtil.getRequest());
            visitLog.setVisitIp(ip);
            final UserAgent userAgent = UserAgentUtil.parse(ServletUtil.getRequest().getHeader("User-Agent"));
            // 获取客户端操作系统
            String os = userAgent.getOs().getName();
            // 获取客户端浏览器
            String browser = userAgent.getBrowser().getName();
            visitLog.setOs(os);
            visitLog.setBrowser(browser);
            visitLog.setVisitLocation(AddressUtil.getAddress(ip));
            visitLog.setUrl(ServletUtil.getRequest().getRequestURI());
            if (e != null) {
                visitLog.setStatus(LogStatus.ERROR.getCode());
                visitLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            //设置入口地址
            visitLog.setEntryUrl(ServletUtil.getRequest().getHeader("referer"));
            // 处理设置注解上的参数
            getControllerMethodDescription(vLog, visitLog);
            // 保存数据库
            asyncLogService.saveLog(visitLog);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log      日志
     * @param visitLog 操作日志
     */
    public void getControllerMethodDescription(VLog log, SysVisitLog visitLog) {
        visitLog.setTitle(log.value());
        if (StringUtil.isNotEmpty(log.blogId())) {
            visitLog.setBlogId(Integer.parseInt(log.blogId()));
        }
    }


    /**
     * 是否存在注解，如果存在就获取
     */
    private VLog getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(VLog.class);
        }
        return null;
    }
}
