package com.demoCompany.payment.payment.dispatcher.service.repository.models;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Table(name = "payments")
@Data
public class PaymentJpaEntity {
    @Id
    private String paymentId;
    private String userId;
    private double amount;
    private String status;
}
}
