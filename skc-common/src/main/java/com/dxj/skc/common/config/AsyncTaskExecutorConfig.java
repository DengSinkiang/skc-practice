package com.dxj.skc.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author sinkiang
 * @date 2022/7/14 14:21
 */
@Configuration
public class AsyncTaskExecutorConfig {

    // 线程前缀
    private static final String threadNamePrefix = "asyncTaskExecutor-";
    // 阻塞队列
    private static final int workQueue = 20;
    // 线程空闲后的存活时长
    private static final int keepAliveTime = 30;
    // cpu核数
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    // 核心线程数量大小
    private static final int corePoolSize = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    // 线程池最大容纳线程数
    private static final int maxPoolSize = CPU_COUNT * 2 + 1;
    @Bean("asyncTaskExecutor")
    public ThreadPoolTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 线程前缀
        threadPoolTaskExecutor.setThreadNamePrefix(threadNamePrefix);
        // 核心线程数
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        // 最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        // 等待队列
        threadPoolTaskExecutor.setQueueCapacity(workQueue);
        // 线程池维护线程所允许的空闲时间,单位为秒
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveTime);
        // 线程池对拒绝任务(无线程可用)的处理策略
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
