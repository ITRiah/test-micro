package com.lgcns.study_micros.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendEmail(EmailRequest request) {
        log.warn("Email service is unavailable. Fallback triggered for email: {}", 
                request != null ? request.getTo() : "unknown");
        
        log.info("Email notification saved to fallback storage for later processing");
        log.info("Email details - To: {}, Subject: {}, Template: {}", 
                request != null ? request.getTo() : "N/A",
                request != null ? request.getSubject() : "N/A", 
                request != null ? request.getTemplate() : "N/A");
        
        // In a real scenario, you might want to:
        // 1. Save to database for retry later
        // 2. Send to a message queue
        // 3. Log to a file for manual processing
        // 4. Send alternative notification (SMS, push notification, etc.)
    }
}
