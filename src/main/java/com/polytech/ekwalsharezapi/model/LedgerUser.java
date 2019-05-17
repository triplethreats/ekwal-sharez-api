package com.polytech.ekwalsharezapi.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ledger_user")
public class LedgerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nom;

    @ManyToOne
    @JoinColumn(name = "ledger_id")
    private Ledger ledger;

    @ManyToMany
    private List<User> user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Ledger getLedger() {
        return ledger;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }
}
