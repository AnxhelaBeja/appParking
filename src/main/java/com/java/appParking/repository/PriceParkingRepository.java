package com.java.appParking.repository;

import com.java.appParking.model.PriceParking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceParkingRepository extends JpaRepository<PriceParking, Integer> {


    PriceParking findTopByOrderByParkingTimeDesc();
}
