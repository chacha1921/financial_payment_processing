package com.demoCompany.payment.payment.query.service.controller;

import com.demoCompany.payment.payment.query.service.services.PaymentStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentQueryController {

    private final PaymentStatusService service;

    public PaymentQueryController(PaymentStatusService service) {
        this.service = service;
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getStatus(@PathVariable String paymentId) {
        return service.getStatus(paymentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

