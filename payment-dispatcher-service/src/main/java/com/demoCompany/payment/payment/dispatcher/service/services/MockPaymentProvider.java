package com.demoCompany.payment.payment.dispatcher.service.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MockPaymentProvider implements PaymentProvider {

    @Override
    public boolean pay(String paymentId, double amount) {
        log.info("Processing payment with MockPaymentProvider for paymentId: " + paymentId);
        return Math.random() > 0.2;
    }
}