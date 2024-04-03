--
-- Name: member; Type: TABLE; Schema: public
--
CREATE TABLE member (
    id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);


--
-- Name: task; Type: TABLE; Schema: public
--
CREATE TABLE task (
    id BIGINT NOT NULL,
    id_member BIGINT DEFAULT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) DEFAULT NULL,
    status VARCHAR(63) DEFAULT NULL,
    priority VARCHAR(63) DEFAULT NULL,
    date_deadline_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_C817D7699525C141 ON task (id_member);
ALTER TABLE task ADD CONSTRAINT FK_C817D7699525C141 FOREIGN KEY (id_member) REFERENCES member (id) NOT DEFERRABLE INITIALLY IMMEDIATE;