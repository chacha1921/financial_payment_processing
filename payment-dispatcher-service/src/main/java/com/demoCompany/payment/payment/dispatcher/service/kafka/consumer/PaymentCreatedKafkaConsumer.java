package com.demoCompany.payment.payment.dispatcher.service.kafka.consumer;

import com.demoCompany.payment.config.data.KafkaConfigData;
import com.demoCompany.payment.kafka.consumer.config.service.KafkaConsumer;
import com.demoCompany.payment.payment.dispatcher.service.dto.PaymentCreatedEvent;
import com.demoCompany.payment.payment.dispatcher.service.services.PaymentDispatcherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class PaymentCreatedKafkaConsumer implements KafkaConsumer<String, String> {

    private final PaymentDispatcherService paymentDispatcherService;
    private final KafkaConfigData kafkaConfigData;
    private final ObjectMapper objectMapper;

    public PaymentCreatedKafkaConsumer(PaymentDispatcherService paymentDispatcherService, KafkaConfigData kafkaConfigData) {
        this.paymentDispatcherService = paymentDispatcherService;
        this.kafkaConfigData = kafkaConfigData;
        this.objectMapper = new ObjectMapper();
    }


    @Override
    public void receive(List<String> messages) {
        for (String message : messages) {
            try {
                PaymentCreatedEvent event = objectMapper.readValue(message, PaymentCreatedEvent.class);
                paymentDispatcherService.processPayment(event);

                log.info("Consumed PaymentCreatedEvent with ID: " + event.getPaymentId());
            } catch (Exception e) {
                log.info("Failed to deserialize PaymentCreatedEvent: " + e.getMessage());
            }
        }
    }


    @KafkaListener(topics = "KafkaConfigData kafkaConfigData", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(List<String> messages) {
        receive(messages);
    }
}

