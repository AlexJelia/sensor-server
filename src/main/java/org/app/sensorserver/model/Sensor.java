package org.app.sensorserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3,max = 30,message = "Name from 3 to 30 symbols")
    @Column(name = "name",unique = true)
    private String name;

    @NotNull
    @Column(name = "registered",  columnDefinition = "timestamp default now()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registered = new Date();

    @JsonIgnore
    @OneToMany(mappedBy = "sensor")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Measurement> measurements;

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
