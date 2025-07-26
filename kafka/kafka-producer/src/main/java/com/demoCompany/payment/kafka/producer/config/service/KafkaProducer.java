package com.demoCompany.payment.kafka.producer.config.service;


import org.springframework.kafka.support.SendResult;

import java.io.Serializable;

public interface KafkaProducer<K extends Serializable, V extends Serializable> {

    SendResult<String, Object> send(String topicName, K key, V message);
}
