package com.lgcns.notification.notification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final org.thymeleaf.spring6.SpringTemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String from;

    @Async(value = "emailTaskExecutor")
    public void sendEmail(EmailRequest emailMessage) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, emailMessage.getAttachment() != null,
                    StandardCharsets.UTF_8.name());
            helper.setTo(emailMessage.getTo());
            helper.setSubject(emailMessage.getSubject());
            helper.setFrom(from);

            Context context = new Context();
            emailMessage.getContextVars().forEach(context::setVariable);
            String body = templateEngine.process(emailMessage.getTemplate(), context);
            helper.setText(body, true);

            if (emailMessage.getAttachment() != null && emailMessage.getAttachmentName() != null) {
                helper.addAttachment(emailMessage.getAttachmentName(),
                        new ByteArrayResource(emailMessage.getAttachment()));
            }

            javaMailSender.send(message);
            log.info("Sent email to: {}", emailMessage.getTo());
        } catch (MessagingException ex) {
            log.error("Failed to send email to {}", emailMessage.getTo(), ex);
            throw new RuntimeException("Failed to send email", ex);
        }
    }
}
