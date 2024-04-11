--
-- Name: member; Type: TABLE; Schema: public
--
CREATE TABLE member (
    id UUID DEFAULT gen_random_uuid() NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN member.id IS '(DC2Type:uuid)';


--
-- Name: task; Type: TABLE; Schema: public
--
CREATE TABLE task (
    id UUID DEFAULT gen_random_uuid() NOT NULL,
    id_member UUID DEFAULT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) DEFAULT NULL,
    status VARCHAR(63) DEFAULT NULL,
    priority VARCHAR(63) DEFAULT NULL,
    date_deadline_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_C817D7699525C141 ON task (id_member);
COMMENT ON COLUMN task.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN task.id_member IS '(DC2Type:uuid)';
ALTER TABLE task ADD CONSTRAINT FK_C817D7699525C141 FOREIGN KEY (id_member) REFERENCES member (id) NOT DEFERRABLE INITIALLY IMMEDIATE;