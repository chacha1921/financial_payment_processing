package com.demoCompany.payment.payment.dispatcher.service.services;


import org.springframework.stereotype.Component;

@Component
public interface PaymentProvider {
    boolean pay(String paymentId, double amount);
}


