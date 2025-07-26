package com.demoCompany.payment.payment.command.service.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class PaymentsJpaEntity {

    @Id
    private Long id;
    private String paymentId;
    private String paymentStatus;
    private String message;

}
