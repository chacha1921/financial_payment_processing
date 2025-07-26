package com.demoCompany.payment.payment.dispatcher.service.repository.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentJpaEntity, String> {
}
