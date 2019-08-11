package com.lilo.liloproject.dto;

public class EmployeeDTO {
    private String lastName;
    private String firstName;
    private String employeeID;
    private String department;
    private String immediateSuperVisor;
    private String clientCompanyName;
    private String clientManagerName;
    private String jobTitle;
    private String project;
    private String consultantCompany; //constant seven seven
    public EmployeeDTO() {
    }

    public EmployeeDTO(String lastName, String firstName, String employeeID, String department, String immediateSuperVisor, String clientCompanyName, String clientManagerName, String project, String consultantCompany) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.employeeID = employeeID;
        this.department = department;
        this.immediateSuperVisor = immediateSuperVisor;
        this.clientCompanyName = clientCompanyName;
        this.clientManagerName = clientManagerName;
        this.project = project;
        this.consultantCompany = consultantCompany;
    }

    public EmployeeDTO(String lastName, String firstName, String employeeID, String department, String immediateSuperVisor, String clientCompanyName, String clientManagerName, String jobTitle, String project, String consultantCompany) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.employeeID = employeeID;
        this.department = department;
        this.immediateSuperVisor = immediateSuperVisor;
        this.clientCompanyName = clientCompanyName;
        this.clientManagerName = clientManagerName;
        this.jobTitle = jobTitle;
        this.project = project;
        this.consultantCompany = consultantCompany;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {

        this.department = department;
    }

    public String getImmediateSuperVisor() {
        return immediateSuperVisor;
    }

    public void setImmediateSuperVisor(String immediateSuperVisor) {
        this.immediateSuperVisor = immediateSuperVisor;
    }

    public String getClientCompanyName() {
        return clientCompanyName;
    }

    public void setClientCompanyName(String clientCompanyName) {
        this.clientCompanyName = clientCompanyName;
    }

    public String getClientManagerName() {
        return clientManagerName;
    }

    public void setClientManagerName(String clientManagerName) {
        this.clientManagerName = clientManagerName;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getConsultantCompany() {
        return consultantCompany;
    }

    public void setConsultantCompany(String consultantCompany) {
        this.consultantCompany = consultantCompany;
    }
}
