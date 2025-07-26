package com.demoCompany.payment.payment.command.service.repository;

import com.demoCompany.payment.payment.command.service.model.PaymentContext;
import com.demoCompany.payment.payment.command.service.model.Payments;
import com.demoCompany.payment.payment.command.service.repository.model.OutBoxJpaEntity;
import com.demoCompany.payment.payment.command.service.repository.model.PaymentsJpaEntity;
import com.demoCompany.payment.payment.command.service.transformer.PaymentCommandTransformer;
import com.demoCompany.payment.payment.command.service.utils.PaymentSerializationUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@Slf4j
@Component
public class PaymentCommandServiceRepository implements PaymentContext {

    private final PaymentComandJpaRepository paymentComandJpaRepository;
    private final PaymentCommandTransformer paymentCommandTransformer;
    private final OutboxRepository outboxRepository;
    private final PaymentSerializationUtil paymentSerializationUtil;

    public PaymentCommandServiceRepository(
            PaymentComandJpaRepository paymentComandJpaRepository,
            PaymentCommandTransformer paymentCommandTransformer,
            OutboxRepository outboxRepository,
            PaymentSerializationUtil paymentSerializationUtil
    ) {
        this.paymentComandJpaRepository = paymentComandJpaRepository;
        this.paymentCommandTransformer = paymentCommandTransformer;
        this.outboxRepository = outboxRepository;
        this.paymentSerializationUtil = paymentSerializationUtil;
    }

    @Transactional
    @Override
    public boolean save(Payments payment) {
        try {
            PaymentsJpaEntity paymentsJpaEntity = paymentComandJpaRepository.save(
                    paymentCommandTransformer.paymentToPaymentEntity(payment)
            );
            log.info("Payment with id {} is saved successfully.", paymentsJpaEntity.getPaymentId());

            OutBoxJpaEntity event = new OutBoxJpaEntity();
            event.setAggregateType("PAYMENT");
            event.setAggregateId(payment.getPaymentId());
            event.setEventType("PAYMENT_CREATED");
            event.setPayload(paymentSerializationUtil.serializeEvent(payment));
            event.setCreatedAt(LocalDate.now());
            outboxRepository.save(event);

            return true;
        } catch (DataIntegrityViolationException e) {
            log.warn("Duplicate or constraint violation for payment id {}", payment.getPaymentId());
            return false;
        } catch (Exception e) {
            log.error("Unexpected error saving payment with id {}", payment.getPaymentId(), e);
            return false;
        }
    }
}
