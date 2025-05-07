CREATE DATABASE IF NOT EXISTS geography;
USE geography;
DROP TABLE IF EXISTS city;
DROP TABLE IF EXISTS country;
DROP TABLE IF EXISTS continent;

CREATE TABLE continent (
   id   INTEGER PRIMARY KEY AUTO_INCREMENT,
   name VARCHAR(50) NOT NULL
);

CREATE TABLE country (
   id        INTEGER PRIMARY KEY AUTO_INCREMENT,
   name      VARCHAR(100) NOT NULL,
   code      VARCHAR(10) NOT NULL,
   
   idContinent INTEGER
      REFERENCES continent ( id )
);

CREATE TABLE city (
   id         INTEGER PRIMARY KEY AUTO_INCREMENT,
   name       VARCHAR(100) NOT NULL,
   capital    BOOLEAN,
   latitude   DOUBLE,
   longitude  DOUBLE,
   
   idCountry INTEGER
      REFERENCES country ( id )
);
