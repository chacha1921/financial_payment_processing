package com.demoCompany.payment.payment.command.service.schedule;

import com.demoCompany.payment.payment.command.service.kafka.producer.PaymentCommandKafkaProducer;
import com.demoCompany.payment.payment.command.service.repository.OutboxRepository;
import com.demoCompany.payment.payment.command.service.repository.model.OutBoxJpaEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@EnableScheduling
@Component
public class OutboxScheduler {

    private final PaymentCommandKafkaProducer kafkaProducer;
    private final OutboxRepository outboxRepository;

    public OutboxScheduler(PaymentCommandKafkaProducer kafkaProducer,
                           OutboxRepository outboxRepository) {
        this.kafkaProducer = kafkaProducer;
        this.outboxRepository = outboxRepository;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendAndUpdate() {

        List<OutBoxJpaEntity> pendingEvents = outboxRepository.findByProcessedFalse();

        for (OutBoxJpaEntity event : pendingEvents) {
            try {
                SendResult<String, Object> result = kafkaProducer.send(event.getPayload());

                if (result != null && result.getRecordMetadata() != null) {
                    event.setProcessed(true);
                    outboxRepository.save(event);
                }
            } catch (Exception e) {
                log.info("Failed to publish event with ID " + event.getId() + ": " + e.getMessage());
            }
        }
    }
}
