package org.app.sensorserver.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
    @NotNull
    @Min(-100)
    @Max(100)
    private double value;

    @Column(name = "raining")
    @NotNull
    private boolean raining;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor_id",referencedColumnName = "id")
    private Sensor sensor;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date registered = new Date();

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

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }
}
