package com.polytech.ekwalsharezapi.repository;

import com.polytech.ekwalsharezapi.model.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerRepository extends JpaRepository<Ledger, Integer> {

    Ledger findById(Long id);

}
