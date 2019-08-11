package com.lilo.liloproject.service;

import com.lilo.liloproject.dto.EmployeeDTO;
import com.lilo.liloproject.model.Timesheet;

import java.io.ByteArrayInputStream;

public interface FileWriterService{

    ByteArrayInputStream generateTimesheetSpreadsheet (EmployeeDTO employee, Timesheet timesheet, String timezone);
}
