CREATE DATABASE IF NOT EXISTS geography;
USE geography;
DROP TABLE IF EXISTS continents;
DROP TABLE IF EXISTS countries;

CREATE TABLE continent (
   id   SERIAL PRIMARY KEY,
   name VARCHAR(50) NOT NULL
);

CREATE TABLE country (
   id        SERIAL PRIMARY KEY,
   name      VARCHAR(100) NOT NULL,
   code      VARCHAR(10) NOT NULL,
   
   idContinent INTEGER FOREIGN KEY
      REFERENCES continent ( id )
);

CREATE TABLE city (
   id         INT PRIMARY KEY AUTO_INCREMENT,
   name       VARCHAR(100) NOT NULL,
   idCountry INT,
   capital    BOOLEAN,
   latitude   DOUBLE,
   longitude  DOUBLE,
   
   idCountry INTEGER FOREIGN KEY
      REFERENCES country ( id )
);
