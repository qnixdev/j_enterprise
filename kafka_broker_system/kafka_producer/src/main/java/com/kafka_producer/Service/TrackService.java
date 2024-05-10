package com.kafka_producer.Service;

import com.kafka_producer.Model.Track;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.random.RandomGenerator;

@Service
public class TrackService {
    private final ProducerService producerService;

    public TrackService(
        ProducerService producerService
    ) {
        this.producerService = producerService;
    }

    public List<Track> getTracks(int count) {
        List<Track> tracks = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            tracks.add(this.createTrack(i + 1));
        }

        return tracks;
    }

    public void sendCoordinates(Track track) {
        this.producerService.produce(
            track.getName(),
            this.groupCoordinates(track)
        );
    }

    public void updateCoordinates(Track track) {
        var existLatitude = track.getLatitude();
        var existLongitude = track.getLongitude();

        track.setLatitude(RandomGenerator.getDefault().nextFloat(existLatitude, 33.37f));
        track.setLongitude(RandomGenerator.getDefault().nextFloat(existLongitude, 44.47f));
    }

    private Track createTrack(int suffix) {
        return Track.builder()
            .name(String.format("Track_%d", suffix))
            .latitude(.0f)
            .longitude(.0f)
            .build()
        ;
    }

    private String groupCoordinates(Track track) {
        return String.format("%1$s:%2$s", track.getLatitude(), track.getLongitude());
    }
}