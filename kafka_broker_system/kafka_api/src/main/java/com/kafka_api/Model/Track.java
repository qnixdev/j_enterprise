package com.kafka_api.Model;

import lombok.*;
import java.util.*;

@AllArgsConstructor
@Builder
@Data
public class Track {
    private UUID id;
    private String name;
    private List<TrackCoordinate> coordinates;
}