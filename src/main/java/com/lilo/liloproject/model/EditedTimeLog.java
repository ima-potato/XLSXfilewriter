package com.lilo.liloproject.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class EditedTimeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ZonedDateTime timeIn;
    private ZonedDateTime timeOut;
    private Boolean isRemote;
    private String timeInLocation;
    private String timeOutLocation;
    private String employeeID;

    @OneToOne
    private TimeLog referenceTimeLog;

    @ManyToOne
    private Timesheet timesheet;

    public EditedTimeLog() {
    }

    public EditedTimeLog(ZonedDateTime timeIn, ZonedDateTime timeOut, Boolean isRemote, String timeInLocation, String timeOutLocation, String employeeID, TimeLog referenceTimeLog, Timesheet timesheet) {
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.isRemote = isRemote;
        this.timeInLocation =timeInLocation;
        this.timeOutLocation = timeOutLocation;
        this.employeeID = employeeID;
        this.referenceTimeLog = referenceTimeLog;
        this.timesheet = timesheet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(ZonedDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public ZonedDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(ZonedDateTime timeOut) {
        this.timeOut = timeOut;
    }

    public Boolean getRemote() {
        return isRemote;
    }

    public void setRemote(Boolean remote) {
        isRemote = remote;
    }

    public String getTimeInLocation() {
        return timeInLocation;
    }

    public void setTimeInLocation(String timeInLocation) {
        this.timeInLocation = timeInLocation;
    }

    public String getTimeOutLocation() {
        return timeOutLocation;
    }

    public void setTimeOutLocation(String timeOutLocation) {
        this.timeOutLocation = timeOutLocation;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public TimeLog getReferenceTimeLog() {
        return referenceTimeLog;
    }

    public void setReferenceTimeLog(TimeLog referenceTimeLog) {
        this.referenceTimeLog = referenceTimeLog;
    }

    public Timesheet getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
    }
}
