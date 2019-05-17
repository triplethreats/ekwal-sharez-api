package com.polytech.ekwalsharezapi.dto;


import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class LedgerDTO {

    @ApiModelProperty(position = 0)
    private String title;
    @ApiModelProperty(position = 1)
    private String description;
    @ApiModelProperty(position = 2)
    private List<LedgerUserDTO> users;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LedgerUserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<LedgerUserDTO> users) {
        this.users = users;
    }
}
