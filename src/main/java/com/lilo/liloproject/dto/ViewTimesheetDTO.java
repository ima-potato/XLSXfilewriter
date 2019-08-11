package com.lilo.liloproject.dto;

import java.util.List;

public class ViewTimesheetDTO {
    List<TimeLogDTO> timeLogs;
    String totalWorkHours;

    public ViewTimesheetDTO() {
    }

    public ViewTimesheetDTO(List<TimeLogDTO> timeLogs, String totalWorkHours) {
        this.timeLogs = timeLogs;
        this.totalWorkHours = totalWorkHours;
    }

    public List<TimeLogDTO> getTimeLogs() {
        return timeLogs;
    }

    public void setTimeLogs(List<TimeLogDTO> timeLogs) {
        this.timeLogs = timeLogs;
    }

    public String getTotalWorkHours() {
        return totalWorkHours;
    }

    public void setTotalWorkHours(String totalWorkHours) {
        this.totalWorkHours = totalWorkHours;
    }

    @Override
    public String toString() {
        return "ViewTimesheetDTO{" +
                "timeLogs=" + timeLogs +
                ", totalWorkHours='" + totalWorkHours + '\'' +
                '}';
    }
}
