--
-- Name: user; Type: TABLE; Schema: public
--
CREATE TABLE "user" (
    id UUID DEFAULT gen_random_uuid() NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);
CREATE UNIQUE INDEX UNIQ_8D93D649E7927C74 ON "user" (email);
COMMENT ON COLUMN "user".id IS '(DC2Type:uuid)';


--
-- Name: security_group; Type: TABLE; Schema: public
--
CREATE TABLE security_group (
    id UUID DEFAULT gen_random_uuid() NOT NULL,
    type VARCHAR(63) NOT NULL,
    PRIMARY KEY(id)
);
COMMENT ON COLUMN security_group.id IS '(DC2Type:uuid)';


--
-- Name: users_security_groups; Type: TABLE; Schema: public
--
CREATE TABLE users_security_groups (
    id_user UUID NOT NULL,
    id_security_group UUID NOT NULL,
    PRIMARY KEY(id_user, id_security_group)
);
CREATE INDEX IDX_7DF730FF6B3CA4B ON users_security_groups (id_user);
CREATE INDEX IDX_7DF730FFBC1A1A69 ON users_security_groups (id_security_group);
COMMENT ON COLUMN users_security_groups.id_user IS '(DC2Type:uuid)';
COMMENT ON COLUMN users_security_groups.id_security_group IS '(DC2Type:uuid)';



ALTER TABLE users_security_groups ADD CONSTRAINT FK_7DF730FF6B3CA4B FOREIGN KEY (id_user) REFERENCES "user" (id) NOT DEFERRABLE INITIALLY IMMEDIATE;
ALTER TABLE users_security_groups ADD CONSTRAINT FK_7DF730FFBC1A1A69 FOREIGN KEY (id_security_group) REFERENCES security_group (id) NOT DEFERRABLE INITIALLY IMMEDIATE;



INSERT INTO security_group (type) VALUES ('ADMIN');
INSERT INTO security_group (type) VALUES ('USER');
INSERT INTO "user" (email, password) VALUES ('ko@test.com', '$2a$10$mSW5ojoJOAfnhAPlK1ols.9w/nsve0cyWS089aL7Z7.VBIrJFXWjm');
INSERT INTO users_security_groups (id_user, id_security_group) VALUES (
    (SELECT u.id FROM "user" u WHERE u.email = 'ko@test.com'),
    (SELECT sg.id FROM security_group sg WHERE sg.type = 'ADMIN')
)