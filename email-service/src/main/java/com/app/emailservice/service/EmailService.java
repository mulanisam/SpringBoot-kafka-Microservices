package com.app.emailservice.service;

import com.app.emailservice.entity.EmailDetails;
import org.apache.kafka.common.protocol.types.Field;

public interface EmailService {
    String sendSimpleEmail(EmailDetails emailDetails);
    String sendMailWithAttachment(EmailDetails emailDetails);
}
