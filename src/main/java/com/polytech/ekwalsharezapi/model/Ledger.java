package com.polytech.ekwalsharezapi.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ledger")
public class Ledger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "ledger")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "ledger")
    private List<LedgerUser> ledgerUser;

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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<LedgerUser> getLedgerUser() {
        return ledgerUser;
    }

    public void setLedgerUser(List<LedgerUser> ledgerUser) {
        this.ledgerUser = ledgerUser;
    }
}
