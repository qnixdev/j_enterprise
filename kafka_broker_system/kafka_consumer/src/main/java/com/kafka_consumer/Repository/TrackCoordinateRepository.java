package com.kafka_consumer.Repository;

import com.kafka_consumer.Entity.TrackCoordinate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TrackCoordinateRepository extends CrudRepository<TrackCoordinate, UUID> {
}