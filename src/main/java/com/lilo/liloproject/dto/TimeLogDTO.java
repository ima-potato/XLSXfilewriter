package com.lilo.liloproject.dto;

public class TimeLogDTO {
    private Long id;
    private String timeIn;
    private String dateIn;
    private String timeOut;
    private String dateOut;
    private String duration;

    public TimeLogDTO() {
    }

    public TimeLogDTO(Long id, String timeIn, String dateIn, String timeOut, String dateOut, String duration) {
        this.id = id;
        this.timeIn = timeIn;
        this.dateIn = dateIn;
        this.timeOut = timeOut;
        this.dateOut = dateOut;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "TimeLogDTO{" +
                "id=" + id +
                ", timeIn='" + timeIn + '\'' +
                ", dateIn='" + dateIn + '\'' +
                ", timeOut='" + timeOut + '\'' +
                ", dateOut='" + dateOut + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
