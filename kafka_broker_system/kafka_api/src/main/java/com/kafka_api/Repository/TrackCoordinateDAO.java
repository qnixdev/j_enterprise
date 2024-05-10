package com.kafka_api.Repository;

import com.kafka_api.Model.Track;
import com.kafka_api.Model.TrackCoordinate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TrackCoordinateDAO {
    private final DataSource dataSource;

    public TrackCoordinateDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addCoordinatesByTrack(Track track) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT * FROM track_coordinate WHERE id_track = ?")) {
            statement.setObject(1, track.getId());
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                track.getCoordinates().add(this.build(result));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private TrackCoordinate build(ResultSet result) throws SQLException {
        return TrackCoordinate.builder()
            .latitude(result.getFloat("latitude"))
            .longitude(result.getFloat("longitude"))
            .build()
        ;
    }
}