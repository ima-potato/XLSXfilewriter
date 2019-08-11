package com.lilo.liloproject.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ResourceMixin {

    @JsonProperty(value = "Id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    @JsonProperty(value = "Name", access = JsonProperty.Access.WRITE_ONLY)
    private String name;

    @JsonProperty(value = "FirstName__c", access = JsonProperty.Access.WRITE_ONLY)
    private String firstName;

    @JsonProperty(value = "LastName__c", access = JsonProperty.Access.WRITE_ONLY)
    private String lastName;

    @JsonProperty(value = "MiddleName__c", access = JsonProperty.Access.WRITE_ONLY)
    private String middleName;

    @JsonProperty(value = "IDNumber__c", access = JsonProperty.Access.WRITE_ONLY)
    private String employeeIdNumber;

    @JsonProperty(value = "OfficeEmail__c", access = JsonProperty.Access.WRITE_ONLY)
    private String officeEmail;

    @JsonProperty(value = "ImmediateManagerName__c", access = JsonProperty.Access.WRITE_ONLY)
    private String immediateManagerName;

    @JsonProperty(value = "ImmediateManagerEmail__c", access = JsonProperty.Access.WRITE_ONLY)
    private String immediateManagerEmail;

    @JsonProperty(value = "DateRegularized__c", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate regularizationDate;

    @JsonProperty(value = "HolidayGroup__c", access = JsonProperty.Access.WRITE_ONLY)
    private String holidayGroup;

    @JsonProperty(value = "WorkLocation__c", access = JsonProperty.Access.WRITE_ONLY)
    private String workLocation;

}
