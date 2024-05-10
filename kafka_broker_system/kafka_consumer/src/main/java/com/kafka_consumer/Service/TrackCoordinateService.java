package com.kafka_consumer.Service;

import com.kafka_consumer.Entity.Track;
import com.kafka_consumer.Entity.TrackCoordinate;
import com.kafka_consumer.Repository.TrackCoordinateRepository;
import org.springframework.stereotype.Service;

@Service
public class TrackCoordinateService {
    private final TrackCoordinateRepository trackCoordinateRepository;

    public TrackCoordinateService(
        TrackCoordinateRepository trackCoordinateRepository
    ) {
        this.trackCoordinateRepository = trackCoordinateRepository;
    }

    public void saveNewCoordinates(Track track, String coordinate) {
        String[] coordinates = coordinate.split(":");

        TrackCoordinate trackCoordinate = TrackCoordinate.builder()
            .track(track)
            .latitude(Float.parseFloat(coordinates[0]))
            .longitude(Float.parseFloat(coordinates[1]))
            .build()
        ;
        trackCoordinateRepository.save(trackCoordinate);
    }
}