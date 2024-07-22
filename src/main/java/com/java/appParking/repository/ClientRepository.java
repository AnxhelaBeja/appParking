package com.java.appParking.repository;

import com.java.appParking.model.Admin;
import com.java.appParking.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByUserUsername(String username);


}
