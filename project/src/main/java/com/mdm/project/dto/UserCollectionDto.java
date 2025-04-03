package com.mdm.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor
public class UserCollectionDto {
    private String customerId;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return customerId;
    }

    public void setId(String id) {
        this.customerId = id;
    }
}
