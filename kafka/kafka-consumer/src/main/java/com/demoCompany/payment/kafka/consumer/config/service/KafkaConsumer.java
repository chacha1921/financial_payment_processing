package com.demoCompany.payment.kafka.consumer.config.service;


import java.io.Serializable;
import java.util.List;

public interface KafkaConsumer<K extends Serializable, V > {
    void receive(List<V> messages);
}
