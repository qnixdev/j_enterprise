--
-- Name: track; Type: TABLE; Schema: public
--
CREATE TABLE track (
    id UUID DEFAULT gen_random_uuid() NOT NULL,
    name VARCHAR(63) NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN track.id IS '(DC2Type:uuid)';


--
-- Name: track_coordinate; Type: TABLE; Schema: public
--
CREATE TABLE track_coordinate (
    id UUID DEFAULT gen_random_uuid() NOT NULL,
    id_track UUID DEFAULT NULL,
    latitude DOUBLE PRECISION NULL NULL,
    longitude DOUBLE PRECISION NULL NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_2ABB023E22FFD58C ON track_coordinate (id_track);
COMMENT ON COLUMN track_coordinate.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN track_coordinate.id_track IS '(DC2Type:uuid)';
ALTER TABLE track_coordinate ADD CONSTRAINT FK_2ABB023E22FFD58C FOREIGN KEY (id_track) REFERENCES track (id) NOT DEFERRABLE INITIALLY IMMEDIATE;