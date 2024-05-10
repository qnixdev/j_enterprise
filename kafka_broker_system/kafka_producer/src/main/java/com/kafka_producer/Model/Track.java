package com.kafka_producer.Model;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Track {
    private String name;
    private float latitude;
    private float longitude;
}