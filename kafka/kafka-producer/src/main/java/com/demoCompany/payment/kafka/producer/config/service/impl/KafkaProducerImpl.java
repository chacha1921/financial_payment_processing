package com.demoCompany.payment.kafka.producer.config.service.impl;

import com.demoCompany.payment.kafka.producer.config.service.KafkaProducer;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Slf4j
@Service
public class KafkaProducerImpl<T extends Serializable> implements KafkaProducer<String, T> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public SendResult<String, Object> send(String topicName, String key, T message) {
        log.info("Sending payload ={} to topic={}", message, topicName);


        try{

            return kafkaTemplate.send(topicName, key, message).get();
        }catch (Exception e){
            log.error("Error while sending message {} to topic {} ", message, topicName, e);
            return null;
        }
//                .whenComplete((result, throwable) -> {
//                    if (throwable != null) {
//                        log.error("Error while sending message {} to topic {} ", message, topicName, throwable);
//
//                    } else {
//                        RecordMetadata metadata = result.getRecordMetadata();
//                        log.info("Received new metadata, Topic: {}; Partitions {}; Offset: {}; Timestamp: {}, at time {}",
//                                metadata.topic(),
//                                metadata.partition(),
//                                metadata.offset(),
//                                metadata.timestamp(),
//                                System.nanoTime());
//                    }
//                });


    }


}
