package com.demoCompany.payment.payment.command.service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentCommandApiResponse {


    private String paymentId;
    private String responseCode;
    private String responseMessage;


    public PaymentCommandApiResponse buildSuccess(String paymentId){
        return PaymentCommandApiResponse.builder()
                .paymentId(paymentId)
                .responseCode("0")
                .responseMessage("payment submitted successfully")
                .build();
    }
    public PaymentCommandApiResponse buildFailure(String paymentId){
        return PaymentCommandApiResponse.builder()
                .paymentId(paymentId)
                .responseCode("1")
                .responseMessage("payment failed try again latter.")
                .build();
    }
}
