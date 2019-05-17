package com.polytech.ekwalsharezapi.repository;

import com.polytech.ekwalsharezapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction findById(Long id);

}
