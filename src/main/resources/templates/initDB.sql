DROP TABLE IF EXISTS measurement;
DROP TABLE IF EXISTS sensor;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 1;

CREATE TABLE sensor(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR UNIQUE NOT NULL,
    registered timestamp DEFAULT now() NOT NULL
);

CREATE TABLE measurement
(
    id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    sensor_id    INTEGER NOT NULL,
    value DOUBLE PRECISION NOT NULL,
    raining BOOLEAN NOT NULL,
    registered timestamp DEFAULT now() NOT NULL,
    FOREIGN KEY (sensor_id) REFERENCES sensor (id) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE UNIQUE INDEX measurement_idx ON measurement (id);
CREATE UNIQUE INDEX sensors_idx ON measurement (id,sensor_id);
CREATE UNIQUE INDEX raining_idx ON measurement (id,raining);


