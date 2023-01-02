package com.app.emailservice.kafka;

import com.app.basedomains.dto.OrderEvent;
import com.app.emailservice.entity.EmailDetails;
import com.app.emailservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailConsumer.class);

     private EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent){
        LOGGER.info(String.format("Order event received in email service -> %s",orderEvent.toString()));

        String message= "Hi Customer, Your Order Placed Successfully! /n Please fine order details: /n"+orderEvent.toString();
        //Send email to the customer
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(orderEvent.getEmail());
        emailDetails.setMsgbody(message);
        emailDetails.setSubject("Your Order Placed Successfully! Order Id: "+orderEvent.getOrder().getOrderId());
        emailService.sendSimpleEmail(emailDetails);
    }

}
