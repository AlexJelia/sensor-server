package org.app.sensorserver.util;

import org.app.sensorserver.util.exceptions.SensorNotCreatedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ValidationUtil {

    public static void validate(BindingResult bindingResult){
        StringBuilder errorMsg = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for(FieldError error:errors){
            errorMsg.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append(";");
        }
        throw new SensorNotCreatedException(errorMsg.toString());

    }
}
