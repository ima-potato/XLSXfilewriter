package com.lilo.liloproject.dto;

public class TimeLogKioskDTO {

    private String employeeID;
    private String password;

    private String time;
    private Boolean isRemote;
    private String location;
    private Boolean isHoliday;
    private String remarks;

    public TimeLogKioskDTO() {

    }

    public TimeLogKioskDTO(String employeeID, String password, String time, Boolean isRemote, String location, Boolean isHoliday, String remarks) {
        this.employeeID = employeeID;
        this.password = password;
        this.time = time;
        this.isRemote = isRemote;
        this.location = location;
        this.isHoliday = isHoliday;
        this.remarks = remarks;
    }

    public TimeLogKioskDTO(String employeeID, String password) {
        this.employeeID = employeeID;
        this.password = password;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
        return "TimeLogKioskDTO{" +
                "employeeID='" + employeeID + '\'' +
                ", password='" + password + '\'' +
                ", time='" + time + '\'' +
                ", isRemote=" + isRemote +
                ", location='" + location + '\'' +
                ", isHoliday=" + isHoliday +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
