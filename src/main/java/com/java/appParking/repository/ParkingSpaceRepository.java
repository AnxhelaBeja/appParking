package com.java.appParking.repository;

import com.java.appParking.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Integer> {

    long countByStatusFalse();
}
