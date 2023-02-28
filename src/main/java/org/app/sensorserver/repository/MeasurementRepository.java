package org.app.sensorserver.repository;

import org.app.sensorserver.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {

}
