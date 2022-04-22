package com.jh.gxiot.config;

import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @version 1.0
 * @project GxIot
 * @description 自定义线程池
 * @date 2022/3/30 16:13:32
 */
@Configuration
@EnableAsync
@EnableScheduling
public class ExecutorConfig {

    @Bean("iotExecutor")
    public Executor iotExecutor(){
       ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
       // 核心县城数量
       executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
       //最大线程数
       executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() *2);
        //队列大小
       executor.setQueueCapacity(Integer.MAX_VALUE);
       //线程池中的1线程名前缀
        executor.setThreadNamePrefix("IOT-");
        //拒绝策略 直接拒绝
       executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
       //拒绝策略 直接拒绝
       executor.initialize();
       return executor;

    }





}
