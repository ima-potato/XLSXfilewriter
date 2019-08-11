package com.lilo.liloproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;

@Component
//@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails implements SalesforceObject {

    private String id;

    private String employeeIdNumber;

    private String department;

    private JobTitle jobTitle;

    private String holidayGroup;

    private String immediateManagerName;


    @JsonIgnore
    public String getId() {
        return id;
    }

    @JsonProperty(value = "Id")
    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeIdNumber() {
        return employeeIdNumber;
    }

    @JsonProperty(value = "IDNumber__c")
    public void setEmployeeIdNumber(String employeeIdNumber) {
        this.employeeIdNumber = employeeIdNumber;
    }

    public String getDepartment() {
        return department;
    }

    @JsonProperty(value = "Department__c")
    public void setDepartment(String department) {
        this.department = department;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    @JsonProperty(value = "JobTitle__r")
    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getHolidayGroup() {
        return holidayGroup;
    }

    @JsonProperty(value = "HolidayGroup__c")
    public void setHolidayGroup(String holidayGroup) {
        this.holidayGroup = holidayGroup;
    }

    public String getImmediateManagerName() {
        return immediateManagerName;
    }

    @JsonProperty(value = "ImmediateManagerName__c")
    public void setImmediateManagerName(String immediateManagerName) {
        this.immediateManagerName = immediateManagerName;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id='" + id + '\'' +
                ", employeeIdNumber='" + employeeIdNumber + '\'' +
                ", department='" + department + '\'' +
                ", jobTitle='" + jobTitle.getName() + '\'' +
                ", holidayGroup='" + holidayGroup + '\'' +
                ", immediateManager='" + immediateManagerName + '\'' +
                '}';
    }
}

