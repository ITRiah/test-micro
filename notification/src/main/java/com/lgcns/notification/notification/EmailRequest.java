package com.lgcns.notification.notification;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String to;
    private String subject;
    private String template;
    private Map<String, Object> contextVars = Map.of("username", "hai");
    private byte[] attachment;
    private String attachmentName;

    public EmailRequest(String to, String subject, String template, Map<String, Object> contextVars) {
        this.to = to;
        this.subject = subject;
        this.template = template;
        this.contextVars = contextVars;
    }
}