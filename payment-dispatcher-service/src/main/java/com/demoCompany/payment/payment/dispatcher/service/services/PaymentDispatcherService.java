package com.demoCompany.payment.payment.dispatcher.service.services;

import com.demoCompany.payment.payment.dispatcher.service.dto.PaymentCreatedEvent;
import com.demoCompany.payment.payment.dispatcher.service.kafka.producer.PaymentResultKafkaProducer;
import com.demoCompany.payment.payment.dispatcher.service.repository.models.PaymentJpaEntity;
import com.demoCompany.payment.payment.dispatcher.service.repository.models.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PaymentDispatcherService {

    private final PaymentProvider paymentProvider;
    private final PaymentRepository paymentRepository;
    private final PaymentResultKafkaProducer paymentResultKafkaProducer;

    private final String paymentResultTopic = "payment-result-topic";

    public PaymentDispatcherService(PaymentProvider paymentProvider,
                                    PaymentRepository paymentRepository,
                                    PaymentResultKafkaProducer paymentResultKafkaProducer) {
        this.paymentProvider = paymentProvider;
        this.paymentRepository = paymentRepository;
        this.paymentResultKafkaProducer = paymentResultKafkaProducer;
    }

    @Transactional
    public void processPayment(PaymentCreatedEvent event) {
        PaymentJpaEntity payment = paymentRepository.findById(event.getPaymentId())
                .orElseGet(() -> {
                    PaymentJpaEntity newPayment = new PaymentJpaEntity();
                    newPayment.setPaymentId(event.getPaymentId());
                    newPayment.setUserId(event.getUserId());
                    newPayment.setAmount(event.getAmount());
                    newPayment.setStatus("CREATED");
                    return newPayment;
                });

        boolean success = paymentProvider.pay(payment.getPaymentId(), payment.getAmount());

        if (success) {
            payment.setStatus("PAID");
        } else {
            payment.setStatus("FAILED");
        }

        paymentRepository.save(payment);

        PaymentResultEvent resultEvent = new PaymentResultEvent(payment.getPaymentId(), payment.getStatus());
        paymentResultKafkaProducer.send(paymentResultTopic, "Success");

        log.info("Payment " + payment.getPaymentId() + " processed with status: " + payment.getStatus());
    }

    public static class PaymentResultEvent {
        private String paymentId;
        private String status;

        public PaymentResultEvent(String paymentId, String status) {
            this.paymentId = paymentId;
            this.status = status;
        }

        public String getPaymentId() { return paymentId; }
        public String getStatus() { return status; }
        public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
        public void setStatus(String status) { this.status = status; }
    }
}

