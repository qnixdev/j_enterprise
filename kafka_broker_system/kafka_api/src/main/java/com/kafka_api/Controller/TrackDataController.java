package com.kafka_api.Controller;

import com.kafka_api.Model.Track;
import com.kafka_api.Service.TrackDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/track")
public class TrackDataController {
    private final TrackDataService trackDataService;

    public TrackDataController(
        TrackDataService trackDataService
    ) {
        this.trackDataService = trackDataService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Track>> getDataAllTracks() {
        return this.trackDataService.getDataOfAllTracks();
    }

    @GetMapping("/{trackName}")
    public ResponseEntity<Track> one(@PathVariable String trackName) {
        return this.trackDataService.getDataOfTrackByName(trackName);
    }
}