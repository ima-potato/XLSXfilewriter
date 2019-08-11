package com.lilo.liloproject.dto;

public class TimeInStatusDTO
{
    public TimeInStatusDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeInLocation() {
        return timeInLocation;
    }

    public void setTimeInLocation(String timeInLocation) {
        this.timeInLocation = timeInLocation;
    }

    private String status;
    private String timeInLocation;

    public TimeInStatusDTO(String status, String timeInLocation) {
        this.status = status;
        this.timeInLocation = timeInLocation;
    }


}
