package com.lgcns.study_micros.statistic;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "statistic-service", fallbackFactory  = StatisticServiceImpl.class)
public interface StatisticService {
    @PostMapping("/api/v1/statistics")
    void createStatistic(@RequestBody Statistic account);
}
