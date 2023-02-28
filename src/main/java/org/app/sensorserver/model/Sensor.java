package org.app.sensorserver.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {

    public static final int START_SEQ = 1;
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private int id;

    @NotEmpty
    @Size(min = 3,max = 30,message = "Name from 3 to 30 symbols")
    @Column(name = "name")
    private String name;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registered = new Date();


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "sensor")
    private List<Measurement> measurements;

    public Sensor() {
    }

    //todo check
    public Sensor(int id, String name, Date registered) {
        this.id = id;
        this.name = name;
        this.registered = registered;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
