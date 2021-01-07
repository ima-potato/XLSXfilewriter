/**
 * Author: Aviv Guese, Nicole Es
 *
 *  API Endpoint called to retrieve timesheet lists and timesheets. This controller is also
 *  used to download timesheets
 *  (see src/app/component/login/login.component.ts)
 *
 *
 **/
package com.lilo.liloproject.controller;

import com.lilo.liloproject.dto.*;
import com.lilo.liloproject.model.*;
import com.lilo.liloproject.service.TimesheetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController

public class TimesheetController extends APIController {

    private static final Logger log = LoggerFactory.getLogger(TimesheetController.class);

    private TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    //for all timesheets
    @GetMapping("/timesheet/get/all")
    public ResponseEntity<List<Timesheet>> getAll() {

        log.info("Entering getAll ");

        return new ResponseEntity<>(timesheetService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/timesheet")
    public ResponseEntity<Timesheet> saveTimeSheet(@RequestBody TimesheetDTO timesheetDTO) {

        Timesheet timesheet = new Timesheet();

        timesheet.setEmployeeID(timesheetDTO.getEmployeeID());
        timesheet.setStartDateTime(timesheetDTO.getStartDateTime());
        timesheet.setEndDateTime(timesheetDTO.getEndDateTime());
        timesheet.setStatus(timesheetDTO.getStatus());
        timesheet.setTimeLogs(timesheetDTO.getTimeLogs());

        return new ResponseEntity<>(timesheetService.save(timesheet), HttpStatus.OK);
    }

    //get specific timesheet
    @GetMapping("/timesheet/get-selected")
    public ResponseEntity<ViewTimesheetDTO> getTimesheet(Authentication authentication,
                                                         @RequestParam("timesheetId") Long timesheetId,
                                                         @RequestParam("timezone") String timezone) {

        User user = (User) authentication.getPrincipal();
        String employeeId = user.getLoginId();
        log.info("Entering getTimesheet ");
        log.info(employeeId);
        log.info("timesheetID: " + timesheetId);
        log.info("timezone: " + timezone);
        System.out.println(employeeId);
        System.out.println("timesheetID: " + timesheetId);
        return new ResponseEntity<>(timesheetService.findLogsByEmployeeIDAndTimesheetId(employeeId, timesheetId, timezone), HttpStatus.OK);
    }
    //get all timesheet for that specific user

    //get all timesheet List limit is 2 years
    @GetMapping("/timesheet/list")
    public ResponseEntity<List<CutoffPeriodDTO>> getTimesheetList(Authentication authentication, @RequestParam("timezone") String timezone) {
        User user = (User) authentication.getPrincipal();
        String employeeID = user.getLoginId();
        log.info("timezone: " + timezone);
        return new ResponseEntity<>(timesheetService.getTimesheetListStartDateEndDate(employeeID, timezone), HttpStatus.OK);
    }

    //change this on deployment
    @GetMapping("/timesheet/download")
    public ResponseEntity<InputStreamResource> downloadUserTimesheet(@RequestParam("timesheetId") Long timesheetId,
                                                                     Authentication authentication,
                                                                     @RequestParam("timezone") String timezone) throws IOException {



        //use this to access ANY employee id, this passes the desired employee's number as a param
//    public ResponseEntity<InputStreamResource> downloadUserTimesheet(@RequestParam("timesheetId") Long timesheetId,
//                                                                     Authentication authentication,
//                                                                     @RequestParam("timezone") String timezone,
//                                                                     @RequestParam("employeeId") String empNumber) throws IOException {
//        //employeeDTO from authentication
        //get your own resource for now
        log.info("ResourceController: Entered getting Employee details");

        User user = (User) authentication.getPrincipal();
        UserDetails userDetails = userDetailsService.getUserDetails(user.getLoginId());
//        Resource resource = resourceService.getResource(user.getId());



        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName(resource.getFirstName());
        employeeDTO.setLastName(resource.getLastName());
        //change to this on deployment to get the timesheet of the logged in user
        employeeDTO.setEmployeeID(resource.getEmployeeIdNumber());
        //change to this to download ANY timesheet
//        employeeDTO.setEmployeeID(empNumber);
        employeeDTO.setDepartment(userDetails.getDepartment());
        employeeDTO.setJobTitle(userDetails.getJobTitle().getName());
        employeeDTO.setImmediateSuperVisor(resource.getImmediateManagerName());
        //set department
        if (StringUtils.isEmpty(employeeDTO.getEmployeeID().trim())) {
            log.error("Cannot process request");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot process request");
        }

        try {
            log.info("Getting employee with employee number " + employeeDTO.getEmployeeID());

//            ByteArrayInputStream in = timesheetService.downloadTimesheet(employeeDTO, timesheetId, timezone);
            ByteArrayInputStream in = timesheetService.downloadTimesheet(employeeDTO, timesheetId, timezone);
            HttpHeaders headers = new HttpHeaders();
            String timesheetName = employeeDTO.getDepartment()+ "_" +
                    employeeDTO.getLastName() + "_" + employeeDTO.getFirstName().replaceAll(" ","_")
                    + "_" + employeeDTO.getEmployeeID() + ".xlsx";

            StringBuilder fileName = new StringBuilder();
            fileName.append("attachment; filename=").append(timesheetName);
            headers.add("Content-Disposition", fileName.toString());
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .body(new InputStreamResource(in));

        } catch (UnsupportedOperationException e) {
            log.error("No employee found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employee found");
        }
    }


}
