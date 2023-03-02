package org.app.sensorserver.to;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorTo {

    @NotEmpty
    @Size(min = 3,max = 30,message = "Name from 3 to 30 symbols")
    private String name;

    public SensorTo() {
    }

    public SensorTo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
