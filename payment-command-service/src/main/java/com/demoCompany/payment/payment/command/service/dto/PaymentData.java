package com.demoCompany.payment.payment.command.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentData {

    @NotBlank(message = "initiator should not be empty or blank")
    private String initiator;
    @NotBlank(message = "initiator should not be empty or blank")
    private String amount;

    @NotBlank(message = "currency is required")
    private String currency;

    private String description;

}
