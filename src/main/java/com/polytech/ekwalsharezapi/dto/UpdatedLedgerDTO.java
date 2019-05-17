package com.polytech.ekwalsharezapi.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class UpdatedLedgerDTO {

    @ApiModelProperty(position = 0)
    private LedgerDTO updatedLedger;
    @ApiModelProperty(position = 1)
    private List<LedgerUserDTO> newUsers;

    public LedgerDTO getUpdatedLedger() {
        return updatedLedger;
    }

    public void setUpdatedLedger(LedgerDTO updatedLedger) {
        this.updatedLedger = updatedLedger;
    }

    public List<LedgerUserDTO> getNewUsers() {
        return newUsers;
    }

    public void setNewUsers(List<LedgerUserDTO> newUsers) {
        this.newUsers = newUsers;
    }
}
