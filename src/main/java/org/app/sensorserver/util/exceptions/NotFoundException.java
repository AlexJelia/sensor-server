package org.app.sensorserver.util.exceptions;

//TODO implement
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        this("Not found");
    }
    public NotFoundException(String message) {
        super(message);
    }
}
