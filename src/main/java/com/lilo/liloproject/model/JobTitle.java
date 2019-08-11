package com.lilo.liloproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class JobTitle implements SalesforceObject {

    private String id;

    private String name;

    private User createdBy;

    private User owner;

    private User lastModifiedBy;

    @JsonIgnore
    public String getId() {
        return id;
    }

    @JsonProperty(value = "Id")
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @JsonProperty(value = "Name")
    public void setName(String name) {
        this.name = name;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    @JsonProperty(value = "CreatedBy")
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getOwner() {
        return owner;
    }

    @JsonProperty(value = "Owner")
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    @JsonProperty(value = "LastModifiedBy")
    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public String toString() {
        return "JobTitle{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdBy=" + createdBy +
                ", owner=" + owner +
                ", lastModifiedBy=" + lastModifiedBy +
                '}';
    }
}
