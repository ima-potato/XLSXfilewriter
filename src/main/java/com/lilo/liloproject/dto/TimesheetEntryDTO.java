package com.lilo.liloproject.dto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class TimesheetEntryDTO {
    private String timeIn;
    private String timeOut;
    private DayOfWeek dayIn;
    private LocalDateTime entryDate;
    private Boolean isHoliday;
    private Boolean isWeekend;
    private Boolean hasNighshift;
    private Boolean isPlaceholder;
    private Double nightShiftHours;
    private Double holidayHours;
    private Double weekendHours;
    private Double overtimeHours;


    public TimesheetEntryDTO() {
    }

    public TimesheetEntryDTO(String timeIn, Boolean isHoliday, Boolean isWeekend, Boolean isPlaceholder) {
        this.timeIn = timeIn;
        this.isHoliday = isHoliday;
        this.isWeekend = isWeekend;
        this.isPlaceholder = isPlaceholder;
    }

    public TimesheetEntryDTO(String timeIn, String timeOut, DayOfWeek dayIn, LocalDateTime entryDate, Boolean isHoliday, Boolean isWeekend, Boolean hasNighshift, Double nightShiftHours, Double holidayHours, Double weekendHours, Double overtimeHours) {
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.dayIn = dayIn;
        this.entryDate = entryDate;
        this.isHoliday = isHoliday;
        this.isWeekend = isWeekend;
        this.hasNighshift = hasNighshift;
        this.nightShiftHours = nightShiftHours;
        this.holidayHours = holidayHours;
        this.weekendHours = weekendHours;
        this.overtimeHours = overtimeHours;
    }

    public TimesheetEntryDTO(String timeIn, String timeOut, DayOfWeek dayIn, Boolean isHoliday, Boolean isWeekend, Boolean hasNighshift, Boolean isPlaceholder, Double nightShiftHours, Double holidayHours, Double weekendHours, Double overtimeHours) {
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.dayIn = dayIn;
        this.isHoliday = isHoliday;
        this.isWeekend = isWeekend;
        this.hasNighshift = hasNighshift;
        this.isPlaceholder = isPlaceholder;
        this.nightShiftHours = nightShiftHours;
        this.holidayHours = holidayHours;
        this.weekendHours = weekendHours;
        this.overtimeHours = overtimeHours;
    }

    public TimesheetEntryDTO(String timeIn, String timeOut, Boolean isPlaceholder) {
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.isPlaceholder = isPlaceholder;
    }

    public Boolean getPlaceholder() {
        return isPlaceholder;
    }

    public void setPlaceholder(Boolean placeholder) {
        isPlaceholder = placeholder;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public DayOfWeek getDayIn() {
        return dayIn;
    }

    public void setDayIn(DayOfWeek dayIn) {
        this.dayIn = dayIn;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public Boolean getHoliday() {
        return isHoliday;
    }

    public void setHoliday(Boolean holiday) {
        isHoliday = holiday;
    }

    public Boolean getWeekend() {
        return isWeekend;
    }

    public void setWeekend(Boolean weekend) {
        isWeekend = weekend;
    }

    public Boolean getHasNighshift() {
        return hasNighshift;
    }

    public void setHasNighshift(Boolean hasNighshift) {
        this.hasNighshift = hasNighshift;
    }

    public Double getNightShiftHours() {
        return nightShiftHours;
    }

    public void setNightShiftHours(Double nightShiftHours) {
        this.nightShiftHours = nightShiftHours;
    }

    public Double getHolidayHours() {
        return holidayHours;
    }

    public void setHolidayHours(Double holidayHours) {
        this.holidayHours = holidayHours;
    }

    public Double getWeekendHours() {
        return weekendHours;
    }

    public void setWeekendHours(Double weekendHours) {
        this.weekendHours = weekendHours;
    }

    public Double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(Double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    @Override
    public String toString() {
        return "TimesheetEntryDTO{" +
                "timeIn=" + timeIn +
                ", timeOut=" + timeOut +
                ", dayIn='" + dayIn + '\'' +
                ", entryDate=" + entryDate +
                ", isHoliday=" + isHoliday +
                ", isWeekend=" + isWeekend +
                ", hasNighshift=" + hasNighshift +
                ", isPlaceholder=" + isPlaceholder +
                ", nightShiftHours=" + nightShiftHours +
                ", holidayHours=" + holidayHours +
                ", weekendHours=" + weekendHours +
                ", overtimeHours=" + overtimeHours +
                '}';
    }
}
