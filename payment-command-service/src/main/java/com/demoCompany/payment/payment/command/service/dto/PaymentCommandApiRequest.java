package com.demoCompany.payment.payment.command.service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentCommandApiRequest {

    @NotBlank(message = "providerId should not be null")
    private String providerId;
    @NotBlank(message = "paymentId should not be null")
    private String paymentId;
    @Valid
    private PaymentData paymentData;
}
