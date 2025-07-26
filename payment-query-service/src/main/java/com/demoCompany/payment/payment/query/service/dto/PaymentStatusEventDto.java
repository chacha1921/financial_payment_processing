package com.demoCompany.payment.payment.query.service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatusEventDto {
    private String paymentId;
    private String userId;
    private double amount;
    private String status;
}
