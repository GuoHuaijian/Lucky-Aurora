package com.aurora.common.config.config.dubbo.fallback;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;

/**
 * describe: 消费者降级
 *
 * @Author Guo Huaijian
 * @Date 2021/10/28
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Slf4j
public class ConsumerFallback implements DubboFallback {
    @Override
    public Result handle(Invoker<?> invoker, Invocation invocation, BlockException ex) {
        log.info("捕获到block异常，消费者降级处理", ex);
        return AsyncRpcResult.newDefaultAsyncResult("服务器处理不过来啦", invocation);
    }
}
