package com.lilo.liloproject.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TimeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ZonedDateTime timeIn;
    private ZonedDateTime timeOut;
    private Boolean isRemote;
    private String timeInLocation;
    private String timeOutLocation;
    private String employeeID;
    private Boolean isEdited;
    private Boolean isHoliday;
    private Boolean isWeekend;
    private String remarks;

    @OneToOne
    private EditedTimeLog editedTimeLog;

    @ManyToOne
    private Timesheet timesheet;

    public TimeLog() {
    }

    public TimeLog(ZonedDateTime timeIn, ZonedDateTime timeOut, Boolean isRemote, String timeInLocation, String timeOutLocation, String employeeID, Boolean isEdited, Boolean isHoliday, String remarks, EditedTimeLog editedTimeLog, Timesheet timesheet) {
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.isRemote = isRemote;
        this.timeInLocation = timeInLocation;
        this.timeOutLocation = timeOutLocation;
        this.employeeID = employeeID;
        this.isEdited = isEdited;
        this.isHoliday = isHoliday;
        this.remarks = remarks;
        this.editedTimeLog = editedTimeLog;
        this.timesheet = timesheet;
    }

    public TimeLog(ZonedDateTime timeIn, ZonedDateTime timeOut, Boolean isRemote, String timeInLocation, String timeOutLocation, String employeeID, Boolean isEdited, Boolean isHoliday, Boolean isWeekend, String remarks, EditedTimeLog editedTimeLog, Timesheet timesheet) {
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.isRemote = isRemote;
        this.timeInLocation = timeInLocation;
        this.timeOutLocation = timeOutLocation;
        this.employeeID = employeeID;
        this.isEdited = isEdited;
        this.isHoliday = isHoliday;
        this.isWeekend = isWeekend;
        this.remarks = remarks;
        this.editedTimeLog = editedTimeLog;
        this.timesheet = timesheet;
    }

    public TimeLog(ZonedDateTime timeIn, ZonedDateTime timeOut, Boolean isRemote, String timeInLocation, String timeOutLocation, String employeeID, Boolean isEdited, EditedTimeLog referenceTimeLog, Timesheet timesheet) {
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.isRemote = isRemote;
        this.timeInLocation = timeInLocation;
        this.timeOutLocation = timeOutLocation;
        this.employeeID = employeeID;
        this.isEdited = isEdited;
        this.editedTimeLog = referenceTimeLog;
        this.timesheet = timesheet;
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

    public Boolean isRemote() {
        return isRemote;
    }

    public void setRemote(Boolean remote) {
        isRemote = remote;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public Boolean getEdited() {
        return isEdited;
    }

    public void setEdited(Boolean edited) {
        isEdited = edited;
    }

    public EditedTimeLog getReferenceTimeLog() {
        return editedTimeLog;
    }

    public void setReferenceTimeLog(EditedTimeLog editedTimeLog) {
        this.editedTimeLog = editedTimeLog;
    }

    public EditedTimeLog getEditedTimeLog() {
        return editedTimeLog;
    }

    public void setEditedTimeLog(EditedTimeLog editedTimeLog) {
        this.editedTimeLog = editedTimeLog;
    }

    public Timesheet getTimesheet() {
        return timesheet;
    }

    public void setTimesheet(Timesheet timesheet) {
        this.timesheet = timesheet;
    }

    public Boolean getRemote() {
        return isRemote;
    }

    public Boolean getHoliday() {
        return isHoliday;
    }

    public void setHoliday(Boolean holiday) {
        isHoliday = holiday;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getWeekend() {
        return isWeekend;
    }

    public void setWeekend(Boolean weekend) {
        isWeekend = weekend;
    }

    @Override
    public String toString() {
        return "TimeLog{" +
                "id=" + id +
                ", timeIn=" + timeIn +
                ", timeOut=" + timeOut +
                ", isRemote=" + isRemote +
                ", timeInLocation='" + timeInLocation + '\'' +
                ", timeOutLocation='" + timeOutLocation + '\'' +
                ", employeeID='" + employeeID + '\'' +
                ", isEdited=" + isEdited +
                ", isHoliday=" + isHoliday +
                ", isWeekend=" + isWeekend +
                ", remarks='" + remarks + '\'' +
                ", editedTimeLog=" + editedTimeLog +
                ", timesheet=" + timesheet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeLog timeLog = (TimeLog) o;
        return id.equals(timeLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
