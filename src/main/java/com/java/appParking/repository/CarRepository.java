package com.java.appParking.repository;

import com.java.appParking.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByCarTypeAndColor(String carType, String color);

}
