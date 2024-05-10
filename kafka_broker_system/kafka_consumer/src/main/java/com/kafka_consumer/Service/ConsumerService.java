package com.kafka_consumer.Service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private final TrackService trackService;
    private final TrackCoordinateService coordinateService;

    public ConsumerService(
        TrackService trackService,
        TrackCoordinateService coordinateService
    ) {
        this.trackService = trackService;
        this.coordinateService = coordinateService;
    }

    @KafkaListener(topics = "track_topic")
    public void consume(ConsumerRecord<String, String> record) {
        var trackName = record.key();
        var track = this.trackService.read(trackName);

        if (null == track) {
            track = this.trackService.create(trackName);
        }

        this.coordinateService.saveNewCoordinates(track, record.value());
    }
}