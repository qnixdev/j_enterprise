package com.kafka_api.Repository;

import com.kafka_api.Model.Track;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
public class TrackDAO {
    private final DataSource dataSource;
    private final TrackCoordinateDAO trackCoordinateDAO;

    public TrackDAO(
        DataSource dataSource,
        TrackCoordinateDAO trackCoordinateDAO
    ) {
        this.dataSource = dataSource;
        this.trackCoordinateDAO = trackCoordinateDAO;
    }

    public Optional<Track> findByName(String name) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT * FROM track WHERE LOWER(name) = ?")) {
            statement.setObject(1, name.toLowerCase());
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                var track = this.build(result);
                this.trackCoordinateDAO.addCoordinatesByTrack(track);

                return Optional.of(track);
            }

            return Optional.empty();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Iterable<Track> findAll() {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT * FROM track")) {
            ResultSet result = statement.executeQuery();
            List<Track> tracks = new ArrayList<>();

            while (result.next()) {
                var track = this.build(result);
                this.trackCoordinateDAO.addCoordinatesByTrack(track);
                tracks.add(track);
            }

            return tracks;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Track build(ResultSet result) throws SQLException {
        return Track.builder()
            .id(result.getObject("id", UUID.class))
            .name(result.getString("name"))
            .coordinates(new ArrayList<>())
            .build()
        ;
    }
}