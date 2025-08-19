package com.lgcns.study_micros.notification;

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
    private Map<String, Object> contextVars;
    private byte[] attachment;
    private String attachmentName;

    public EmailRequest(Map<String, Object> contextVars, String email) {
        this.contextVars = contextVars;
        to = email;
    }
}