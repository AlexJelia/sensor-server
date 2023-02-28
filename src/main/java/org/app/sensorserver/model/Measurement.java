package org.app.sensorserver.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "measurement")
public class Measurement {
    public static final int START_SEQ = 1;
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private int id;

    @Column(name = "value")
    @Size(min = -100,max = 100,message = "Temperature from -100 to 100")
    private double value;

    @Column(name = "raining")
    private boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_id",referencedColumnName = "id")
    private Sensor sensor;

    public Measurement() {
    }

    //todo check
    public Measurement(int id, double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
