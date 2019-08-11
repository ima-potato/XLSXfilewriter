package com.lilo.liloproject.dao;

import com.lilo.liloproject.model.TimeLog;
import com.lilo.liloproject.model.Timesheet;
import com.lilo.liloproject.repository.TimeLogRepository;
import com.lilo.liloproject.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DbInit {
    private TimeLogRepository timeLogRepository;
    private TimesheetRepository timesheetRepository;

    @Autowired
    public DbInit(TimeLogRepository timeLogRepository, TimesheetRepository timesheetRepository) {
        this.timeLogRepository = timeLogRepository;
        this.timesheetRepository = timesheetRepository;
    }

//    @PostConstruct
//    private void postConstruct() {
//        List<TimeLog> aprilALogs = new ArrayList<>();
//        List<TimeLog> mayALogs = new ArrayList<>();
//        List<TimeLog> juneALogs = new ArrayList<>();
//        List<TimeLog> novBLogs = new ArrayList<>();
//
//        Timesheet aprilA = new Timesheet(
//                "13-1773",
//                "approved",
//                ZonedDateTime.parse("2019-04-01T07:00:00.000+08:00[Asia/Manila]"),
//                ZonedDateTime.parse("2019-04-15T17:00:00.000+08:00[Asia/Manila]"),
//                aprilALogs
//        ); //1 falls on Monday, April 9 is a holiday, Absent, night shift
//
//        Timesheet mayA = new Timesheet(
//                "13-1773",
//                "approved",
//                ZonedDateTime.parse("2019-05-01T07:00:00.000+08:00[Asia/Manila]"),
//                ZonedDateTime.parse("2019-05-15T17:00:00.000+08:00[Asia/Manila]"),
//                mayALogs
//        ); //1 falls on Wednesday, May 1 is a holiday, work on weekend, night shift
//
//        Timesheet juneA = new Timesheet(
//                "13-1773",
//                "approved",
//                ZonedDateTime.parse("2019-06-01T07:00:00.000+08:00[Asia/Manila]"),
//                ZonedDateTime.parse("2019-06-15T17:00:00.000+08:00[Asia/Manila]"),
//                juneALogs
//        ); //1 falls on a weekend, June 5 and 12 are holidays, work on holiday, night shift
//
//        Timesheet novB = new Timesheet(
//                "13-1773",
//                "approved",
//                ZonedDateTime.parse("2019-11-16T07:00:00.000+08:00[Asia/Manila]"),
//                ZonedDateTime.parse("2019-11-30T17:00:00.000+08:00[Asia/Manila]"),
//                novBLogs
//        );
//
//        //April 1-5, 8-12, 15
//        for (int i=1; i<=15; i++) {
//            String d;
//
//            if (i<10) {
//                d = "0" + i;
//            } else {
//                d = String.valueOf(i);
//            }
//
//            if (i==3 || i == 9 || i == 6 || i == 7 || i == 13 || i == 14) {
//                //3 absent on a weekday
//                //9 is a holiday
//                //6-7,13-14 are weekends
//            } else if (i==11) {
//                //night shift
//                aprilALogs.add(new TimeLog(
//                        ZonedDateTime.parse("2019-04-" + d + "T14:00:00.000+08:00[Asia/Manila]"),
//                        ZonedDateTime.parse("2019-04-" + d + "T23:00:00.000+08:00[Asia/Manila]"),
//                        false,
//                        "7F Kiosk",
//                        "7F Kiosk",
//                        "13-1773",
//                        false,
//                        false,
//                        "N/A",
//                        null,
//                        aprilA
//                ));
//            } else {
//                aprilALogs.add(new TimeLog(
//                        ZonedDateTime.parse("2019-04-" + d + "T08:00:00.000+08:00[Asia/Manila]"),
//                        ZonedDateTime.parse("2019-04-" + d + "T17:00:00.000+08:00[Asia/Manila]"),
//                        false,
//                        "7F Kiosk",
//                        "7F Kiosk",
//                        "13-1773",
//                        false,
//                        false,
//                        "N/A",
//                        null,
//                        aprilA
//                ));
//            }
//
//        }
//        aprilA.setTimeLogs(aprilALogs);
//        timesheetRepository.save(aprilA);
//
//        //May 2-3, 6-10, 13-15
//        for (int i=1; i<=15; i++) {
//            String d;
//
//            if (i<10) {
//                d = "0" + i;
//            } else {
//                d = String.valueOf(i);
//            }
//
//            if (i==1 || i == 4 || i == 5 || i == 11) {
//                //1 is a holiday
//                //4-5, 11-12 are weekends but working on 12
//            } else if (i==14) {
//                //overtime
//                mayALogs.add(new TimeLog(
//                        ZonedDateTime.parse("2019-05-" + d + "T08:00:00.000+08:00[Asia/Manila]"),
//                        ZonedDateTime.parse("2019-05-" + d + "T19:00:00.000+08:00[Asia/Manila]"),
//                        false,
//                        "7F Kiosk",
//                        "7F Kiosk",
//                        "13-1773",
//                        false,
//                        false,
//                        "N/A",
//                        null,
//                        mayA
//                ));
//            } else {
//                mayALogs.add(new TimeLog(
//                        ZonedDateTime.parse("2019-05-" + d + "T08:00:00.000+08:00[Asia/Manila]"),
//                        ZonedDateTime.parse("2019-05-" + d + "T15:00:00.000+08:00[Asia/Manila]"),
//                        false,
//                        "7F Kiosk",
//                        "7F Kiosk",
//                        "13-1773",
//                        false,
//                        false,
//                        "N/A",
//                        null,
//                        mayA
//                ));
//            }
//
//        }
//        mayA.setTimeLogs(mayALogs);
//        timesheetRepository.save(mayA);
//
//        //Jun 3-7, 10-14
//        for (int i=1; i<=15; i++) {
//            String d;
//
//            if (i<10) {
//                d = "0" + i;
//            } else {
//                d = String.valueOf(i);
//            }
//
//            if (i == 1 || i == 2 || i == 5 || i == 8 || i == 9 || i == 15) {
//                //5 & 12 are holidays, working on 12
//                //1-2, 8-9, 15 are weekends
//            } else if (i==3) {
//                //overtime
//                juneALogs.add(new TimeLog(
//                        ZonedDateTime.parse("2019-06-" + d + "T08:00:00.000+08:00[Asia/Manila]"),
//                        ZonedDateTime.parse("2019-06-" + d + "T18:00:00.000+08:00[Asia/Manila]"),
//                        false,
//                        "7F Kiosk",
//                        "7F Kiosk",
//                        "13-1773",
//                        false,
//                        false,
//                        "N/A",
//                        null,
//                        juneA
//                ));
//            } else {
//                juneALogs.add(new TimeLog(
//                        ZonedDateTime.parse("2019-06-" + d + "T08:00:00.000+08:00[Asia/Manila]"),
//                        ZonedDateTime.parse("2019-06-" + d + "T17:00:00.000+08:00[Asia/Manila]"),
//                        false,
//                        "7F Kiosk",
//                        "7F Kiosk",
//                        "13-1773",
//                        false,
//                        false,
//                        "N/A",
//                        null,
//                        juneA
//                ));
//            }
//
//        }
//        juneA.setTimeLogs(juneALogs);
//        timesheetRepository.save(juneA);
//
//        //Nov 18-22, 25-29
//        for (int i=16; i<=30; i++) {
//
//            if (i == 16 || i == 17 || i == 23 || i == 24 || i == 30) {
//                //16-17, 23-24, 30 are weekends
//                //30 is also a holiday
//            } else {
//                novBLogs.add(new TimeLog(
//                        ZonedDateTime.parse("2019-11-" + i + "T08:00:00.000+08:00[Asia/Manila]"),
//                        ZonedDateTime.parse("2019-11-" + i + "T17:00:00.000+08:00[Asia/Manila]"),
//                        false,
//                        "7F Kiosk",
//                        "7F Kiosk",
//                        "13-1773",
//                        false,
//                        false,
//                        "N/A",
//                        null,
//                        novB
//                ));
//            }
//
//        }
//        novB.setTimeLogs(novBLogs);
////        timesheetRepository.save(novB);
//    }
//
////    @PostConstruct
////    private void postConstruct() {
////        String year, month, day, start, end;
////
////        for (int y=2017; y<=2018; y++) {
////            year = String.valueOf(y);
////            for (int m=1; m<=12; m++) {
////                if (m<10) {
////                    month = "0" + m;
////                } else {
////                    month = String.valueOf(m);
////                }
////                for (int c=1; c<=2; c++) {
////                    int fd, ld;
////
////                    if (c==1) {
////                        fd=1;
////                        ld=15;
////                        start = "0" + fd;
////                    } else {
////                        fd=16;
////                        start = String.valueOf(fd);
////                        if ( m==1 || m==3 || m==5 || m==7 || m==8 || m==10 || m==12) {
////                            ld=31;
////                        } else if ( m==2 ) {
////                            ld=28;
////                        } else {
////                            ld=30;
////                        }
////                    }
////
////                    end = String.valueOf(ld);
////
////                    List<TimeLog> timeLogs = new ArrayList<>();
////
////                    Timesheet timesheet = new Timesheet(
////                            "13-1773",
////                            "approved",
////                            ZonedDateTime.parse(year + "-" + month + "-" + start + "T16:24:11.252+08:00[Asia/Manila]"),
////                            ZonedDateTime.parse(year + "-" + month + "-" + end + "T16:24:11.252+08:00[Asia/Manila]"),
////                            timeLogs
////                    );
////
////                    for (int d=fd; d<=ld; d++) {
////                        if (d<10) {
////                            day = "0" + d;
////                        } else {
////                            day = String.valueOf(d);
////                        }
////
////                        if (m==2 && c==1 && d==15) {
////                            timeLogs.add(new TimeLog(
////                                    ZonedDateTime.parse(year + "-" + month + "-" + day + "T07:24:37.252+08:00[Asia/Manila]"),
////                                    null,
////                                    false,
////                                    "7F Kiosk",
////                                    "7F Kiosk",
////                                    "13-1773",
////                                    false,
////                                    false,
////                                    "N/A",
////                                    null,
////                                    timesheet
////                            ));
////                        } else {
////                            timeLogs.add(new TimeLog(
////                                    ZonedDateTime.parse(year + "-" + month + "-" + day + "T07:24:37.252+08:00[Asia/Manila]"),
////                                    ZonedDateTime.parse(year + "-" + month + "-" + day + "T16:13:27.132+08:00[Asia/Manila]"),
////                                    false,
////                                    "7F Kiosk",
////                                    "7F Kiosk",
////                                    "13-1773",
////                                    false,
////                                    false,
////                                    "N/A",
////                                    null,
////                                    timesheet
////                            ));
////                        }
////                    }
////
////                    timesheet.setTimeLogs(timeLogs);
////                    timesheetRepository.save(timesheet);
////                }
////            }
////        }
////
////    }

//    @PostConstruct //uncomment if you want an existing
    private void postConstruct() {
        List<TimeLog> aprilALogs = new ArrayList<>();
        List<TimeLog> mayALogs = new ArrayList<>();
        List<TimeLog> juneALogs = new ArrayList<>();
        List<TimeLog> julyALogs = new ArrayList<>();

        Timesheet aprilA = new Timesheet(
                "18-4559",
                "approved",
                ZonedDateTime.parse("2019-04-01T07:00:00.000+08:00[Asia/Manila]"),
                ZonedDateTime.parse("2019-04-15T17:00:00.000+08:00[Asia/Manila]"),
                aprilALogs
        ); //1 falls on Monday, April 9 is a holiday, Absent, night shift

        Timesheet mayA = new Timesheet(
                "18-4559",
                "approved",
                ZonedDateTime.parse("2019-05-01T07:00:00.000+08:00[Asia/Manila]"),
                ZonedDateTime.parse("2019-05-15T17:00:00.000+08:00[Asia/Manila]"),
                mayALogs
        ); //1 falls on Wednesday, May 1 is a holiday, work on weekend, night shift

        Timesheet juneA = new Timesheet(
                "18-4559",
                "approved",
                ZonedDateTime.parse("2019-06-01T07:00:00.000+08:00[Asia/Manila]"),
                ZonedDateTime.parse("2019-06-15T17:00:00.000+08:00[Asia/Manila]"),
                juneALogs
        ); //1 falls on a weekend, June 5 and 12 are holidays, work on holiday, night shift

        Timesheet julyA = new Timesheet(
                "18-4559",
                "approved",
                ZonedDateTime.parse("2019-07-01T07:00:00.000+08:00[Asia/Manila]"),
                ZonedDateTime.parse("2019-07-15T17:00:00.000+08:00[Asia/Manila]"),
                julyALogs
        );

        //April 1-5, 8-12, 15
        for (int i = 1; i <= 15; i++) {
            String d;

            if (i < 10) {
                d = "0" + i;
            } else {
                d = String.valueOf(i);
            }
            if (i == 3 || i == 9 || i == 6 || i == 7 || i == 13 || i == 14) {
                //3 absent on a weekday
                //9 is a holiday
                //6-7,13-14 are weekends
            } else if (i == 11) {
                //night shift
                aprilALogs.add(new TimeLog(
                        ZonedDateTime.parse("2019-04-" + d + "T14:00:00.000+08:00[Asia/Manila]"),
                        ZonedDateTime.parse("2019-04-" + d + "T23:00:00.000+08:00[Asia/Manila]"),
                        false,
                        "7F Kiosk",
                        "7F Kiosk",
                        "18-4559",
                        false,
                        false,
                        "N/A",
                        null,
                        aprilA
                ));
            } else {
                aprilALogs.add(new TimeLog(
                        ZonedDateTime.parse("2019-04-" + d + "T08:00:00.000+08:00[Asia/Manila]"),
                        ZonedDateTime.parse("2019-04-" + d + "T17:00:00.000+08:00[Asia/Manila]"),
                        false,
                        "7F Kiosk",
                        "7F Kiosk",
                        "18-4559",
                        false,
                        false,
                        "N/A",
                        null,
                        aprilA
                ));
            }

        }
        aprilA.setTimeLogs(aprilALogs);
        timesheetRepository.save(aprilA);

        //May 2-3, 6-10, 13-15
        for (int i = 1; i <= 15; i++) {
            String d;

            if (i < 10) {
                d = "0" + i;
            } else {
                d = String.valueOf(i);
            }

            if (i == 1 || i == 4 || i == 5 || i == 11) {
                //1 is a holiday
                //4-5, 11-12 are weekends but working on 12
            } else if (i == 14) {
                //overtime
                mayALogs.add(new TimeLog(
                        ZonedDateTime.parse("2019-05-" + d + "T08:00:00.000+08:00[Asia/Manila]"),
                        ZonedDateTime.parse("2019-05-" + d + "T19:00:00.000+08:00[Asia/Manila]"),
                        false,
                        "7F Kiosk",
                        "7F Kiosk",
                        "18-4559",
                        false,
                        false,
                        "N/A",
                        null,
                        mayA
                ));
            } else {
                mayALogs.add(new TimeLog(
                        ZonedDateTime.parse("2019-05-" + d + "T08:00:00.000+08:00[Asia/Manila]"),
                        ZonedDateTime.parse("2019-05-" + d + "T15:00:00.000+08:00[Asia/Manila]"),
                        false,
                        "7F Kiosk",
                        "7F Kiosk",
                        "18-4559",
                        false,
                        false,
                        "N/A",
                        null,
                        mayA
                ));
            }

        }
        mayA.setTimeLogs(mayALogs);
        timesheetRepository.save(mayA);

        //Jun 3-7, 10-14
        for (int i = 1; i <= 15; i++) {
            String d;

            if (i < 10) {
                d = "0" + i;
            } else {
                d = String.valueOf(i);
            }
            if (i == 1 || i == 2 || i == 5 || i == 8 || i == 9 ) {
                //5 & 12 are holidays, working on 12
                //1-2, 8-9, 15 are weekends
            } else if (i == 3) {
                //overtime
                juneALogs.add(new TimeLog(
                        ZonedDateTime.parse("2019-06-" + d + "T08:00:00.000+08:00[Asia/Manila]"),
                        ZonedDateTime.parse("2019-06-" + d + "T18:00:00.000+08:00[Asia/Manila]"),
                        false,
                        "7F Kiosk",
                        "7F Kiosk",
                        "18-4559",
                        false,
                        false,
                        "N/A",
                        null,
                        juneA
                ));
            } else {
                juneALogs.add(new TimeLog(
                        ZonedDateTime.parse("2019-06-" + d + "T08:00:00.000+08:00[Asia/Manila]"),
                        ZonedDateTime.parse("2019-06-" + d + "T17:00:00.000+08:00[Asia/Manila]"),
                        false,
                        "7F Kiosk",
                        "7F Kiosk",
                        "18-4559",
                        false,
                        false,
                        "N/A",
                        null,
                        juneA
                ));
            }

        }
        juneA.setTimeLogs(juneALogs);
        timesheetRepository.save(juneA);

        //Jul 1,3-5,8-12,15
        for (int i = 1; i <= 15; i++) {
            String d;

            if (i < 10) {
                d = "0" + i;
            } else {
                d = String.valueOf(i);
            }

            if (i == 2 || i == 6 || i == 7 || i == 13 || i == 14) {
                //2 is a holiday
                //6-7, 13-14 are weekends
            } else if (i == 3 || i == 9 || i == 15) {
                julyALogs.add(new TimeLog(
                        ZonedDateTime.parse("2019-07-" + d + "T08:00:00.000+08:00[Asia/Manila]"),
                        ZonedDateTime.parse("2019-07-" + d + "T12:00:00.000+08:00[Asia/Manila]"),
                        false,
                        "7F Kiosk",
                        "7F Kiosk",
                        "18-4559",
                        false,
                        false,
                        "N/A",
                        null,
                        julyA
                ));
                julyALogs.add(new TimeLog(
                        ZonedDateTime.parse("2019-07-" + d + "T13:00:00.000+08:00[Asia/Manila]"),
                        ZonedDateTime.parse("2019-07-" + d + "T18:00:00.000+08:00[Asia/Manila]"),
                        false,
                        "7F Kiosk",
                        "7F Kiosk",
                        "18-4559",
                        false,
                        false,
                        "N/A",
                        null,
                        julyA
                ));
            } else if (i==1|| i == 10 || i == 15) {
                julyALogs.add(new TimeLog(
                        ZonedDateTime.parse("2019-07-" + d + "T08:00:00.000+08:00[Asia/Manila]"),
                        ZonedDateTime.parse("2019-07-" + d + "T10:30:00.000+08:00[Asia/Manila]"),
                        false,
                        "7F Kiosk",
                        "7F Kiosk",
                        "18-4559",
                        false,
                        false,
                        "N/A",
                        null,
                        julyA
                ));
                julyALogs.add(new TimeLog(
                        ZonedDateTime.parse("2019-07-" + d + "T11:00:00.000+08:00[Asia/Manila]"),
                        ZonedDateTime.parse("2019-07-" + d + "T14:30:00.000+08:00[Asia/Manila]"),
                        false,
                        "7F Kiosk",
                        "7F Kiosk",
                        "18-4559",
                        false,
                        false,
                        "N/A",
                        null,
                        julyA
                ));
                julyALogs.add(new TimeLog(
                        ZonedDateTime.parse("2019-07-" + d + "T14:45:00.000+08:00[Asia/Manila]"),
                        ZonedDateTime.parse("2019-07-" + d + "T18:03:00.000+08:00[Asia/Manila]"),
                        false,
                        "7F Kiosk",
                        "7F Kiosk",
                        "18-4559",
                        false,
                        false,
                        "N/A",
                        null,
                        julyA
                ));
            } else {
                julyALogs.add(new TimeLog(
                        ZonedDateTime.parse("2019-07-" + d + "T08:00:00.000+08:00[Asia/Manila]"),
                        ZonedDateTime.parse("2019-07-" + d + "T17:00:00.000+08:00[Asia/Manila]"),
                        false,
                        "7F Kiosk",
                        "7F Kiosk",
                        "18-4559",
                        false,
                        false,
                        "N/A",
                        null,
                        julyA
                ));
            }

        }
        julyA.setTimeLogs(julyALogs);
        timesheetRepository.save(julyA);

    }


}


