/**
 * Author: Aviv Guese
 *
 * Utility used in exporting timesheet into XLSX format
 **/
package com.lilo.liloproject.util;


import com.lilo.liloproject.dto.EmployeeDTO;
import com.lilo.liloproject.dto.TimesheetEntryDTO;
import com.lilo.liloproject.dto.TimesheetFormatDTO;
import com.lilo.liloproject.model.Timesheet;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ExcelSpreadSheetUtils {
    private static String timesheetTemplateFilepath = "Timesheet_template.xlsx";
    private static String carryOverCell = "+BX";
    private static int carryOverRow = 7;
    private static int balanceTimeOffColumn = 75;
    private static final Logger log = LoggerFactory.getLogger(ExcelSpreadSheetUtils.class);
    private static int timeinColumnIndex = 65;
    private static int timeoutColumnIndex = 66;
    private static int timesheetStartingRow = 9;
    private static int regularHoursIndex = 2;
    private static int timesheetStartDateColumnIndex = 13;
    private static int timesheetStartDateRowIndex = 5;
    private static double regularHours = 8.0;
    private static int billableHoursColumn = 64;
    private static int logDateIndex = 0;
    private static int insertCount = 0;


    public ByteArrayInputStream timesheetSpreadsheetGenerator(EmployeeDTO employee, Timesheet timesheet, String timezone) {

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            FileInputStream inputStream = new FileInputStream(new File(timesheetTemplateFilepath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            sheet.getForceFormulaRecalculation();

            timesheetContentWriter(workbook, sheet, employee, timesheet, timezone);
            inputStream.close();

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException | EncryptedDocumentException
                | InvalidFormatException ex) {
            ex.printStackTrace();
            return null;
        }

    }
    public static void timesheetContentWriter(Workbook workbook, Sheet sheet, EmployeeDTO employee, Timesheet timesheet, String timezone){
        String employeeLastName = employee.getLastName();
        String employeeFirstName = employee.getFirstName();
        String employeeId = employee.getEmployeeID();
        String employeeDepartment = employee.getDepartment();
        String employeePosition = employee.getJobTitle();
        String immidiateSupervisor = employee.getImmediateSuperVisor();
        String employeeName = employeeLastName.toUpperCase() + ", " + employeeFirstName.toUpperCase();
        DateTimeFormatter formatterMonthDateYear = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        TimesheetFormatDTO formattedTS = new TimesheetUtils().timesheetGenerator(timesheet, timezone);
        String startDateTS = "";
        if (formattedTS.getStartDateTime() != null) {
            startDateTS = formattedTS.getStartDateTime().format(formatterMonthDateYear);
        }
        int referenceCell = 0;
        //Put employee and timesheet Details:
        //update company name
        cellWriter(sheet, 2, 1, "SEVEN SEVEN GLOBAL SERVICES INC");
        //write employee name
        cellWriter(sheet, 3, 1, employeeName);
        //write employee position
        cellWriter(sheet, 4, 1, employeePosition.toUpperCase());
        //write employee department
        cellWriter(sheet, 5, 1, employeeDepartment);
        //update timesheet startdate
        cellWriter(sheet, timesheetStartDateRowIndex, timesheetStartDateColumnIndex, startDateTS);

        //insert rows then write the respective logs
        timesheetLogWriter(workbook, sheet, formattedTS);
        //remove template row
        referenceCell = timesheetStartingRow + insertCount;
        //delete template row
        sheet.shiftRows(timesheetStartingRow + insertCount + 1, sheet.getLastRowNum(), -1);
        totalRowFormatter(sheet, workbook, timesheetStartingRow + insertCount,
                timesheetStartingRow + 1,
                referenceCell);
        //update balance timeoff column
        Cell getBalanceTimeOff = sheet.getRow(referenceCell - 1).getCell(balanceTimeOffColumn);
        cellWriter(sheet, referenceCell, balanceTimeOffColumn, getBalanceTimeOff.getAddress().toString()
                .replaceAll("[0-9]", "") + referenceCell);
        //reset row insert counter
        insertCount = 0;


    }

    public static void timesheetLogWriter(Workbook workbook, Sheet sheet, TimesheetFormatDTO timesheet) {
        int multipleLogCounter = 0;
        DateTimeFormatter formatterMonthDateYearDay = DateTimeFormatter.ofPattern("dd-MM-yyyy E");
        DateTimeFormatter timeLogFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        StringBuilder billableHourFormula = new StringBuilder();
        String billableHourStart = "IFERROR(-1";
        String billableHourEnd = ",\"INCOMPLETE\")";
        billableHourFormula.append(billableHourStart);
        //insert log starting at index row: 9

        for (int i = 0; i < timesheet.getEntryList().size(); i++) {
            TimesheetEntryDTO log = timesheet.getEntryList().get(i);
            LocalDateTime timeInLDT = LocalDateTime.parse(log.getTimeIn());
            int currentDate = timeInLDT.getDayOfMonth();
            int currentRowIndex = timesheetStartingRow + insertCount;
            String timeIn = "";
            String timeOut = "";

            //create row for the log
            insertRow(workbook.getSheetAt(0), currentRowIndex, 1);
            rowFormatter(sheet, 10 + insertCount, timesheetStartingRow + insertCount);
            //write the log date in the date column
            cellWriter(sheet, currentRowIndex, logDateIndex, timeInLDT.format(formatterMonthDateYearDay));


            if (!log.getPlaceholder()) {
                if (!log.getTimeIn().equals("")) {
                    timeIn = LocalDateTime.parse(log.getTimeIn()).format(timeLogFormatter);
                }
                if (!log.getTimeOut().equals("")) {
                    timeOut = LocalDateTime.parse(log.getTimeOut()).format(timeLogFormatter);
                }
            }
            //write time logs (in and out)
            cellWriter(sheet, currentRowIndex, timeinColumnIndex, timeIn);
            cellWriter(sheet, currentRowIndex, timeoutColumnIndex, timeOut);

            billableHourFormula.append("+").append(billableHourFiller(String.valueOf(timesheetStartingRow + insertCount + 1), true));
            if (i != 0) {
                TimesheetEntryDTO previousLog = timesheet.getEntryList().get(i - 1);
                LocalDateTime previousLogTimeInLDT = LocalDateTime.parse(previousLog.getTimeIn());


                int previousDate = previousLogTimeInLDT.getDayOfMonth();

                if (currentDate == previousDate) {
                    multipleLogCounter++;
                    int currentBillableLength = billableHourFormula.length();
                    int expectedBillableLength = billableHourFiller(String.valueOf(currentRowIndex + 1), true).length() +
                            billableHourFiller(String.valueOf(currentRowIndex), true).length() + 3;

                    //expected must always be less than since we are not adding the "+" in the formula string
                    if (expectedBillableLength > currentBillableLength) {
                        multipleLogCounter++;
                        billableHourFormula.append("+").append(billableHourFiller(String.valueOf(currentRowIndex), true));
                        //reset the string
                        billableHourFormula.setLength(0);
                        //the first common log's computation was not added since it doesnt have a previous log similar to it
                        billableHourFormula.append(billableHourStart).append("+").append(billableHourFiller(String.valueOf(currentRowIndex), true))
                                .append("+").append(billableHourFiller(String.valueOf(currentRowIndex + 1), true));
                        //empty the cell because the computed value stays there and will be included in the computation even when merged

                    }
                    Cell billableHourCell = sheet.getRow(currentRowIndex).getCell(billableHoursColumn);
                    billableHourCell.setCellValue("");
//                    cellWriter(sheet, currentRowIndex, billableHoursColumn, "");
                } else {
                    multipleLogCounter = 0;
                    //reset the billable hour formula
                    billableHourFormula.setLength(2);
                }
                //merge cells if the billable formula is filled since
                if (billableHourFormula.length() != 2) {

//                        check if the next log is the same day
//                        same day - do not merge yet

                    if (i != timesheet.getEntryList().size() - 1) {

                        TimesheetEntryDTO succeedingLog = timesheet.getEntryList().get(i + 1);
                        LocalDateTime succeedingLogTimeInLDT = LocalDateTime.parse(succeedingLog.getTimeIn());
                        int succeedingDate = succeedingLogTimeInLDT.getDayOfMonth();
                        if (currentDate != succeedingDate) {
                            //SINCE POI DOES NOT SUPPORT SPECIFIC MERGED REGION UNMERGING. THIS BLOCK
                            sheet.addMergedRegion(CellRangeAddress.valueOf("BM" + String.valueOf(currentRowIndex - multipleLogCounter + 2) + ":BM" + String.valueOf(currentRowIndex + 1)));
                        }

                    } else {
                        if (currentDate == previousDate) {
                            sheet.addMergedRegion(CellRangeAddress.valueOf("BM" + String.valueOf(currentRowIndex - multipleLogCounter + 2) + ":BM" + String.valueOf(currentRowIndex + 1)));
                        }

                    }
                    cellWriter(sheet,
                            currentRowIndex - multipleLogCounter + 1,
                            billableHoursColumn,
                            billableHourFormula.toString() + billableHourEnd);
                }
            } else {
//                    This handles the starting log if it is one of the multiple log entry
                if (i != timesheet.getEntryList().size() - 1) {
                    TimesheetEntryDTO succeedingLog = timesheet.getEntryList().get(i + 1);
                    LocalDateTime succeedingLogTimeInLDT = LocalDateTime.parse(succeedingLog.getTimeIn());
                    int succeedingDate = succeedingLogTimeInLDT.getDayOfMonth();
                    if (currentDate == succeedingDate) {
                        multipleLogCounter++;
                        cellWriter(sheet, currentRowIndex, billableHoursColumn, "");
                    }

                }
            }
            insertCount++;
        }
    }

    public static void insertRow(Sheet sheet, int rowIndex, int numberOfRows) {
        sheet.shiftRows(rowIndex, sheet.getLastRowNum(), numberOfRows);
        sheet.createRow(rowIndex);
    }

    public static void cellWriter(Sheet sheet, int targetRow, int targetColumn, String filler) {
        int cellType = 1; //set String as defult type
        Cell cell = sheet.getRow(targetRow).getCell(targetColumn);
        switch (cell.getCellTypeEnum()) {
            case FORMULA:
                cellType = 2;
                break;
            default:
                cellType = 1;
                break;
        }
        if (cellType == 2) {
            cell.setCellFormula(filler);
        } else cell.setCellValue(filler);
    }

    public static void totalRowFormatter(Sheet sheet, Workbook workbook, int targetRow, int logStartRow, int logEndRow) {
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        for (int targetCell = 0; targetCell < sheet.getRow(targetRow).getLastCellNum(); targetCell++) {
            Cell target = sheet.getRow(targetRow).getCell(targetCell);
            String column = target.getAddress().toString().replaceAll("[0-9]", "");
            if (target.getCellTypeEnum() == CellType.FORMULA) {
                target.setCellFormula("SUM(" + column + logStartRow + ":" + column + logEndRow + ")");
                evaluator.evaluate(target);

            }
        }
    }

    public static void rowFormatter(Sheet sheet, int templateRow, int targetRow) {
        for (int templateColumn = 0; templateColumn < sheet.getRow(templateRow).getLastCellNum(); templateColumn++) {
            int targetColumn = templateColumn;
            if (targetColumn == balanceTimeOffColumn) {
                if (targetRow == 9) {
                    cellFormatCopier(sheet, templateRow, templateColumn, targetRow, targetColumn, carryOverFormatter(carryOverCell, carryOverRow));
                } else
                    cellFormatCopier(sheet, templateRow, templateColumn, targetRow, targetColumn, carryOverFormatter(carryOverCell, targetRow));
            } else cellFormatCopier(sheet, templateRow, templateColumn, targetRow, targetColumn, "");

        }
    }

    public static String carryOverFormatter(String carryOver, int row) {
        return carryOver + row;
    }

    public static void cellFormatCopier(Sheet sheet, int templateRow, int templateColumn, int newTargetRow, int newTargetColumn, String carryOver) {
        Cell templateCell = sheet.getRow(templateRow).getCell(templateColumn);
        Cell targetCell = sheet.getRow(newTargetRow).createCell(newTargetColumn);
        targetCell.setCellType(templateCell.getCellTypeEnum());
        targetCell.setCellStyle(templateCell.getCellStyle());
        //copy template if it is formula
        if (targetCell.getCellTypeEnum() == CellType.FORMULA) {
            targetCell.setCellFormula(logFormulaFormatter(templateCell.getCellFormula(), newTargetRow, carryOver));
        }

    }


    public static String logFormulaFormatter(String formula, int rowNumber, String carryOver) {
        String[] formulaArr = formula.split("");
        StringBuilder originRow = new StringBuilder();
        for (int i = 0; i < formulaArr.length; i++) {
            if (StringUtils.isNumeric(formulaArr[i])) {
                originRow.append(formulaArr[i]);
                if (!StringUtils.isNumeric(formulaArr[i + 1])) {
                    break;
                }
            }
        }
        return formula.replaceAll(originRow.toString(), String.valueOf(rowNumber + 1))
                + carryOver;
    }

    public static String billableHourFiller(String row, boolean isGrouped) {
        String str = "-1";
        if (isGrouped) {
            str = "";
        }

        return "IF(AND(BN" + row + "=BO" + row + ",NOT(BN" + row + "=\"\")),23,IF(OR($BN"
                + row + "=\"\",$BO" + row + "=\"\"),\"\",IF(((BO" + row + "+(BN" + row + ">BO" + row + ")-BN"
                + row + ")*24)=0,\"\",(BO" + row + "+(BN" + row + ">BO" + row + ")-BN" + row + ")*24)" + str + "))";

    }
}
