package com.kafka_consumer.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "track")
public class Track {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", length = 63, nullable = false)
    private String name;

    @JsonManagedReference
    @OneToMany(targetEntity = TrackCoordinate.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrackCoordinate> coordinates = new ArrayList<>();
}