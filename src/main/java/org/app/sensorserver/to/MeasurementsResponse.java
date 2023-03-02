package org.app.sensorserver.to;

import java.util.List;

public class MeasurementsResponse {
    private List<MeasurementTo> measurements;

    public MeasurementsResponse(List<MeasurementTo> measurements) {
        this.measurements = measurements;
    }

    public List<MeasurementTo> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementTo> measurements) {
        this.measurements = measurements;
    }
}
