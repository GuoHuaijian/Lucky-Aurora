package com.aurora.common.core.manager;

import com.aurora.common.core.utils.SpringUtil;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.TimerTask;

/**
 * describe: 异步任务管理器
 *
 * @author Guo Huaijian
 * @date 2021/10/15
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
public class AsyncManager {
    /**
     * 操作延迟10毫秒
     */
    private final int OPERATE_DELAY_TIME = 10;

    /**
     * 异步操作任务调度线程池
     */
    private ThreadPoolTaskExecutor executor = SpringUtil.getBean("asyncExecutor");

    /**
     * 单例模式
     */
    private AsyncManager() {
    }

    private static AsyncManager me = new AsyncManager();

    public static AsyncManager me() {
        return me;
    }

    /**
     * 执行任务
     *
     * @param task 任务
     */
    public void execute(TimerTask task) {
        executor.execute(task, OPERATE_DELAY_TIME);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        executor.shutdown();
    }
}
