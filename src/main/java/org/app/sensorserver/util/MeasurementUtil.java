package org.app.sensorserver.util;

import org.app.sensorserver.model.Measurement;
import org.app.sensorserver.to.MeasurementTo;

import java.util.Date;

public class MeasurementUtil {

    public static Measurement createNewFromTo(MeasurementTo to) {
        Measurement  measurement  = new Measurement ();

        measurement.setValue(to.getValue());
        measurement.setRaining(to.isRaining());
        measurement.setSensor(to.getSensor());
        measurement.setRegistered(new Date());

        return measurement;
    }

    public static MeasurementTo asTo(Measurement measurement) {
        return new MeasurementTo(measurement.getValue(),measurement.isRaining(),measurement.getSensor());
    }
}
