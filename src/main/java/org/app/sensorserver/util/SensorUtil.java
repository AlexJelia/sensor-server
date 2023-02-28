package org.app.sensorserver.util;

import org.app.sensorserver.model.Sensor;
import org.app.sensorserver.to.SensorTo;

import java.util.Date;

public class SensorUtil {

    public static Sensor createNewFromTo(SensorTo to) {
        Sensor sensor = new Sensor();
        sensor.setName(to.getName());
        sensor.setRegistered(new Date());
        return sensor;
    }

    public static SensorTo asTo(Sensor sensor) {
        return new SensorTo(sensor.getName());
    }
}
