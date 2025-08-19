package com.lgcns.study_micros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor emailTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        
        // Số thread core - luôn sẵn sàng
        executor.setCorePoolSize(5);
        
        // Số thread tối đa
        executor.setMaxPoolSize(20);
        
        // Kích thước queue chờ
        executor.setQueueCapacity(100);
        
        // Thời gian keep-alive cho thread không core (giây)
        executor.setKeepAliveSeconds(60);
        
        // Prefix cho tên thread
        executor.setThreadNamePrefix("EmailAsync-");
        
        // Chờ các task hoàn thành khi shutdown
        executor.setWaitForTasksToCompleteOnShutdown(true);
        
        // Thời gian chờ tối đa khi shutdown (giây)
        executor.setAwaitTerminationSeconds(30);
        
        // Khởi tạo executor
        executor.initialize();
        
        return executor;
    }
}