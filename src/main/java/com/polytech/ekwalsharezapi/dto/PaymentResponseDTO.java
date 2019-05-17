package com.polytech.ekwalsharezapi.dto;

import io.swagger.annotations.ApiModelProperty;

public class PaymentResponseDTO {
    @ApiModelProperty(position = 0)
    private Long id;

    @ApiModelProperty(position = 1)
    private Long total;

    @ApiModelProperty(position = 2)
    private LedgerUserResponseDTO user;

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

    public LedgerUserResponseDTO getUser() {
        return user;
    }

    public void setUser(LedgerUserResponseDTO user) {
        this.user = user;
    }
}
