package com.lilo.liloproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

public class Resource implements SalesforceObject {

    private String id;

    private String name;

    private String firstName;

    private String lastName;

    private String middleName;

    private String employeeIdNumber;

    private String officeEmail;

    private String immediateManagerName;

    private String immediateManagerEmail;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate regularizationDate;

    private String holidayGroup;

    private String workLocation;

    private boolean isManager;

//    private String
//    private List<LeaveEntitlement> leaveEntitlements;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmployeeIdNumber() {
        return employeeIdNumber;
    }

    public void setEmployeeIdNumber(String employeeIdNumber) {
        this.employeeIdNumber = employeeIdNumber;
    }

    public String getOfficeEmail() {
        return officeEmail;
    }

    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail;
    }

    public String getImmediateManagerName() {
        return immediateManagerName;
    }

    public void setImmediateManagerName(String immediateManagerName) { this.immediateManagerName = immediateManagerName; }

    public String getImmediateManagerEmail() {
        return immediateManagerEmail;
    }

    public void setImmediateManagerEmail(String immediateManagerEmail) { this.immediateManagerEmail = immediateManagerEmail; }

    public LocalDate getRegularizationDate() {
        return regularizationDate;
    }

    public void setRegularizationDate(LocalDate regularizationDate) {
        this.regularizationDate = regularizationDate;
    }

    public String getHolidayGroup() { return holidayGroup; }

    public void setHolidayGroup(String holidayGroup) { this.holidayGroup = holidayGroup; }

    public String getWorkLocation() { return workLocation; }

    public void setWorkLocation(String workLocation) { this.workLocation = workLocation; }

    public boolean isManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

//    public List<LeaveEntitlement> getLeaveEntitlements() {
//        return leaveEntitlements;
//    }
//
//    public void setLeaveEntitlements(List<LeaveEntitlement> leaveEntitlements) {
//        this.leaveEntitlements = leaveEntitlements;
//    }

    @Override
    public String toString() {
        return "Resource{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", employeeIdNumber='" + employeeIdNumber + '\'' +
                ", officeEmail='" + officeEmail + '\'' +
                ", immediateManagerName='" + immediateManagerName + '\'' +
                ", immediateManagerEmail='" + immediateManagerEmail + '\'' +
                ", regularizationDate=" + regularizationDate +
                ", holidayGroup='" + holidayGroup + '\'' +
                ", workLocation='" + workLocation + '\'' +
                ", isManager=" + isManager +
//                ", leaveEntitlements=" + leaveEntitlements +
                '}';
    }
}
