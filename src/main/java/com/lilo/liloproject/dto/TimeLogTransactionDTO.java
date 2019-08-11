package com.lilo.liloproject.dto;


/**
 * Author: Tyler Venzon
 *
 * Data Transfer Object used for both time in and time out transactions; either through
 * the terminal or the dashboard.
 *
 * From the front end to back end
 *
 * As of 7/15/2019:
 * 1.) time : includes both datetime and zone but is only used to get the timezone of the user
 * 2.) isRemote: is set to true when timed in from dashboard and false for terminal transactions
 * 3.) location: is set to null from dashboard and "Kiosk" for terminal trnsactions
 * 4.) employeeID: contains the users employeeID/login ID (1X-XXXX)
 * 5.) isHoliday: unused
 * 6.) remarks: used to relay additional messages either from backend or frontend
 *
 */

public class TimeLogTransactionDTO {
    private String time;
    private Boolean isRemote;
    private String location;
    private String employeeID;
    private Boolean isHoliday;
    private String remarks;

    public TimeLogTransactionDTO() {
    }

    public TimeLogTransactionDTO(String time, Boolean isRemote, String location, String employeeID, Boolean isHoliday, String remarks) {
        this.time = time;
        this.isRemote = isRemote;
        this.location = location;
        this.employeeID = employeeID;
        this.isHoliday = isHoliday;
        this.remarks = remarks;
    }

    public TimeLogTransactionDTO(String time, Boolean isRemote, String location, String employeeID) {
        this.time = time;
        this.isRemote = isRemote;
        this.location = location;
        this.employeeID = employeeID;
    }

//    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
//
//    public Boolean isRemote() {
//        return isRemote;
//    }
//
//    public void setRemote(Boolean remote) {
//        isRemote = remote;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public String getEmployeeID() {
//        return employeeID;
//    }
//
//    public void setEmployeeID(String employeeID) {
//        this.employeeID = employeeID;
//    }
//
//    public Boolean getRemote() {
//        return isRemote;
//    }
//
//    public Boolean isHoliday() {
//        return isHoliday;
//    }
//
//    public void setHoliday(Boolean holiday) {
//        isHoliday = holiday;
//    }
//
//    public String getRemarks() {
//        return remarks;
//    }
//
//    public void setRemarks(String remarks) {
//        this.remarks = remarks;
//    }
//
//    public Boolean getHoliday() {
//        return isHoliday;
//    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getRemote() {
        return isRemote;
    }

    public void setRemote(Boolean remote) {
        isRemote = remote;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
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

    @Override
    public String toString() {
        return "TimeLogTransactionDTO{" +
                "time=" + time +
//                ", isRemote=" + isRemote +
//                ", location='" + location + '\'' +
//                ", employeeID='" + employeeID + '\'' +
//                ", isHoliday=" + isHoliday +
//                ", remarks='" + remarks + '\'' +
                '}';
    }
}
