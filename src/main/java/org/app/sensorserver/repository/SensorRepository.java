package org.app.sensorserver.repository;

import org.app.sensorserver.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor,Integer> {
    Sensor findByName(String name);
}
