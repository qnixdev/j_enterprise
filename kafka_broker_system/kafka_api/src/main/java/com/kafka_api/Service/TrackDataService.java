package com.kafka_api.Service;

import com.kafka_api.Model.Track;
import com.kafka_api.Repository.TrackDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TrackDataService {
    private final TrackDAO trackDAO;

    public TrackDataService(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public ResponseEntity<Iterable<Track>> getDataOfAllTracks() {
        return ResponseEntity.of(Optional.ofNullable(this.trackDAO.findAll()));
    }

    public ResponseEntity<Track> getDataOfTrackByName(String trackName) {
        return ResponseEntity.of(this.trackDAO.findByName(trackName));
    }
}