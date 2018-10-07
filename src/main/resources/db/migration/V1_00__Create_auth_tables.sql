CREATE TABLE ORGANISATION(
    ID				        VARCHAR(250) NOT NULL,
    ORGANISATION_NAME       VARCHAR(50) NOT NULL,
    STREET                  VARCHAR(100),
    TOWN                    VARCHAR(50),
    COUNTY                  VARCHAR(30),
    POSTCODE                VARCHAR(15),
    COUNTRY                 VARCHAR(100),
    PRIMARY KEY(ID)
);

CREATE TABLE APP_USER(
    ID                  	VARCHAR(250) NOT NULL,
    USERNAME                VARCHAR(100) NOT NULL UNIQUE,
    PASSWORD                VARCHAR(250),
    ORGANISATION_ID         VARCHAR(250) NOT NULL,
    ACCOUNTNONEXPIRED       BOOLEAN DEFAULT TRUE,
    ACCOUNTNONLOCKED        BOOLEAN DEFAULT TRUE,
    CREDENTIALSNONEXPIRED   BOOLEAN DEFAULT TRUE,
    ENABLED                 BOOLEAN DEFAULT TRUE,
    FIRSTNAME               VARCHAR(250),
    SURNAME                 VARCHAR(250),
    USERTYPE                VARCHAR(250),
    EMAIL                   VARCHAR(250),
    PRIMARY KEY(ID),
    FOREIGN KEY(ORGANISATION_ID) REFERENCES ORGANISATION(ID)
);

CREATE TABLE ROLE_ACCESS(
    ID                  	VARCHAR(250) NOT NULL,
    USER_ID                 VARCHAR(250) NOT NULL,
    ROLE                    VARCHAR(250) NOT NULL,
    ACCESS                  CHAR(1) NOT NULL,
    PRIMARY KEY(ID),
    FOREIGN KEY(USER_ID) REFERENCES APP_USER(ID)
);

INSERT INTO ORGANISATION(ID, ORGANISATION_NAME, COUNTRY) VALUES
('f29a849a-b3a6-11e8-9d52-8a3878b21553', 'Black Crow Systems Limited', 'United Kingdom');

INSERT INTO APP_USER(ID, USERNAME, PASSWORD, ORGANISATION_ID, USERTYPE) VALUES
('a374c104-b3a7-11e8-9d52-8a3878b21553', 'system', '{bcrypt}$2a$10$9fGMLYlzGzPI918tMpCKw.EVrSxGSRv0ji2vNCyPuHlAlgpLPKimi', 'f29a849a-b3a6-11e8-9d52-8a3878b21553', 'ADMIN');

INSERT INTO ROLE_ACCESS(ID, USER_ID, ROLE, ACCESS) VALUES
('d040e360-b3a8-11e8-9d52-8a3878b21553', 'a374c104-b3a7-11e8-9d52-8a3878b21553', 'SYSTEM', 'D');