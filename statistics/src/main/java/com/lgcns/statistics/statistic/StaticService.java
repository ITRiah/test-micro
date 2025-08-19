package com.lgcns.statistics.statistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StaticService {
    private final StatisticRepository repository;

    @KafkaListener(topics = "statistic", id = "statistic-consumer-group")
    public void listen(Statistic statistic) {
        log.info("Save success!");
        repository.save(statistic);
    }
}
