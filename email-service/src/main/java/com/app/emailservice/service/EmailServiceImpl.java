package com.app.emailservice.service;

import com.app.emailservice.entity.EmailDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Async
    @Override
    public String sendSimpleEmail(EmailDetails emailDetails) {
        try {
            LOGGER.info("Email Service start...");
            SimpleMailMessage message= new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(emailDetails.getRecipient());
            message.setText(emailDetails.getMsgbody());
            message.setSubject(emailDetails.getSubject());
            LOGGER.info("Email Message created...");
            javaMailSender.send(message);
            LOGGER.info("Email sent to  %s",emailDetails.getRecipient());
        }catch(Exception e){
            LOGGER.info("Error while Sending Mail..");
            return "Error while Sending Mail";
        }
        return null;
    }

    @Override
    public String sendMailWithAttachment(EmailDetails emailDetails) {
        return null;
    }
}
