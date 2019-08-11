package com.lilo.liloproject.service;

import com.lilo.liloproject.dto.CutoffPeriodDTO;
import com.lilo.liloproject.dto.EmployeeDTO;
import com.lilo.liloproject.dto.ViewTimesheetDTO;
import com.lilo.liloproject.model.Timesheet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface TimesheetService {

    List<Timesheet> findAllByEmployeeID(String employeeID);
    List<Timesheet> findAllByEmployeeIDOrderByStartDateDesc(String employeeID);
    List<Timesheet> findAllByEmployeeIDAndStatus(String employeeID, String status);
    Timesheet findByEmployeeIDAndTimesheetId(String employeeID, Long timesheetId);
    ViewTimesheetDTO findLogsByEmployeeIDAndTimesheetId(String employeeID, Long timesheetId, String timezone);
    Timesheet findByLogDate(String employeeID, LocalDate timeLogDate);
    Timesheet findLatest(String employeeID);
    Timesheet save(Timesheet timesheet);
    List<Timesheet> findAll();
    List<CutoffPeriodDTO> getTimesheetListStartDateEndDate(String employeeID, String timezone);
    ByteArrayInputStream downloadTimesheet(EmployeeDTO employee, Long timesheetID, String timezone)throws IOException;

}
