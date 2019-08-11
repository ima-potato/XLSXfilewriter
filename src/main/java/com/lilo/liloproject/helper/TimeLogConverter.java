package com.lilo.liloproject.helper;

import com.lilo.liloproject.dto.TimeLogDTO;
import com.lilo.liloproject.dto.ViewTimesheetDTO;
import com.lilo.liloproject.model.TimeLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TimeLogConverter {

    public ViewTimesheetDTO ConvertToTimeLogDTO(List<TimeLog> retrievedLogs, String timezone) {
        ViewTimesheetDTO viewTimesheetDTO = new ViewTimesheetDTO();
        List<TimeLogDTO> stringLogs = new ArrayList<>();
        Long cutoffDuration = 0L;

        for (TimeLog log: retrievedLogs) {
            log.setTimeIn(log.getTimeIn().withZoneSameInstant(ZoneId.of(timezone)));


            TimeLogDTO stringLog = new TimeLogDTO();


            stringLog.setId(log.getId());

            DateTimeFormatter military = DateTimeFormatter.ofPattern("HH:mm:ss");

            String timeIn = log.getTimeIn().format(military);
            stringLog.setTimeIn(timeIn);

            String dateIn = StringUtils.capitalize(log.getTimeIn().getMonth().toString().toLowerCase())  + " " +
                    log.getTimeIn().getDayOfMonth() + ", " +
                    log.getTimeIn().getYear();
            stringLog.setDateIn(dateIn);

            if (log.getTimeOut() != null) {
                log.setTimeOut(log.getTimeOut().withZoneSameInstant(ZoneId.of(timezone)));
                String timeOut = log.getTimeOut().format(military);
                stringLog.setTimeOut(timeOut);

                String dateOut = StringUtils.capitalize(log.getTimeOut().getMonth().toString().toLowerCase()) + " " +
                        log.getTimeOut().getDayOfMonth() + ", " +
                        log.getTimeOut().getYear();
                stringLog.setDateOut(dateOut);

                Long hour = Duration.between(log.getTimeIn(), log.getTimeOut()).toHours();
                Long minute = Duration.between(log.getTimeIn(), log.getTimeOut()).toMinutes()%60;
                Long second = (Duration.between(log.getTimeIn(), log.getTimeOut()).toMillis()/1000)%60;

                cutoffDuration += Duration.between(log.getTimeIn(), log.getTimeOut()).toMillis();

                String duration = hour + " hh " + minute + " mm " + second + " ss ";
                stringLog.setDuration(duration);

            } else {
                stringLog.setTimeOut("");
                stringLog.setDateOut("");
                stringLog.setDuration("");
            }

            stringLogs.add(stringLog);

        }

        String totalWorkHours = TimeUnit.MILLISECONDS.toHours(cutoffDuration) + " hh " +
                TimeUnit.MILLISECONDS.toMinutes(cutoffDuration)%60 + " mm " +
                TimeUnit.MILLISECONDS.toSeconds(cutoffDuration)%60 + " ss ";

        System.out.println("TOTAL WORK HOURS: " + totalWorkHours);

        Collections.reverse(stringLogs);
        viewTimesheetDTO.setTimeLogs(stringLogs);
        viewTimesheetDTO.setTotalWorkHours(totalWorkHours);

        return viewTimesheetDTO;
    }
}
