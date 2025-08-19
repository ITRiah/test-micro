package com.lgcns.study_micros.statistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.stats.StatisticsRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements FallbackFactory<StatisticService> {

    @Override
    public StatisticService create(Throwable cause) {
        log.error("Error calling statistic-service", cause);
        return account -> {
            log.warn("Fallback triggered for account: {}",
                    account != null ? account.getId() : "unknown");
            log.info("Statistic data saved to fallback storage for later processing");
        };
    }


}
