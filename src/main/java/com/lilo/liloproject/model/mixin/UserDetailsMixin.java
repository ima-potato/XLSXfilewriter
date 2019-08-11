package com.lilo.liloproject.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilo.liloproject.model.JobTitle;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class UserDetailsMixin {

    @JsonProperty(value = "Id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    @JsonProperty(value = "IDNumber__c", access = JsonProperty.Access.WRITE_ONLY)
    private String employeeIdNumber;

    @JsonProperty(value = "JobTitle__r", access = JsonProperty.Access.WRITE_ONLY)
    private JobTitle jobTitle;

    @JsonProperty(value = "Department__c", access = JsonProperty.Access.WRITE_ONLY)
    private String department;

    @JsonProperty(value = "HolidayGroup__c", access = JsonProperty.Access.WRITE_ONLY)
    private String holidayGroup;

    @JsonProperty(value = "ImmediateManagerName__c", access = JsonProperty.Access.WRITE_ONLY)
    private String immediateManagerName;


}
