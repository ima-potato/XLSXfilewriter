package com.lilo.liloproject.service;

import com.lilo.liloproject.dto.CutoffPeriodDTO;
import com.lilo.liloproject.dto.EmployeeDTO;
import com.lilo.liloproject.dto.ViewTimesheetDTO;
import com.lilo.liloproject.helper.TimeLogConverter;
import com.lilo.liloproject.model.Timesheet;
import com.lilo.liloproject.repository.TimesheetRepository;
import com.lilo.liloproject.util.ExcelSpreadSheetUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimesheetServiceImpl implements TimesheetService {

    private TimesheetRepository timesheetRepository;
    private FileWriterService fileWriterService;
    private ExcelSpreadSheetUtils generateTimesheetExcelFile;
    private TimeLogConverter timeLogConverter;
    private static final Logger log = LoggerFactory.getLogger(TimesheetServiceImpl.class);

    @Autowired
    public TimesheetServiceImpl(TimesheetRepository timesheetRepository, TimeLogConverter timeLogConverter, FileWriterServiceImpl fileWriterService) {
        this.timesheetRepository = timesheetRepository;
        this.fileWriterService = fileWriterService;
        this.timeLogConverter = timeLogConverter;
    }

    @Override
    public List<Timesheet> findAllByEmployeeID(String employeeID) {
        return timesheetRepository.findAllByEmployeeID(employeeID);
    }

    @Override //get All
    public List<Timesheet> findAllByEmployeeIDOrderByStartDateDesc(String employeeID) {
        return timesheetRepository.findAllByEmployeeIDOrderByStartDateTimeDesc(employeeID);
    }

    @Override
    public List<Timesheet> findAllByEmployeeIDAndStatus(String employeeID, String status) {
        return timesheetRepository.findAllByEmployeeIDAndStatus(employeeID, status);
    }

    @Override
    public Timesheet save(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    @Override //get timesheetz
    public Timesheet findByEmployeeIDAndTimesheetId(String employeeID, Long timsheetId) {

        return timesheetRepository.findByEmployeeIDAndId(employeeID, timsheetId);
    }

    @Override
    public ViewTimesheetDTO findLogsByEmployeeIDAndTimesheetId(String employeeId, Long timesheetId, String timezone) {
        return timeLogConverter.ConvertToTimeLogDTO(timesheetRepository.findByEmployeeIDAndId(employeeId, timesheetId).getTimeLogs(), timezone);
    }

    @Override
    public Timesheet findByLogDate(String employeeID, LocalDate timeLogDate) {

        LocalDate timesheetStartDate;

        if (timeLogDate.getDayOfMonth() < 16) {
            //first cutoff
            timesheetStartDate = LocalDate.of(timeLogDate.getYear(), timeLogDate.getMonth(), 1);
        } else {
            //first cutoff
            timesheetStartDate = LocalDate.of(timeLogDate.getYear(), timeLogDate.getMonth(), 16);
        }

        return timesheetRepository.findByEmployeeIDAndStartDateTime(employeeID, timesheetStartDate.atStartOfDay());
    }

    @Override
    public Timesheet findLatest(String employeeID) {
        return timesheetRepository.findFirstByEmployeeIDOrderByStartDateTimeDesc(employeeID);
    }

    public List<Timesheet> findAll() {
        return timesheetRepository.findAll();
    }

    public List<Timesheet> getTimesheetListWithinTwoYearLimit(String employeeID) {

        return timesheetRepository.findFirst48ByEmployeeIDOrderByStartDateTimeDesc(employeeID);
    }
    //List of timesheet start date

    public List<CutoffPeriodDTO> getTimesheetListStartDateEndDate(String employeeID, String timezone){

        List<CutoffPeriodDTO> cutoffPeriods = new ArrayList<>();
        for(Timesheet timesheet: timesheetRepository.findFirst48ByEmployeeIDOrderByStartDateTimeDesc(employeeID)){
            CutoffPeriodDTO cutoffPeriodDTO = new CutoffPeriodDTO();
            StringBuilder cutoffPeriod = new StringBuilder();

            timesheet.setStartDateTime(timesheet.getStartDateTime().withZoneSameInstant(ZoneId.of(timezone)));
            timesheet.setEndDateTime(timesheet.getEndDateTime().withZoneSameInstant(ZoneId.of(timezone)));

            cutoffPeriodDTO.setId(timesheet.getId());
            cutoffPeriod.append(StringUtils.capitalize(timesheet.getStartDateTime().getMonth().toString().toLowerCase().substring(0,3)) + " ");
            cutoffPeriod.append(timesheet.getStartDateTime().getDayOfMonth() + "-");
            cutoffPeriod.append(timesheet.getEndDateTime().getDayOfMonth() + ", ");
            cutoffPeriod.append(timesheet.getEndDateTime().getYear());
            cutoffPeriodDTO.setCutoffPeriod(cutoffPeriod.toString());
            cutoffPeriods.add(cutoffPeriodDTO);

            log.info("CUT OFF: " + cutoffPeriod.toString());

        }
        return cutoffPeriods;
    }

    public ByteArrayInputStream downloadTimesheet(EmployeeDTO employee, Long timesheetID, String timezone) throws IOException {

        Timesheet timesheet = timesheetRepository.findByEmployeeIDAndId(employee.getEmployeeID(), timesheetID);



        return fileWriterService.generateTimesheetSpreadsheet(employee, timesheet, timezone);

    }

}
