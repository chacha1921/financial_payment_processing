package com.demoCompany.payment.payment.command.service.repository;

import com.demoCompany.payment.payment.command.service.repository.model.OutBoxJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutboxRepository extends JpaRepository<OutBoxJpaEntity, Long> {
    List<OutBoxJpaEntity> findByProcessedFalse();
}
