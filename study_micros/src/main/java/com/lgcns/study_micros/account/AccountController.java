package com.lgcns.study_micros.account;

import com.lgcns.study_micros.notification.EmailRequest;
import com.lgcns.study_micros.notification.NotificationService;
import com.lgcns.study_micros.statistic.Statistic;
import com.lgcns.study_micros.statistic.StatisticService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class AccountController {
    AccountRepository accountRepository;
    StatisticService statisticService;
    NotificationService notificationService;
    AccountService accountService;
    KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        Account accountSave = accountRepository.save(account);
        kafkaTemplate.send("statistic", new Statistic("create account" + account.getUsername()));
//        kafkaTemplate.send("notification", new EmailRequest(Map.of("username", account.getUsername()), account.getEmail()));
//        statisticService.createStatistic(new Statistic("create account" + account.getUsername()));
//        notificationService.sendEmail(new EmailRequest(Map.of("username", account.getUsername()), account.getEmail()));
        return accountSave;
    }

    @GetMapping
    public List<Account> getAll() {
        log.info("Account controller");
        return accountService.getAll();
    }
}
