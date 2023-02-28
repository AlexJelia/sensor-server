package org.app.sensorserver.web;

import org.app.sensorserver.service.SensorService;
import org.app.sensorserver.to.SensorTo;
import org.app.sensorserver.util.SensorErrorResponse;
import org.app.sensorserver.util.SensorUtil;
import org.app.sensorserver.util.exceptions.SensorNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.app.sensorserver.util.SensorUtil.asTo;
import static org.app.sensorserver.util.SensorUtil.createNewFromTo;
import static org.app.sensorserver.util.ValidationUtil.validate;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService service;

    public SensorController(SensorService service) {
        this.service = service;
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
        if(bindingResult.hasErrors()){
            validate(bindingResult);
        }
        service.register(createNewFromTo(sensorTo));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e){
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
