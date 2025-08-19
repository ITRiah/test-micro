package com.lgcns.study_micros.account;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AccountService {
    AccountRepository accountRepository;

    public List<Account> getAll() {
        log.info("Account service");
        return accountRepository.findAll();
    }
}
