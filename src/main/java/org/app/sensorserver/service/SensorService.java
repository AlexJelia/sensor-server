package org.app.sensorserver.service;

import org.app.sensorserver.model.Sensor;
import org.app.sensorserver.repository.SensorRepository;
import org.app.sensorserver.util.exceptions.SensorNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void register(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public List<Sensor> getSensors(){
         return sensorRepository.findAll();
    }

    public Sensor getSensor(int id) {
        return sensorRepository.findById(id).orElseThrow(SensorNotFoundException::new);
    }
}
