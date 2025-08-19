package com.lgcns.statistics.statistic;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class StatisticController {
    StatisticRepository accountRepository;

    @PostMapping
    public Statistic createStatistic(@RequestBody Statistic account) {
        log.info("create statistic done !");
        return accountRepository.save(account);
    }

    @GetMapping
    public List<Statistic> getAll() {
        return accountRepository.findAll();
    }
}
