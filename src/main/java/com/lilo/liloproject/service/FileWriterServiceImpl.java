/**
 * Author: Aviv Guese
 * <p>
 * Service that is called to export the timesheet of a specific cutoff to a downloadable xlsx file
 * (see src/app/component/login/login.component.ts)
 **/
package com.lilo.liloproject.service;

import com.lilo.liloproject.dto.EmployeeDTO;
import com.lilo.liloproject.model.Timesheet;
import com.lilo.liloproject.util.ExcelSpreadSheetUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class FileWriterServiceImpl implements  FileWriterService{
    private ExcelSpreadSheetUtils writeExcelFile;

    public FileWriterServiceImpl(ExcelSpreadSheetUtils writeExcelFile) {
        this.writeExcelFile = writeExcelFile;
    }

    @Override
    public ByteArrayInputStream generateTimesheetSpreadsheet (EmployeeDTO employee,
                                                              Timesheet timesheet,
                                                              String timezone){

     return writeExcelFile.timesheetSpreadsheetGenerator(employee, timesheet, timezone);
    }
//    private String timesheetTemplateFilepath = "Timesheet_template.xlsx";
//    private static String carryOverCell = "+BX";
//    private static int carryOverRow = 7;
//    private static int balanceTimeOffColumn = 75;
//    private static final Logger log = LoggerFactory.getLogger(TimeLogController.class);
//    private static int timeinColumnIndex = 65;
//    private static int timeoutColumnIndex = 66;
//    private static int timesheetStartingRow = 9;
//    private static int regularHoursIndex = 2;
//    private static int timesheetStartDateColumnIndex = 13;
//    private static int timesheetStartDateRowIndex = 5;
//    private double regularHours = 8.0;
//    private static int billableHoursColumn = 64;
//
////    public ByteArrayInputStream timesheetSpreadsheet
//
//    public ByteArrayInputStream timesheetGenerator(EmployeeDTO employee, Timesheet timesheet, String timezone) {
//
//        String employeeLastName = employee.getLastName();
//        String employeeFirstName = employee.getFirstName();
//        String employeeId = employee.getEmployeeID();
//        String employeeDepartment = employee.getDepartment();
//        String employeePosition = employee.getJobTitle();
//        String immidiateSupervisor = employee.getImmediateSuperVisor();
//        String clientCompanyName = employee.getClientCompanyName();
//        String projectName = employee.getProject();
//        String consultantCompany = employee.getConsultantCompany();
//        String employeeName = employeeLastName.toUpperCase() + ", " + employeeFirstName.toUpperCase();
//        log.info("Writing timesheet for employee number: " + employeeId);
//
//        int logDateIndex = 0;
//        int insertCount = 0;
//        String billableHourStart = "IFERROR(-1";
//        String billableHourEnd = ",\"INCOMPLETE\")";
//        DateTimeFormatter formatterMonthDateYearDay = DateTimeFormatter.ofPattern("dd-MM-yyyy E");
//        DateTimeFormatter formatterMonthDateYear = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
//        DateTimeFormatter timeLogFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//
//        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
//
//            FileInputStream inputStream = new FileInputStream(new File(timesheetTemplateFilepath));
//            Workbook workbook = WorkbookFactory.create(inputStream);
//            Sheet sheet = workbook.getSheetAt(0);
//            sheet.getForceFormulaRecalculation();
//            int referenceCell = 0;
//            int multipleLogCounter = 0;
//            //Put employee Details:
////            Cell updateConsultant = sheet.getRow(2).getCell(1);
////            updateConsultant.setCellValue("SEVEN SEVEN GLOBAL SERVICES INC");
//
//            cellWriter(sheet, 2, 1, "SEVEN SEVEN GLOBAL SERVICES INC");
//
////            Cell updateEmployeeName = sheet.getRow(3).getCell(1);
////            updateEmployeeName.setCellValue(employeeName);
//
//            cellWriter(sheet, 3, 1, employeeName);
//
////            Cell updateEmployeePosition = sheet.getRow(4).getCell(1);
////            updateEmployeePosition.setCellValue(employeePosition.toUpperCase());
//
//            cellWriter(sheet, 4, 1, employeePosition.toUpperCase());
//
////            Cell updateDepartment = sheet.getRow(5).getCell(1);
////            updateDepartment.setCellValue(employeeDepartment);
//
//            cellWriter(sheet, 5, 1, employeeDepartment);
//            //write implementation here
//            //insert log starting at index row: 9
//            String startDateTS = "";
//            TimesheetFormatDTO formattedTS = new TimesheetUtils().timesheetGenerator(timesheet, timezone);
//            if (formattedTS.getStartDateTime() != null) {
//                startDateTS = formattedTS.getStartDateTime().format(formatterMonthDateYear);
//            }
//
////            Cell updateTimesheetStartDate = sheet.getRow(timesheetStartDateRowIndex).getCell(timesheetStartDateColumnIndex);
////            updateTimesheetStartDate.setCellValue(startDateTS);
//
//            cellWriter(sheet, timesheetStartDateRowIndex,timesheetStartDateColumnIndex,startDateTS);
//
//
//            for(TimesheetEntryDTO entry: formattedTS.getEntryList()){
//                log.info(entry.getTimeIn() + " - "+ entry.getTimeOut());
//            }
//            StringBuilder billableHourFormula = new StringBuilder();
//            billableHourFormula.append(billableHourStart);
////            for (TimesheetEntryDTO log : formattedTS.getEntryList()) {
//            for (int i = 0; i < formattedTS.getEntryList().size(); i++) {
//                TimesheetEntryDTO log = formattedTS.getEntryList().get(i);
//                LocalDateTime timeInLDT = LocalDateTime.parse(log.getTimeIn());
//                int currentDate = timeInLDT.getDayOfMonth();
//                //check current log is equal to the previous log
//                int currentRowIndex = timesheetStartingRow + insertCount;
//                insertRow(workbook.getSheetAt(0), currentRowIndex, 1);
//                rowFormatter(sheet, 10 + insertCount, timesheetStartingRow + insertCount);
//                Cell updateLogDate = sheet.getRow(currentRowIndex).getCell(logDateIndex);
//                Cell updateTimeIn = sheet.getRow(currentRowIndex).getCell(timeinColumnIndex);
//                Cell updateTimeOut = sheet.getRow(currentRowIndex).getCell(timeoutColumnIndex);
//                Cell updateRegularHours = sheet.getRow(currentRowIndex).getCell(regularHoursIndex);
//
//                updateLogDate.setCellValue(timeInLDT.format(formatterMonthDateYearDay));
//
//                String timeIn = "";
//                String timeOut = "";
//
//                if (!log.getPlaceholder()) {
//                    if (!log.getTimeIn().equals("")) {
//                        timeIn = LocalDateTime.parse(log.getTimeIn()).format(timeLogFormatter);
//                    }
//                    if (!log.getTimeOut().equals("")) {
//                        timeOut = LocalDateTime.parse(log.getTimeOut()).format(timeLogFormatter);
//                    }
////                    updateRegularHours.setCellValue(regularHours);
//                }
////                sheet.getForceFormulaRecalculation();
//                updateTimeIn.setCellValue(timeIn);
//                updateTimeOut.setCellValue(timeOut);
//                billableHourFormula.append("+").append(billableHourFiller(String.valueOf(timesheetStartingRow + insertCount + 1), true));
//                if (i != 0) {
//                    TimesheetEntryDTO previousLog = formattedTS.getEntryList().get(i - 1);
//                    LocalDateTime previousLogTimeInLDT = LocalDateTime.parse(previousLog.getTimeIn());
//
//
//                    int previousDate = previousLogTimeInLDT.getDayOfMonth();
//
//                    if (currentDate == previousDate) {
//                        multipleLogCounter++;
//                        //append formula
////                        billableHourFormula.append("+").append(billableHourFiller(String.valueOf(timesheetStartingRow + insertCount)));
//                        System.out.println("new formula" + billableHourFormula);
//                        //                        merge cells on cellsif()
//                        int currentBillableLength = billableHourFormula.length();
//                        int expectedBillableLength = billableHourFiller(String.valueOf(currentRowIndex + 1), true).length() +
//                                billableHourFiller(String.valueOf(currentRowIndex), true).length() + 3;
//                        System.out.println("current length" + currentBillableLength);
//                        System.out.println("expected length" + expectedBillableLength);
//                        //expected must always be less than since we are not adding the "+" in the formula string
//                        if (expectedBillableLength > currentBillableLength) {
//                            multipleLogCounter++;
//                            billableHourFormula.append("+").append(billableHourFiller(String.valueOf(currentRowIndex), true));
//                            //reset the string
//                            billableHourFormula.setLength(0);
//                            //the first common log's computation was not added since it doesnt have a previous log similar to it
//                            billableHourFormula.append(billableHourStart).append("+").append(billableHourFiller(String.valueOf(currentRowIndex), true))
//                                    .append("+").append(billableHourFiller(String.valueOf(currentRowIndex + 1), true));
//                            //empty the cell because the computed value stays there and will be included in the computation even when merged
//
//                        }
//                        Cell billableHourCell = sheet.getRow(currentRowIndex).getCell(billableHoursColumn);
//                        billableHourCell.setCellValue("");
//                    } else {
//                        multipleLogCounter = 0;
//                        //reset the billable hour formula
//                        billableHourFormula.setLength(2);
////                        Cell billableHourCell = sheet.getRow(currentRowIndex).getCell(billableHoursColumn);
////                        billableHourCell.setCellFormula(billableHourFiller(String.valueOf(currentRowIndex + 1), false));
//                    }
//                    System.out.println("so much logs: " + multipleLogCounter);
//                    //merge cells if the billable formula is filled since
//                    if (billableHourFormula.length() != 2) {
//
////                        check if the next log is the same day
////                        same day - do notmerge yet
//
//                        if (i != formattedTS.getEntryList().size() - 1) {
//
//                            TimesheetEntryDTO succeedingLog = formattedTS.getEntryList().get(i + 1);
//                            LocalDateTime succeedingLogTimeInLDT = LocalDateTime.parse(succeedingLog.getTimeIn());
//                            int succeedingDate = succeedingLogTimeInLDT.getDayOfMonth();
//                            if (currentDate != succeedingDate) {
//                                //SINCE POI DOES NOT SUPPORT SPECIFIC MERGED REGION UNMERGING. THIS BLOCK
//                                System.out.println("next not equal: im merging now");
//                                sheet.addMergedRegion(CellRangeAddress.valueOf("BM" + String.valueOf(currentRowIndex - multipleLogCounter + 2) + ":BM" + String.valueOf(currentRowIndex + 1)));
//                            }
//
//                        } else {
//                            if (currentDate == previousDate) {
//                                sheet.addMergedRegion(CellRangeAddress.valueOf("BM" + String.valueOf(currentRowIndex - multipleLogCounter + 2) + ":BM" + String.valueOf(currentRowIndex + 1)));
//                            }
//
//                        }
//
//
//                        Cell billableHoursCell = sheet.getRow(currentRowIndex - multipleLogCounter + 1).getCell(billableHoursColumn);
//                        billableHoursCell.setCellFormula(billableHourFormula.toString() + billableHourEnd);
//
//                    }
//
//
//                } else {
////                    This handles the starting log if it is one of the multiple log entry
//                    if (i != formattedTS.getEntryList().size() - 1) {
//                        TimesheetEntryDTO succeedingLog = formattedTS.getEntryList().get(i + 1);
//                        LocalDateTime succeedingLogTimeInLDT = LocalDateTime.parse(succeedingLog.getTimeIn());
//                        int succeedingDate = succeedingLogTimeInLDT.getDayOfMonth();
//                        if (currentDate == succeedingDate) {
//                            multipleLogCounter++;
//                            Cell billableHourCell = sheet.getRow(currentRowIndex).getCell(billableHoursColumn);
//                            billableHourCell.setCellValue("");
//                        }
//
//                    }
//                }
//                insertCount++;
//
//
//            }
//            //remove template row
//            referenceCell = timesheetStartingRow + insertCount;
//            sheet.shiftRows(timesheetStartingRow + insertCount + 1, sheet.getLastRowNum(), -1);
//            totalRowFormatter(sheet, workbook, timesheetStartingRow + insertCount,
//                    timesheetStartingRow + 1,
//                    referenceCell);
//
//            Cell updateBalanceTimeOffTotal = sheet.getRow(referenceCell).getCell(balanceTimeOffColumn);
//            Cell getBalanceTimeOff = sheet.getRow(referenceCell - 1).getCell(balanceTimeOffColumn);
//            updateBalanceTimeOffTotal.setCellFormula(getBalanceTimeOff.getAddress().toString()
//                    .replaceAll("[0-9]", "") + referenceCell);
//
//            inputStream.close();
//
//            workbook.write(out);
//            return new ByteArrayInputStream(out.toByteArray());
//        } catch (IOException | EncryptedDocumentException
//                | InvalidFormatException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//
//    }
//
//    public static void fileWriter(){
//
//    }
//
//    public static void insertRow(Sheet sheet, int rowIndex, int numberOfRows) {
//        sheet.shiftRows(rowIndex, sheet.getLastRowNum(), numberOfRows);
//        sheet.createRow(rowIndex);
//    }
//
//    public static void cellWriter(Sheet sheet, int targetRow, int targetColumn, String filler) {
//        int cellType = 1; //set String as defult type
//        Cell cell = sheet.getRow(targetRow).getCell(targetColumn);
//        switch (cell.getCellTypeEnum()) {
//            case FORMULA:
//                cellType = 2;
//                break;
//            default:
//                cellType = 1;
//                break;
//        }
//        if (cellType == 2) {
//            cell.setCellFormula(filler);
//        } else cell.setCellValue(filler);
//    }
//
//    public static void totalRowFormatter(Sheet sheet, Workbook workbook, int targetRow, int logStartRow, int logEndRow) {
//        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
//        for (int targetCell = 0; targetCell < sheet.getRow(targetRow).getLastCellNum(); targetCell++) {
//            Cell target = sheet.getRow(targetRow).getCell(targetCell);
//            String column = target.getAddress().toString().replaceAll("[0-9]", "");
//            if (target.getCellTypeEnum() == CellType.FORMULA) {
//                target.setCellFormula("SUM(" + column + logStartRow + ":" + column + logEndRow + ")");
//                evaluator.evaluate(target);
//
//            }
//        }
//    }
//
//    public static void rowFormatter(Sheet sheet, int templateRow, int targetRow) {
//        for (int templateColumn = 0; templateColumn < sheet.getRow(templateRow).getLastCellNum(); templateColumn++) {
//            int targetColumn = templateColumn;
//            if (targetColumn == balanceTimeOffColumn) {
//                if (targetRow == 9) {
//                    cellFormatCopier(sheet, templateRow, templateColumn, targetRow, targetColumn, carryOverFormatter(carryOverCell, carryOverRow));
//                } else
//                    cellFormatCopier(sheet, templateRow, templateColumn, targetRow, targetColumn, carryOverFormatter(carryOverCell, targetRow));
//            } else cellFormatCopier(sheet, templateRow, templateColumn, targetRow, targetColumn, "");
//
//        }
//    }
//
//    public static String carryOverFormatter(String carryOver, int row) {
//        return carryOver + row;
//    }
//
//    public static void cellFormatCopier(Sheet sheet, int templateRow, int templateColumn, int newTargetRow, int newTargetColumn, String carryOver) {
//        Cell templateCell = sheet.getRow(templateRow).getCell(templateColumn);
//        Cell targetCell = sheet.getRow(newTargetRow).createCell(newTargetColumn);
//        targetCell.setCellType(templateCell.getCellTypeEnum());
//        targetCell.setCellStyle(templateCell.getCellStyle());
//        //copy template if it is formula
//        if (targetCell.getCellTypeEnum() == CellType.FORMULA) {
//            targetCell.setCellFormula(logFormulaFormatter(templateCell.getCellFormula(), newTargetRow, carryOver));
//        }
//
//    }
//
//
//    public static String logFormulaFormatter(String formula, int rowNumber, String carryOver) {
//        String[] formulaArr = formula.split("");
//        StringBuilder originRow = new StringBuilder();
//        for (int i = 0; i < formulaArr.length; i++) {
//            if (StringUtils.isNumeric(formulaArr[i])) {
//                originRow.append(formulaArr[i]);
//                if (!StringUtils.isNumeric(formulaArr[i + 1])) {
//                    break;
//                }
//            }
//        }
//        return formula.replaceAll(originRow.toString(), String.valueOf(rowNumber + 1))
//                + carryOver;
//    }
//
//    public static String billableHourFiller(String row, boolean isGrouped) {
//        String str = "-1";
//        if (isGrouped) {
//            str = "";
//        }
//
//        return "IF(AND(BN" + row + "=BO" + row + ",NOT(BN" + row + "=\"\")),23,IF(OR($BN"
//                + row + "=\"\",$BO" + row + "=\"\"),\"\",IF(((BO" + row + "+(BN" + row + ">BO" + row + ")-BN"
//                + row + ")*24)=0,\"\",(BO" + row + "+(BN" + row + ">BO" + row + ")-BN" + row + ")*24)" + str + "))";
//
//    }


}
