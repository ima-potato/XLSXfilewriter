/**
 * Author: Aviv Guese
 * <p>
 * <p>
 * Utility that formats timelogs and timesheet into an object that is format specific and
 * will be used in the display and download of timesheet
 **/
package com.lilo.liloproject.util;

import com.lilo.liloproject.dto.TimesheetEntryDTO;
import com.lilo.liloproject.dto.TimesheetFormatDTO;
import com.lilo.liloproject.model.TimeLog;
import com.lilo.liloproject.model.Timesheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;



@Service
public class TimesheetUtils extends TimesheetElementUtils {
    private List<TimesheetEntryDTO> entryList = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(TimesheetUtils.class);

    public TimesheetFormatDTO timesheetGenerator(Timesheet timesheet, String timezone) {
        if (timesheet == null) {
            return blankTimesheetGenerator(timezone);
        } else return timesheetFormatter(timesheet, timezone);
    }

    public TimesheetFormatDTO timesheetFormatter(Timesheet timesheet, String timezone) {
        System.out.println(timesheet);
        List<TimeLog> timeLogs = timesheet.getTimeLogs();
        ZonedDateTime tsStartDate = timesheet.getStartDateTime().withZoneSameInstant(ZoneId.of(timezone));
        ZonedDateTime tsEndDate = timesheet.getEndDateTime().withZoneSameInstant(ZoneId.of(timezone));

        log.info("ORIGINAL LOG:" + timeLogs.get(0).getTimeIn());
        ZonedDateTime initialLog = timeLogs.get(0).getTimeIn().withZoneSameInstant(ZoneId.of(timezone));
        log.info("ORIGINAL LOG:" + initialLog);
        ZonedDateTime lastLog = timeLogs.get(timeLogs.size() - 1).getTimeIn().withZoneSameInstant(ZoneId.of(timezone));

        //convert extremesLog into desired zone
        int initialEntryDate = initialLog.getDayOfMonth();
        int lastEntryDate = lastLog.getDayOfMonth();
        int timesheetStartDate =tsStartDate.getDayOfMonth();
        int timesheetEndDate = 15;

        TimesheetFormatDTO formattedTimesheet = new TimesheetFormatDTO();
        formattedTimesheet.setStartDateTime(tsStartDate);
        formattedTimesheet.setEndDateTime(tsEndDate);

        if (timesheetStartDate == 16) {
            timesheetEndDate = initialLog.toLocalDate().lengthOfMonth();

        }

        if (initialEntryDate != timesheetStartDate) {
//create entry : subtract
            for (int i = initialEntryDate - timesheetStartDate; i > 0; i--) {
                String entry = initialLog.toLocalDateTime().minusDays(i).toString();
                entryList.add(new TimesheetEntryDTO(entry, null, null, true));
            }
        }

        for (int i = 0; i < timesheet.getTimeLogs().size(); i++) {

            String timeIn = "";
            String timeOut = "";

            TimeLog timeLog = timesheet.getTimeLogs().get(i);
            //add the log if next log is not the succeeding day
                if (!timeLog.getTimeIn().toString().equals("")) {
                    timeIn = timeLog.getTimeIn().withZoneSameInstant(ZoneId.of(timezone)).toLocalDateTime().toString();
                }
                if (!timeLog.getTimeOut().toString().equals("")) {
                    timeOut = timeLog.getTimeOut().withZoneSameInstant(ZoneId.of(timezone)).toLocalDateTime().toString();
                }



            Boolean isHoliday = false;
            Boolean isWeekend = false;
            Boolean hasNighshift = false;
            Boolean isPlaceholder = false;
            Double nightShiftHours = 0.0;
            Double holidayHours = 0.0;
            Double weekendHours = 0.0;
            Double overtimeHours = 0.0;
            int sandwichedEntryCounter = 0;
            int currentLogDayValue = timeLog.getTimeIn().withZoneSameInstant(ZoneId.of(timezone)).getDayOfMonth();

            int firstCutOffEnd = 15;
            int secondCutOffEnd = timeLog.getTimeIn().getMonth().maxLength();

            //end of the month filler

            if (i != timesheet.getTimeLogs().size() - 1) {
                int succeedingLogDayValue = timesheet.getTimeLogs().get(i + 1).getTimeIn().
                        withZoneSameInstant(ZoneId.of(timezone)).getDayOfMonth();

                entryList.add(new TimesheetEntryDTO(timeIn, timeOut, false));
                if (succeedingLogDayValue != currentLogDayValue + 1) {
                    for (int j = 0; j < succeedingLogDayValue - currentLogDayValue - 1; j++) {
                        String entry = timeLog.getTimeIn().withZoneSameInstant(ZoneId.of(timezone)).toLocalDateTime().plusDays(j + 1).toString();
                        entryList.add(new TimesheetEntryDTO(entry, null, null, true));
                    }

                }

            } else entryList.add(new TimesheetEntryDTO(timeIn, timeOut, false));

        }

        if (lastLog.getDayOfMonth() != timesheetEndDate) {
            for (int i = 1; i <= timesheetEndDate - lastEntryDate; i++) {
                String entry = lastLog.toLocalDateTime().plusDays(i).toString();
                entryList.add(new TimesheetEntryDTO(entry, null, null, true));
            }
        }

        formattedTimesheet.setEntryList(entryList);
        return formattedTimesheet;
    }

    public TimesheetEntryDTO entryFormatter(TimeLog timeLog) {

        return null;
    }

    public TimesheetFormatDTO blankTimesheetGenerator(String timezone) {
        TimesheetFormatDTO timesheetFormatDTO = new TimesheetFormatDTO();
        int offsetDay = 0;
        int timesheetEndDate = 15;
        int timesheetCutOffStartDate = 1;
        if (LocalDate.now().getDayOfMonth() >= 16) {
            System.out.println(LocalDateTime.now().getDayOfMonth() + "is greater than 16");
            offsetDay = 16;
            timesheetEndDate = LocalDate.now().getMonth().maxLength();
            timesheetCutOffStartDate = 16;
        }

        for (int i = offsetDay; i <= timesheetEndDate-1; i++) {
            System.out.println("month value now is " + LocalDateTime.now().getMonthValue());
//            int monthNumber = LocalDateTime.now().getMonthValue();
            int monthNumber = ZonedDateTime.now().withZoneSameInstant(ZoneId.of(timezone)).toLocalDate().getMonthValue();
            String placeHolderLog = "2019-" + String.format("%02d", monthNumber) + "-" +
                    String.format("%02d", timesheetCutOffStartDate) + "T07:00:00";
            String entry = LocalDateTime.parse(placeHolderLog).plusDays(i).toString();
            entryList.add(new TimesheetEntryDTO(entry, null, null, true));

        }
        timesheetFormatDTO.setEntryList(entryList);
        return timesheetFormatDTO;
    }


}
