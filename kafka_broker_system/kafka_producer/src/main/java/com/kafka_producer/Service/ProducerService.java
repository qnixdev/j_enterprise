package com.kafka_producer.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    @Value("${app.topic}")
    private String topic;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerService(
        KafkaTemplate<String, String> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(String key, String data) {
        try {
            this.kafkaTemplate.send(this.topic, key, data);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}