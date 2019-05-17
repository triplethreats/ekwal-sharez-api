package com.polytech.ekwalsharezapi.dto;

import com.polytech.ekwalsharezapi.model.LedgerUser;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

public class PaymentDTO {
    @ApiModelProperty(position = 0)
    private Long id;

    @ApiModelProperty(position = 1)
    private Long total;

    @ApiModelProperty(position = 2)
    private LedgerUserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public LedgerUserDTO getUser() {
        return user;
    }

    public void setUser(LedgerUserDTO user) {
        this.user = user;
    }
}
