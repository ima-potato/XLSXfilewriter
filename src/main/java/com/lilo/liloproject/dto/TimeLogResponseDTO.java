package com.lilo.liloproject.dto;

import java.time.ZonedDateTime;

/**
 * Author: Tyler Venzon
 *
 * Data Transfer Object used for both time in and time out transactions; either through
 * the terminal or the dashboard.
 *
 *
 * From back-end to front-end
 *
 * As of 7/15/2019:
 * 1.) zone contains the timezone of the back-end
 * 2.) ldtTimeIn contains the String equivalent of the LocalDateTime of the recorded time in date and time.
 * 3.) ldtTimeOut contains the String equivalent of the LocalDateTime of the recorded time in date and time.
 * 4.) strTimeIn contains the String equivalent of the ZonedDateTime of the recorded time in date and time.
 * 5.) strTimeOut contains the String equivalent of the ZonedDateTime of the recorded time in date and time.
 * 5.) duration contains the duration from the time in and time out date and time in hh:mm:ss format.
 * 6.) message contains remarks written from the back-end for possible front-end handling.
 *
 **/
public class TimeLogResponseDTO {
    private String zone;
    private ZonedDateTime timeIn;
    private ZonedDateTime timeOut;
    private String ldtTimeIn;
    private String ldtTimeOut;
    private String strTimeIn;
    private String strTimeOut;
    private String duration;
    private String timeInLocation;
    private String timeOutLocation;
    private String employeeID;
    private String message;

    public TimeLogResponseDTO() {
    }

    public TimeLogResponseDTO(ZonedDateTime timeIn, ZonedDateTime timeOut, String strTimeIn, String strTimeOut, String duration, String timeInLocation, String timeOutLocation, String employeeID, String message) {
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.strTimeIn = strTimeIn;
        this.strTimeOut = strTimeOut;
        this.duration = duration;
        this.timeInLocation = timeInLocation;
        this.timeOutLocation = timeOutLocation;
        this.employeeID = employeeID;
        this.message = message;
    }

    public TimeLogResponseDTO(ZonedDateTime timeIn, ZonedDateTime timeOut, String ldtTimeIn, String ldtTimeOut, String strTimeIn, String strTimeOut, String duration, String timeInLocation, String timeOutLocation, String employeeID, String message) {
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.ldtTimeIn = ldtTimeIn;
        this.ldtTimeOut = ldtTimeOut;
        this.strTimeIn = strTimeIn;
        this.strTimeOut = strTimeOut;
        this.duration = duration;
        this.timeInLocation = timeInLocation;
        this.timeOutLocation = timeOutLocation;
        this.employeeID = employeeID;
        this.message = message;
    }

    public TimeLogResponseDTO(String zone, ZonedDateTime timeIn, ZonedDateTime timeOut, String ldtTimeIn, String ldtTimeOut, String strTimeIn, String strTimeOut, String duration, String timeInLocation, String timeOutLocation, String employeeID, String message) {
        this.zone = zone;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.ldtTimeIn = ldtTimeIn;
        this.ldtTimeOut = ldtTimeOut;
        this.strTimeIn = strTimeIn;
        this.strTimeOut = strTimeOut;
        this.duration = duration;
        this.timeInLocation = timeInLocation;
        this.timeOutLocation = timeOutLocation;
        this.employeeID = employeeID;
        this.message = message;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStrTimeIn() {
        return strTimeIn;
    }

    public void setStrTimeIn(String strTimeIn) {
        this.strTimeIn = strTimeIn;
    }

    public String getStrTimeOut() {
        return strTimeOut;
    }

    public void setStrTimeOut(String strTimeOut) {
        this.strTimeOut = strTimeOut;
    }

    public String getLdtTimeIn() {
        return ldtTimeIn;
    }

    public void setLdtTimeIn(String ldtTimeIn) {
        this.ldtTimeIn = ldtTimeIn;
    }

    public String getLdtTimeOut() {
        return ldtTimeOut;
    }

    public void setLdtTimeOut(String ldtTimeOut) {
        this.ldtTimeOut = ldtTimeOut;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}

