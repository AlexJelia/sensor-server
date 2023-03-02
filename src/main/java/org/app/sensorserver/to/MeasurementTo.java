package org.app.sensorserver.to;

import org.app.sensorserver.model.Sensor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasurementTo {

    @NotNull
    @Min(-100)
    @Max(100)
    private double value;

    @NotNull
    private boolean raining;

    @NotNull
    private Sensor sensor;

    public MeasurementTo() {
    }

    public MeasurementTo(double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
