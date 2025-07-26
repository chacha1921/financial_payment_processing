package com.demoCompany.payment.payment.command.service.controller;

import com.demoCompany.payment.payment.command.service.dto.PaymentCommandApiRequest;
import com.demoCompany.payment.payment.command.service.dto.PaymentCommandApiResponse;
import com.demoCompany.payment.payment.command.service.service.impl.PaymentCommandServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentCommandController {

    private final PaymentCommandServiceImpl paymentCommandService;

    public PaymentCommandController(PaymentCommandServiceImpl paymentCommandService) {
        this.paymentCommandService = paymentCommandService;
    }

    @PostMapping
    public ResponseEntity<PaymentCommandApiResponse> pay(@RequestBody @Valid PaymentCommandApiRequest request) {

        PaymentCommandApiResponse response = paymentCommandService.process(request);
        return ResponseEntity.ok(response);
    }


}
