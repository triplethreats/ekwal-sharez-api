package com.polytech.ekwalsharezapi.dto;


import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class LedgerResponseDTO {

    @ApiModelProperty(position = 0)
    private String title;
    @ApiModelProperty(position = 1)
    private String description;
    @ApiModelProperty(position = 2)
    private List<LedgerUserResponseDTO> users;

    @ApiModelProperty(position = 4)
    private Long id;

    @ApiModelProperty(position = 5)
    private List<TransactionResponseDTO> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<LedgerUserResponseDTO> getUsers() {
        return users;
    }

    public void setUsers(List<LedgerUserResponseDTO> users) {
        this.users = users;
    }

    public List<TransactionResponseDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionResponseDTO> transactions) {
        this.transactions = transactions;
    }
}
