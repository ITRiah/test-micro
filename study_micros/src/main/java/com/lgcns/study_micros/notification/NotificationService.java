package com.lgcns.study_micros.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="notification-service", url="${service.notification-url}", fallback = NotificationServiceImpl.class)
public interface NotificationService {
    @PostMapping("/emails")
    void sendEmail(@RequestBody EmailRequest request);
}
