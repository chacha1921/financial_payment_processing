package com.demoCompany.payment.payment.query.service.repository;

import com.demoCompany.payment.payment.query.service.repository.models.PaymentStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatusEntity, String> {
}