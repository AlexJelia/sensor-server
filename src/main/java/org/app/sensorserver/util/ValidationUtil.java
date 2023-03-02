package org.app.sensorserver.util;

import org.app.sensorserver.model.Sensor;
import org.app.sensorserver.repository.MeasurementRepository;
import org.app.sensorserver.repository.SensorRepository;
import org.app.sensorserver.to.MeasurementTo;
import org.app.sensorserver.to.SensorTo;
import org.app.sensorserver.util.exceptions.NotCreatedException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Objects;

@Component
public class ValidationUtil {

    private final SensorRepository sensorRepository;
    private final MeasurementRepository measurementRepository;

    public ValidationUtil(SensorRepository sensorRepository, MeasurementRepository measurementRepository) {
        this.sensorRepository = sensorRepository;
        this.measurementRepository = measurementRepository;
    }

    public void validateSensor(SensorTo sensorTo, BindingResult bindingResult) {
        Sensor dbSensor = sensorRepository.findByName(sensorTo.getName());
        if (dbSensor != null && Objects.equals(dbSensor.getName(), sensorTo.getName())) {
            bindingResult.rejectValue("name", "", "This name is already taken");
            handle(bindingResult);
        } else if (bindingResult.hasErrors()) {
            handle(bindingResult);
        }
    }

    public void validateMeasurement(MeasurementTo measurementTo, BindingResult bindingResult) {
        Sensor dbSensor = sensorRepository.findByName(measurementTo.getSensor().getName());
        if (dbSensor == null) {
            bindingResult.rejectValue("sensor", "", "This sensor does not exist");
            handle(bindingResult);
        } else {
            measurementTo.getSensor().setId(dbSensor.getId());
        }
        if (bindingResult.hasErrors()) {
            handle(bindingResult);
        }
    }

    public void handle(BindingResult bindingResult) {

        StringBuilder errorMsg = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMsg.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        throw new NotCreatedException(errorMsg.toString());
    }
}
