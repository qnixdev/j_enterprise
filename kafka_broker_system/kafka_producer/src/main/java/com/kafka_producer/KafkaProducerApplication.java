package com.kafka_producer;

import com.kafka_producer.Service.LogisticService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaProducerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaProducerApplication.class, args);

        LogisticService logisticService = context.getBean(LogisticService.class);
        logisticService.runTracks(50);
    }
}