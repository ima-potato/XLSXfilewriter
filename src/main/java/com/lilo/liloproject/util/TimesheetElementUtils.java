package com.lilo.liloproject.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class TimesheetElementUtils {


    public Boolean setWeekend(LocalDateTime entry) {
        return entry.getDayOfWeek() == DayOfWeek.SATURDAY || entry.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public Double nightShiftDifferentialCalculator(double hours) {
        return null;
    }
}
