package com.lilo.liloproject.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class HolidayMixin {

    @JsonProperty(value = "Id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    @JsonProperty(value = "Name", access = JsonProperty.Access.WRITE_ONLY)
    private String name;

    @JsonProperty(value = "Date__c", access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    @JsonProperty(value = "Group__c", access = JsonProperty.Access.WRITE_ONLY)
    private String group;

    @JsonProperty(value = "Location__c", access = JsonProperty.Access.WRITE_ONLY)
    private String location;
}
