package com.lilo.liloproject.dto;

import com.lilo.liloproject.model.TimeLog;

import java.time.ZonedDateTime;
import java.util.List;

public class TimesheetDTO {

    private String employeeID;
    private String status;
    private ZonedDateTime startDateTime;
    private ZonedDateTime endDateTime;
    private List<TimeLog> timeLogs;

    public TimesheetDTO() {
    }

    public TimesheetDTO(String employeeID, String status, ZonedDateTime startDateTime, ZonedDateTime endDateTime, List<TimeLog> timeLogs) {
        this.employeeID = employeeID;
        this.status = status;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.timeLogs = timeLogs;
    }

    public TimesheetDTO(String employeeID, String status, ZonedDateTime startDateTime, ZonedDateTime endDateTime) {
        this.employeeID = employeeID;
        this.status = status;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "TimesheetDTO{" +
                "employeeID='" + employeeID + '\'' +
                ", status='" + status + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", timeLogs=" + timeLogs +
                '}';
    }
}
