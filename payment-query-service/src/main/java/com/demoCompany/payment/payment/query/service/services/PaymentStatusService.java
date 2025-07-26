package com.demoCompany.payment.payment.query.service.services;

import com.demoCompany.payment.payment.query.service.dto.PaymentStatusEventDto;
import com.demoCompany.payment.payment.query.service.repository.PaymentStatusRepository;
import com.demoCompany.payment.payment.query.service.repository.models.PaymentStatusEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentStatusService {

    private final PaymentStatusRepository repository;

    public PaymentStatusService(PaymentStatusRepository repository) {
        this.repository = repository;
    }

    public void upsertStatus(PaymentStatusEventDto event) {
        PaymentStatusEntity entity = PaymentStatusEntity.builder()
                .paymentId(event.getPaymentId())
                .userId(event.getUserId())
                .amount(event.getAmount())
                .status(event.getStatus())
                .build();

        repository.save(entity);
    }

    public Optional<PaymentStatusEntity> getStatus(String paymentId) {
        return repository.findById(paymentId);
    }
}
