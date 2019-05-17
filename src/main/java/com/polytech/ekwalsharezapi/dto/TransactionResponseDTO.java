package com.polytech.ekwalsharezapi.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class TransactionResponseDTO {

    @ApiModelProperty(position = 0)
    private Long id;

    @ApiModelProperty(position = 1)
    private Long total;

    @ApiModelProperty(position = 2)
    private Date date;

    @ApiModelProperty(position = 3)
    private String name;

    @ApiModelProperty(position = 4)
    private List<PaymentResponseDTO> payments;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PaymentResponseDTO> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentResponseDTO> payments) {
        this.payments = payments;
    }
}
