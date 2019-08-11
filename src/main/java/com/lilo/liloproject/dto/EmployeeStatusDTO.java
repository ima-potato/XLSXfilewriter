package com.lilo.liloproject.dto;

public class EmployeeStatusDTO {

    private String status;
    private String lastTransactionTime;


    public EmployeeStatusDTO() {

    }

    public EmployeeStatusDTO(String status, String lastTransactionTime) {
        this.status = status;
        this.lastTransactionTime = lastTransactionTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastTransactionTime() {
        return lastTransactionTime;
    }

    public void setLastTransactionTime(String lastTransactionTime) {
        this.lastTransactionTime = lastTransactionTime;
    }
}
