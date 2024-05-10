package com.kafka_producer.Service;

import com.kafka_producer.Model.Track;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.*;

@Service
public class LogisticService {
    private final TrackService trackService;
    private final ScheduledExecutorService executorService;

    public LogisticService(
        TrackService trackService
    ) {
        this.trackService = trackService;
        this.executorService = Executors.newScheduledThreadPool(6);
    }

    public void runTracks(int count) {
        List<Track> tracks = this.trackService.getTracks(count);

        for (Track track : tracks) {
            this.executorService.scheduleAtFixedRate(
                () -> {
                    this.trackService.sendCoordinates(track);
                    this.trackService.updateCoordinates(track);
                }, 0, 10, TimeUnit.SECONDS
            );
        }
    }

    public void stopTracks() {
        executorService.shutdown();
    }
}