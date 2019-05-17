package com.polytech.ekwalsharezapi.repository;

import com.polytech.ekwalsharezapi.model.LedgerUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerUserRepository extends JpaRepository<LedgerUser, Integer> {

    LedgerUser findById(Long id);

}
