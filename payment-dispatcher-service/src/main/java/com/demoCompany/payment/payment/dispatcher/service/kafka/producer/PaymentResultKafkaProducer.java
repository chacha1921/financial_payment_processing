package com.demoCompany.payment.payment.dispatcher.service.kafka.producer;

import com.demoCompany.payment.config.data.KafkaConfigData;
import com.demoCompany.payment.kafka.producer.config.service.impl.KafkaProducerImpl;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentResultKafkaProducer {

    private final KafkaProducerImpl<String> kafkaProducer;
    private final KafkaConfigData kafkaConfigData;

    public PaymentResultKafkaProducer(KafkaProducerImpl<String> kafkaProducer, KafkaConfigData kafkaConfigData) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaConfigData = kafkaConfigData;
    }

    public SendResult<String, Object> send(String topic, String message) {
        return kafkaProducer.send(topic, "", message);
    }
}