package com.demoCompany.payment.payment.command.service.kafka.producer;

import com.demoCompany.payment.config.data.KafkaConfigData;
import com.demoCompany.payment.kafka.producer.config.service.impl.KafkaProducerImpl;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentCommandKafkaProducer {

    private final KafkaProducerImpl<String> kafkaProducer;

    private final KafkaConfigData kafkaConfigData;


    public PaymentCommandKafkaProducer(KafkaProducerImpl<String> kafkaProducer, KafkaConfigData kafkaConfigData) {
        this.kafkaProducer = kafkaProducer;
        this.kafkaConfigData = kafkaConfigData;
    }

   public SendResult<String, Object> send(String message){
      return kafkaProducer.send(kafkaConfigData.getTopicName(), "", message);

    }
}
