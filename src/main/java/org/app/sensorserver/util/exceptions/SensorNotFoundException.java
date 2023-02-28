package org.app.sensorserver.util.exceptions;
//todo implement
public class SensorNotFoundException extends RuntimeException{
    public SensorNotFoundException() {
        this("Sensor not found");
    }
    public SensorNotFoundException(String message) {
        super(message);
    }
}
