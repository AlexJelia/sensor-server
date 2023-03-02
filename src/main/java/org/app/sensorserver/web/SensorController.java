package org.app.sensorserver.web;

import org.app.sensorserver.service.SensorService;
import org.app.sensorserver.to.SensorTo;
import org.app.sensorserver.util.ErrorResponse;
import org.app.sensorserver.util.SensorUtil;
import org.app.sensorserver.util.ValidationUtil;
import org.app.sensorserver.util.exceptions.NotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.app.sensorserver.util.SensorUtil.asTo;
import static org.app.sensorserver.util.SensorUtil.createNewFromTo;


@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService service;
    private final ValidationUtil validator;

    public SensorController(SensorService service, ValidationUtil validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping("/{id}")
    public SensorTo getSensor(@PathVariable int id){
        return asTo(service.getSensor(id));
    }

    @GetMapping
    public List<SensorTo> getSensors (){
        return service.getSensors().stream()
                .map(SensorUtil::asTo)
                .collect(Collectors.toList());
    }

    @PostMapping("registration")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorTo sensorTo, BindingResult bindingResult){
        validator.validateSensor(sensorTo,bindingResult);
        service.register(createNewFromTo(sensorTo));
        return ResponseEntity.ok(HttpStatus.OK);
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
