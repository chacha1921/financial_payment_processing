package com.demoCompany.payment.payment.dispatcher.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PaymentCreatedEvent {
    private String paymentId;
    private String userId;
    private double amount;
    private String status;


}
