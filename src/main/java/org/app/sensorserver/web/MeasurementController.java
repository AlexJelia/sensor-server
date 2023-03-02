package org.app.sensorserver.web;

import org.app.sensorserver.service.MeasurementService;
import org.app.sensorserver.to.MeasurementTo;
import org.app.sensorserver.to.MeasurementsResponse;
import org.app.sensorserver.util.ErrorResponse;
import org.app.sensorserver.util.MeasurementUtil;
import org.app.sensorserver.util.ValidationUtil;
import org.app.sensorserver.util.exceptions.NotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.stream.Collectors;

import static org.app.sensorserver.util.MeasurementUtil.asTo;
import static org.app.sensorserver.util.MeasurementUtil.createNewFromTo;


@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService service;
    private final ValidationUtil validator;

    public MeasurementController(MeasurementService service, ValidationUtil validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping
    public MeasurementsResponse getMeasurements(){
        return new MeasurementsResponse(service.getMeasurements().stream()
                .map(MeasurementUtil::asTo)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public MeasurementTo getMeasurement(@PathVariable int id){
        return asTo(service.getMeasurement(id));
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid MeasurementTo measurementTo, BindingResult bindingResult){
        validator.validateMeasurement(measurementTo,bindingResult);
        service.add(createNewFromTo(measurementTo));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDaysCount() {
        return service.getRainyDaysCount();
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(NotCreatedException e){
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                new Date()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
