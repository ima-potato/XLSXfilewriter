package com.lilo.liloproject.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lilo.liloproject.model.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class JobTitleMixin {

    @JsonProperty(value = "Id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    @JsonProperty(value = "Name", access = JsonProperty.Access.WRITE_ONLY)
    private String name;

    @JsonProperty(value = "CreatedBy", access = JsonProperty.Access.WRITE_ONLY)
    private User createdBy;

    @JsonProperty(value = "Owner", access = JsonProperty.Access.WRITE_ONLY)
    private User owner;

    @JsonProperty(value = "LastModifiedBy", access = JsonProperty.Access.WRITE_ONLY)
    private User lastModifiedBy;


}
