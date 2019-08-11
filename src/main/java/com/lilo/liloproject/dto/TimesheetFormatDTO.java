package com.lilo.liloproject.dto;

import java.time.ZonedDateTime;
import java.util.List;

public class TimesheetFormatDTO {
    private String status;
    private ZonedDateTime startDateTime;
    private ZonedDateTime endDateTime;
    private List<TimesheetEntryDTO> entryList;

    public TimesheetFormatDTO() {
    }

    public TimesheetFormatDTO(String status, ZonedDateTime startDateTime, ZonedDateTime endDateTime, List<TimesheetEntryDTO> entryList) {
        this.status = status;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.entryList = entryList;
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

    public List<TimesheetEntryDTO> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<TimesheetEntryDTO> entryList) {
        this.entryList = entryList;
    }

    @Override
    public String toString() {
        return "TimesheetFormatDTO{" +
                "status='" + status + '\'' +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", entryList=" + entryList +
                '}';
    }
}
