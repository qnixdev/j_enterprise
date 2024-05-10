package com.kafka_consumer.Service;

import com.kafka_consumer.Entity.Track;
import com.kafka_consumer.Repository.TrackRepository;
import org.springframework.stereotype.Service;

@Service
public class TrackService {
    private final TrackRepository trackRepository;

    public TrackService(
        TrackRepository trackRepository
    ) {
        this.trackRepository = trackRepository;
    }

    public Track create(String name) {
        Track track = new Track();
        track.setName(name);
        this.trackRepository.save(track);

        return track;
    }

    public Track read(String name) {
        return this.trackRepository.getTrackByName(name);
    }
}