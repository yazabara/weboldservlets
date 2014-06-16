DROP TABLE IF EXISTS BOOK_GROUP;
DROP TABLE IF EXISTS GROUP_BOOK_CONNECTION;

CREATE TABLE BOOK_GROUP (
    ID          INT          NOT NULL AUTO_INCREMENT
  , NAME        VARCHAR(200) NOT NULL
  , DESCRIPTION VARCHAR(200)
  , PRIMARY KEY (ID)
);

CREATE TABLE GROUP_BOOK_CONNECTION (
    ID       INT NOT NULL AUTO_INCREMENT
  , GROUP_ID INT NOT NULL
  , BOOK_ID  INT NOT NULL
  , PRIMARY KEY (ID)
);

INSERT INTO BOOK_GROUP (NAME, DESCRIPTION) VALUES ('Классика', 'Классические произведения');
INSERT INTO BOOK_GROUP (NAME, DESCRIPTION) VALUES ('Наука', 'Научные произведения');
INSERT INTO BOOK_GROUP (NAME, DESCRIPTION) VALUES ('Развлечение', 'Различные веселые произведения');

INSERT INTO GROUP_BOOK_CONNECTION (GROUP_ID, BOOK_ID) VALUES (1, 1);
INSERT INTO GROUP_BOOK_CONNECTION (GROUP_ID, BOOK_ID) VALUES (1, 2);
INSERT INTO GROUP_BOOK_CONNECTION (GROUP_ID, BOOK_ID) VALUES (1, 3);
INSERT INTO GROUP_BOOK_CONNECTION (GROUP_ID, BOOK_ID) VALUES (1, 4);
INSERT INTO GROUP_BOOK_CONNECTION (GROUP_ID, BOOK_ID) VALUES (1, 5);
INSERT INTO GROUP_BOOK_CONNECTION (GROUP_ID, BOOK_ID) VALUES (1, 6);
INSERT INTO GROUP_BOOK_CONNECTION (GROUP_ID, BOOK_ID) VALUES (1, 7);
INSERT INTO GROUP_BOOK_CONNECTION (GROUP_ID, BOOK_ID) VALUES (2, 1);

INSERT INTO GROUP_BOOK_CONNECTION (GROUP_ID, BOOK_ID) VALUES (2, 33);
INSERT INTO GROUP_BOOK_CONNECTION (GROUP_ID, BOOK_ID) VALUES (2, 34);
INSERT INTO GROUP_BOOK_CONNECTION (GROUP_ID, BOOK_ID) VALUES (2, 35);

