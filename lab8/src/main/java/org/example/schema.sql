CREATE TABLE continents (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(50) NOT NULL
);

CREATE TABLE countries (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           code VARCHAR(10) NOT NULL,
                           continent INTEGER REFERENCES continents(id)
);
