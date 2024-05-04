--
-- Name: notification; Type: TABLE; Schema: public
--
CREATE TABLE notification (
    id UUID DEFAULT gen_random_uuid() NOT NULL,
    id_member UUID DEFAULT NULL,
    message VARCHAR(255) NOT NULL,
    type VARCHAR(63) NOT NULL,
    date_created_at TIMESTAMP(0) WITHOUT TIME ZONE NOT NULL,
    is_read BOOLEAN DEFAULT 'false' NOT NULL,
    PRIMARY KEY(id)
);
CREATE INDEX IDX_3F980AC9525C141 ON notification (id_member);
COMMENT ON COLUMN notification.id IS '(DC2Type:uuid)';
COMMENT ON COLUMN notification.id_member IS '(DC2Type:uuid)';
ALTER TABLE notification ADD CONSTRAINT FK_3F980AC9525C141 FOREIGN KEY (id_member) REFERENCES member (id) NOT DEFERRABLE INITIALLY IMMEDIATE;