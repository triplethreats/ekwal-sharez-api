package com.polytech.ekwalsharezapi.repository;

import com.polytech.ekwalsharezapi.model.Payment;
import com.polytech.ekwalsharezapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findById(Long id);

}
