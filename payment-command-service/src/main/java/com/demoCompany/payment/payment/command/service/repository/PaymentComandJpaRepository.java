package com.demoCompany.payment.payment.command.service.repository;

import com.demoCompany.payment.payment.command.service.repository.model.PaymentsJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentComandJpaRepository extends JpaRepository<PaymentsJpaEntity,Long> {
}
