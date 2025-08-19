package com.lgcns.notification.notification;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emails")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmailController {
    EmailService emailService;

    @PostMapping
    public void createAccount(@RequestBody EmailRequest request) {
        request.setTemplate("welcome");
        request.setSubject("Welcome to our service");
        emailService.sendEmail(request);
    }

}
