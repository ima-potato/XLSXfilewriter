//package com.lilo.liloproject.controller;
//
//import com.lilo.liloproject.dto.TimeLogKioskDTO;
//import com.lilo.liloproject.dto.TimeLogResponseDTO;
//import com.lilo.liloproject.dto.TimeLogTransactionDTO;
//import com.lilo.liloproject.model.Login;
//import com.lilo.liloproject.model.TimeLog;
//import com.lilo.liloproject.model.Timesheet;
//import com.lilo.liloproject.model.User;
//import com.lilo.liloproject.service.TimesheetService;
//import com.lilo.liloproject.util.EncryptionUtils;
//import org.apache.commons.lang3.time.DurationFormatUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.time.*;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class TimeLogController extends APIController {
//
//    private static final Logger log = LoggerFactory.getLogger(TimeLogController.class);
//
//    private TimeLogService timeLogService;
//    private TimesheetService timesheetService;
//
//    @Autowired
//    public TimeLogController(TimeLogService timeLogService, TimesheetService timesheetService) {
//        this.timeLogService = timeLogService;
//        this.timesheetService = timesheetService;
//    }
//
//    @GetMapping("timelog")
//    public ResponseEntity<List<TimeLog>> getAll() {
//
//        log.info("Entering getAll TimeLogs");
//
//        return new ResponseEntity<>(timeLogService.findAll(), HttpStatus.OK);
//    }
//
//    @GetMapping("timelog/latest")
//    @PreAuthorize("LILO_USER")
//    public ResponseEntity<TimeLog> getLatest(Authentication authentication) {
//        log.info("Entering getLatest (1) TimeLog of user: [request: {}]", authentication);
//
//        User user = (User) authentication.getPrincipal();
//
//        TimeLog retrievedLatestTimeLog = timeLogService.findLatestByEmployeeID(user.getLoginId());
//
//
//        log.info("Exiting getLatest: [response: {}]", retrievedLatestTimeLog);
//        if(retrievedLatestTimeLog == null) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        } else {
//            return new ResponseEntity<>(retrievedLatestTimeLog, HttpStatus.OK);
//        }
//    }
//
//
//    @PostMapping("timelog/log")
//    public ResponseEntity<TimeLogResponseDTO> recordLog(Authentication authentication, @RequestBody TimeLogTransactionDTO timeLogTransactionDTO) {
//
//        //transform timelogdto into timelog object then set timesheet to latest/created
//
//        /**
//         *  Proceed to the generic method for recording logs
//         */
//
//        TimeLog timeLog = recordLog(timeLogTransactionDTO);
//
//        log.info("[@POST recordLog] timeLog remarks " + timeLog.getRemarks());
//
//
//        /**
//         *  Generate response object/entity to be passed to the front-end/client
//         */
//
//        TimeLogResponseDTO timeLogResponseDTO = timeLogResponseGenerator(timeLog);
//
//        log.info("[@POST recordLog] timeLogResponseDTO message " + timeLogResponseDTO.getMessage());
//
//        System.out.println("This is the timelog: " + timeLog);
//        System.out.println("This is the DTO: " + timeLogTransactionDTO.getEmployeeID() + " " + timeLogTransactionDTO.getTime());
//        System.out.println("This is the response: " + timeLogResponseDTO.getDuration());
//
//
//        if(timeLog == null) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//
//        return new ResponseEntity<>(timeLogResponseDTO, HttpStatus.OK);
////        return new ResponseEntity<>(null, HttpStatus.OK);
//    }
//
////    private static final String INVALID_LOGIN_MESSAGE = "Invalid login account. Please contact your administrator.";
////    private static final String GENERIC_ERROR_MESSAGE = "An error has occured. Please contact your administrator.";
//
//    //TO DO: Change
//
//
//
//    /**
//     * Author: Tyler Venzon
//     *
//     *  API Endpoint called when a user times in or out from the terminal.
//     *  (see src/app/component/login/login.component.ts)
//     *
//     *
//     **/
//    @PostMapping("timelog/log-kiosk")
//    @PreAuthorize("hasRole('LILO_USER')")
//    public ResponseEntity<TimeLogResponseDTO> loginFromKiosk (@RequestBody TimeLogKioskDTO timeLogKioskDTO) {
//        Boolean matches = false;
//
//
//        /**
//         * Get login credentials first from Salesforce using input employee ID
//         */
//
//        log.info("[LoginController] Entered loginFromKiosk {}", timeLogKioskDTO);
//
//        Login login = loginService.getLoginCredentialsByIDNumber(timeLogKioskDTO.getEmployeeID());
//
//        log.info("[LoginController] employeeID from form<" + timeLogKioskDTO.getEmployeeID() + ">END");
//        log.info("[LoginController] password from form<" + timeLogKioskDTO.getPassword() + ">END");
//        log.info("[LoginController] password<" + login.getPassword() + ">END");
//        log.info("[LoginController] resource<" + login.getResource() + ">END");
//
//        try {
//
//            /**
//             * Check if the hashed password matches the input when hashed.
//             */
//
//            String hashString = EncryptionUtils.hashString(timeLogKioskDTO.getPassword(), login.getSalt());
//            if(hashString.equals(login.getPassword())) {
//
//                TimeLogTransactionDTO timeLogTransactionDTO = new TimeLogTransactionDTO();
//
//                timeLogTransactionDTO.setTime(timeLogKioskDTO.getTime());
//                timeLogTransactionDTO.setEmployeeID(timeLogKioskDTO.getEmployeeID());
//                timeLogTransactionDTO.setHoliday(timeLogKioskDTO.getHoliday());
//                timeLogTransactionDTO.setLocation(timeLogKioskDTO.getLocation());
//                timeLogTransactionDTO.setRemarks(timeLogKioskDTO.getRemarks());
//                timeLogTransactionDTO.setRemote(timeLogKioskDTO.getRemote());
//
//                /**
//                 *  Proceed to the generic method for recording logs
//                 */
//
//                TimeLog timeLog = recordLog(timeLogTransactionDTO);
//
//                if(timeLog == null) {
//                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//                }
//                TimeLogResponseDTO timeLogResponseDTO = timeLogResponseGenerator(timeLog);
//                return new ResponseEntity<>(timeLogResponseDTO, HttpStatus.OK);
//            } else {
//                TimeLogResponseDTO timeLogResponseDTO = timeLogResponseGenerator(null);
//                return new ResponseEntity<>(timeLogResponseDTO, HttpStatus.BAD_REQUEST);
//            }
//
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeyException e) {
//            e.printStackTrace();
//        }
//
//
//
////        User user = (User) authentication.getPrincipal();
//
//        log.info("[LoginController]: Exiting loginFromKiosk with: {}", login);
//        return null;
//    }
//
//
//    public TimeLog recordLog(TimeLogTransactionDTO timeLogTransactionDTO) {
//
//        String zonedDateTimePattern = "dd.MM.yyyy HH:mm:ss z";
//
//        System.out.println("RAW TIME: " + timeLogTransactionDTO.getTime());
//
//        ZonedDateTime parsedLogTime = ZonedDateTime.parse(timeLogTransactionDTO.getTime());
//
//        ZonedDateTime utcLogTime = parsedLogTime.withZoneSameInstant(ZoneId.of("Etc/UTC"));
//
//        //Time to be recorded as time in/time out
//        //Time stored in the database is UTC
//        ZonedDateTime serverTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("Etc/UTC"));
//
//        log.info("[recordLog] recorded utc converted frontend time "+ utcLogTime);
//        log.info("[recordLog] recorded server log time "+ serverTime);
//
//        DateTimeFormatter zonedDateTimeFormatter = DateTimeFormatter.ofPattern(zonedDateTimePattern);
//
//        System.out.println("zoneddatetimenow: " + zonedDateTimeFormatter.format(ZonedDateTime.now()));
//
//
//        log.info("Entering recordLog: [request: {}]", timeLogTransactionDTO);
//
//        TimeLog latestTimeLog = timeLogService.findLatestByEmployeeID(timeLogTransactionDTO.getEmployeeID());
//
//        log.info("[recordLog] latestTimeLog: [latestTimeLog: {}]", latestTimeLog);
//
//        Timesheet latestTimesheet = timesheetService.findLatest(timeLogTransactionDTO.getEmployeeID());
//
//        log.info("[recordLog] latestTimesheet: [latestTimesheet: {}] ", latestTimesheet);
//
//
//        //For the current timesheet, if there exists a (most recent) log/prior log and it has no time out, then
//        //the transaction will be a timeout;
//        //otherwise, it is recorded as a time in.
//
//        if (latestTimeLog != null && latestTimeLog.getTimeOut() == null) {
//            log.info("[recordLog] latestTimeLog has no TimeOut");
//
//
//            if(latestTimeLog.isRemote() == timeLogTransactionDTO.getRemote()){
//
//
//                log.info("[recordLog] timeout in Client" + zonedDateTimeFormatter.format(parsedLogTime));
//                latestTimeLog.setTimeOut(serverTime);
//                latestTimeLog.setTimeOutLocation(timeLogTransactionDTO.getLocation());
//                TimeLog updatedTimeLog = timeLogService.save(latestTimeLog);
//                log.info("[recordLog] latestTimeLog TimeOut recorded");
//                log.info("exiting recordLog: [response:{}]", updatedTimeLog);
//
//                return updatedTimeLog;
//            } else {
//
//                log.info("[recordLog] Mismatch of latestTimeLog and current transaction");
//
//                latestTimeLog.setRemarks("REMOTE_MISMATCH");
//                latestTimeLog.setTimeOut(null);
//                return latestTimeLog;
//            }
//        }
//
//
//        //If the user has no timesheet (first log) or
//        //their latest timesheet is for the past cutoff
//        //(end date of cutoff is before the transaction),
//        //then create a new timesheet for the current cutoff
//
//        if(latestTimesheet != null)
//        log.info("[recordLog] is serverTime: " + serverTime + " before latestTimesheet.getEndDateTime ");
//
//        if(latestTimesheet == null || latestTimesheet.getEndDateTime().isBefore(serverTime)) {
//
//
//            log.info("[recordLog] creating timesheet for current cutoff");
//
////            LocalDate currentDate = LocalDate.now();
//
//            log.info("[recordLog] user offset [user offset {}] ", parsedLogTime.getOffset().toString() );
//
//            ZoneId userZoneID = parsedLogTime.getZone();
//            ZonedDateTime currentDateUser = ZonedDateTime.now(userZoneID);
//
//            int startDayOfCutoff;
//            int endDayOfCutoff;
//
//            if(currentDateUser.getDayOfMonth() < 16) {
//                startDayOfCutoff = 1;
//                endDayOfCutoff = 15;
//            } else {
//                startDayOfCutoff = 16;
//                endDayOfCutoff = currentDateUser.toLocalDate().lengthOfMonth();
//            }
//
//            LocalDate startDate = LocalDate.of(currentDateUser.getYear(), currentDateUser.getMonthValue(), startDayOfCutoff);
//            LocalDate endDate = LocalDate.of(currentDateUser.getYear(), currentDateUser.getMonthValue(), endDayOfCutoff);
//
//            //T00:00:00 of the start cutoff date for the client timezone
//            ZonedDateTime zonedStartDate = startDate.atStartOfDay(userZoneID);
//
//            //T23:59:59 of the end cutoff date for the client timezone
//            ZonedDateTime zonedEndDate = LocalTime.MAX.atDate(endDate).atZone(userZoneID);
//            log.info("[recordLog] timesheet startdate in Client: [zonedStartDate {}]", zonedStartDate);
//            log.info("[recordLog] timesheet enddate in Client: [zonedEndDate {}]", zonedEndDate);
//
//
//            //converted cutoff dates into UTC +00:00
//            //all dates in the db art in UTC +0;
//            ZonedDateTime utcStartDate = zonedStartDate.withZoneSameInstant(ZoneId.of("UTC"));
//            ZonedDateTime utcEndDate = zonedEndDate.withZoneSameInstant(ZoneId.of("UTC"));
//
//            log.info("[recordLog] timesheet startdate in UTC: [utcStartDate {}]", utcStartDate);
//            log.info("[recordLog] timesheet enddate in UTC: [utcEndDate {}]", utcEndDate);
//
//            latestTimesheet = new Timesheet();
//            latestTimesheet.setStatus("Pending");
//            latestTimesheet.setEmployeeID(timeLogTransactionDTO.getEmployeeID());
//            latestTimesheet.setStartDateTime(zonedStartDate);
//            latestTimesheet.setEndDateTime(zonedEndDate);
//            List<TimeLog> logs = new ArrayList<>();
//            latestTimesheet.setTimeLogs(logs);
//        }
//        System.out.println("latesttimesheet enddatetime: " + zonedDateTimeFormatter.format(latestTimesheet.getEndDateTime()));
//
//
//        log.info("[recordLog] time in " + parsedLogTime.getZone().normalized() + ": [timeIn {}]", parsedLogTime);
//        log.info("[recordLog] time in " + utcLogTime.getZone().normalized() + ": [timeIn {}]", utcLogTime);
//
////        ZonedDateTime utcTimeIn = parsedLogTime.withZoneSameInstant(ZoneId.of("UTC"));
////        System.out.println("converted: " + utcTimeIn);
////
////        log.info("[recordLog] creating log with TimeIn: [utcTimeIn {}]", utcTimeIn);
////
//
//        //create a new timelog with time in
//        TimeLog timeLog = new TimeLog();
//        timeLog.setEdited(false);
//        timeLog.setEmployeeID(timeLogTransactionDTO.getEmployeeID());
////        timeLog.setTimeIn(parsedLogTime);
//        timeLog.setTimeIn(serverTime);
//        timeLog.setTimeOut(null);
//        timeLog.setTimeInLocation(timeLogTransactionDTO.getLocation());
//        timeLog.setTimeOutLocation(null);
//        timeLog.setRemote(timeLogTransactionDTO.getRemote());
//        timeLog.setReferenceTimeLog(null);
//        timeLog.setTimesheet(latestTimesheet);
//        timeLog.setHoliday(timeLogTransactionDTO.getHoliday());
//        timeLog.setRemarks(timeLogTransactionDTO.getRemarks());
//
//        List<TimeLog> currentTimeLogs = latestTimesheet.getTimeLogs();
//        currentTimeLogs.add(timeLog);
//        latestTimesheet.setTimeLogs(currentTimeLogs);
//
//        Timesheet updatedTimesheet = timesheetService.save(latestTimesheet);
//        log.info("[recordLog] updated current timesheet: response[{}]", updatedTimesheet);
//
//        Integer numOfLogs = updatedTimesheet.getTimeLogs().size();
//        latestTimeLog = updatedTimesheet.getTimeLogs().get(numOfLogs-1);
//
//        log.info("Exiting recordLog: response[{}]", latestTimeLog);
//
//        return latestTimeLog;
//    }
//
//    private TimeLogResponseDTO timeLogResponseGenerator(TimeLog timeLog) {
//
//        TimeLogResponseDTO timeLogResponseDTO = new TimeLogResponseDTO();
//
//        if(timeLog == null) {
//            timeLogResponseDTO.setMessage("Invalid credentials");
//            return timeLogResponseDTO;
//        }
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy - hh:mm:ss");
//
//        if(timeLog.getRemarks() != null) {
//            timeLogResponseDTO.setMessage(timeLog.getRemarks());
//        }
//
//        if(timeLog.getTimeOut() != null) {
//            log.info("Duration: "+ Duration.between(timeLog.getTimeIn(), timeLog.getTimeOut()).toString());
//
//            Long totalSeconds = Duration.between(timeLog.getTimeIn(), timeLog.getTimeOut()).toMillis();
//
//            timeLogResponseDTO.setTimeOut(timeLog.getTimeOut());
//            timeLogResponseDTO.setStrTimeOut(timeLog.getTimeOut().toString());
//            timeLogResponseDTO.setDuration(DurationFormatUtils.formatDurationHMS(totalSeconds));
//            timeLogResponseDTO.setTimeOutLocation(timeLog.getTimeOutLocation());
//            timeLogResponseDTO.setLdtTimeOut(timeLog.getTimeOut().toLocalDateTime().toString());
//            timeLogResponseDTO.setZone(timeLog.getTimeOut().getZone().toString());
//
//
//
//            log.info(timeLog.getTimeOut().toInstant() + " TO to Instant");
//            log.info(timeLog.getTimeOut().getZone() + " TO the zone");
//            log.info(timeLog.getTimeOut().toLocalDateTime() + " TO LDT");
//            log.info(timeLog.getTimeOut().toLocalDateTime().toString() + " TO LDT Str");
//            log.info(timeLog.getTimeOut().toString() + " TO ZDT Str");
//
//        } else {
//            timeLogResponseDTO.setTimeIn(timeLog.getTimeIn());
//            timeLogResponseDTO.setStrTimeIn(timeLog.getTimeIn().toString());
//            timeLogResponseDTO.setTimeInLocation(timeLog.getTimeInLocation());
//            timeLogResponseDTO.setLdtTimeIn(timeLog.getTimeIn().toLocalDateTime().toString());
//            timeLogResponseDTO.setZone(timeLog.getTimeIn().getZone().toString());
//
//            log.info(timeLog.getTimeIn().toInstant() + " TI to Instant");
//            log.info(timeLog.getTimeIn().getZone() + " TI the zone");
//            log.info(timeLog.getTimeIn().toLocalDateTime() + " TI LDT");
//            log.info(timeLog.getTimeIn().toLocalDateTime().toString() + " TI LDT Str");
//            log.info(timeLog.getTimeIn().toString() + " TI ZDT Str");
//        }
//
//        log.info("[recordLog] remarks: " + timeLog.getRemarks());
//
//        timeLogResponseDTO.setEmployeeID(timeLog.getEmployeeID());
//
//        return timeLogResponseDTO;
//    }
//
//
//
//
//}
