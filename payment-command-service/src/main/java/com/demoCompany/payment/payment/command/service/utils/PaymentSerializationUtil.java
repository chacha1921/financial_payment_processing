package com.demoCompany.payment.payment.command.service.utils;

import com.demoCompany.payment.payment.command.service.model.Payments;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentSerializationUtil {

    private final ObjectMapper objectMapper;

    public PaymentSerializationUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String serializeEvent(Payments payment) {
        try {
            return objectMapper.writeValueAsString(payment);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize payment for outbox", e);
            return "{}";
        }
    }
}
