package com.lgcns.study_micros;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableAsync
public class StudyMicrosApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyMicrosApplication.class, args);
    }


    @Bean
    NewTopic notification() {
        return new NewTopic("notification", 1, (short) 1);
    }

    @Bean
    NewTopic statistics() {
        return new NewTopic("statistic", 1, (short) 1);
    }

}
