package com.kafka_api.Model;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
public class TrackCoordinate {
    private float latitude;
    private float longitude;
}