package com.lilo.liloproject.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeID;

    //Pending, Approved,
    private String status;
    private ZonedDateTime startDateTime;
    private ZonedDateTime endDateTime;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<TimeLog> timeLogs;

    public Timesheet() {
    }

    public Timesheet(String employeeID, String status, ZonedDateTime startDateTime, ZonedDateTime endDateTime, List<TimeLog> timeLogs) {
        this.employeeID = employeeID;
        this.status = status;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.timeLogs = timeLogs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }


    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public List<TimeLog> getTimeLogs() {
        return timeLogs;
    }

    public void setTimeLogs(List<TimeLog> timeLogs) {
        this.timeLogs = timeLogs;
    }


    @Override
    public String toString() {
        return "Timesheet{" +
                "id=" + id +
                ", employeeID='" + employeeID + '\'' +
                ", status='" + status + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", timeLogs=" + timeLogs.size() +
                '}';
    }
}
