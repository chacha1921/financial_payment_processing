package com.demoCompany.payment.payment.command.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payments {


    private Long id;
    private String paymentProviderId;
    private String paymentId;
    private PaymentStatus paymentStatus;
}
