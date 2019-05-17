package com.polytech.ekwalsharezapi.dto;

import io.swagger.annotations.ApiModelProperty;

public class LedgerUserDTO {

    @ApiModelProperty(position = 0)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
