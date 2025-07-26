package com.demoCompany.payment.payment.query.service.repository.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentStatusEntity {

    @Id
    private String paymentId;

    private String userId;

    private double amount;

    private String status;
}
