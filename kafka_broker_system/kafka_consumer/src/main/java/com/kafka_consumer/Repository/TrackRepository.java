package com.kafka_consumer.Repository;

import com.kafka_consumer.Entity.Track;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TrackRepository extends CrudRepository<Track, UUID> {
    Track getTrackByName(String trackName);
}