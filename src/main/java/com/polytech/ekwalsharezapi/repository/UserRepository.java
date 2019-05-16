package com.polytech.ekwalsharezapi.repository;

import com.polytech.ekwalsharezapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmail(String email);

    User findByEmail(String email);

}
