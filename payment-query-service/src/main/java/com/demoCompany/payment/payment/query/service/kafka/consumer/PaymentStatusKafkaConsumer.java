package com.demoCompany.payment.payment.query.service.kafka.consumer;

import com.demoCompany.payment.payment.query.service.dto.PaymentStatusEventDto;
import com.demoCompany.payment.payment.query.service.services.PaymentStatusService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentStatusKafkaConsumer {

    private final PaymentStatusService statusService;
    private final ObjectMapper objectMapper;

    public PaymentStatusKafkaConsumer(PaymentStatusService statusService) {
        this.statusService = statusService;
        this.objectMapper = new ObjectMapper();
    }

    @KafkaListener(topics = "${kafka.topic.payment-status}", groupId = "${spring.application.name}")
    public void consume(String message) {
        try {
            PaymentStatusEventDto event = objectMapper.readValue(message, PaymentStatusEventDto.class);
            statusService.upsertStatus(event);
            log.info("Updated status for paymentId = " + event.getPaymentId() + ", status = " + event.getStatus());
        } catch (Exception e) {
            log.info("Error processing Kafka message: " + e.getMessage());
        }
    }
}
