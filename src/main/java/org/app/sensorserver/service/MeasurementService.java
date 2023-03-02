package org.app.sensorserver.service;

import org.app.sensorserver.model.Measurement;
import org.app.sensorserver.repository.MeasurementRepository;
import org.app.sensorserver.util.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Transactional
    public void add(Measurement measurement){
        measurementRepository.save(measurement);
    }

    public List<Measurement> getMeasurements(){
        return  measurementRepository.findAll();
    }

    public Measurement getMeasurement(int id){
        return  measurementRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Long getRainyDaysCount(){
        return  measurementRepository.findAll().stream().filter(Measurement::isRaining).count();
    }
}
