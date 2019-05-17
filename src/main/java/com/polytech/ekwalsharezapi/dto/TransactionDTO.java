package com.polytech.ekwalsharezapi.dto;


import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class TransactionDTO {

    @ApiModelProperty(position = 0)
    private Long total;

    @ApiModelProperty(position = 1)
    private Date date;

    @ApiModelProperty(position = 2)
    private String description;

    @ApiModelProperty(position = 3)
    private List<PaymentDTO> payments;

    @ApiModelProperty(position = 4)
    private Long id;

    @ApiModelProperty(position = 5)
    private String name;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PaymentDTO> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentDTO> payments) {
        this.payments = payments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
