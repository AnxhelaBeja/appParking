package com.java.appParking.service;

import com.java.appParking.model.PriceParking;
import com.java.appParking.repository.PriceParkingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
@Service
public class PriceParkingService {

    private final PriceParkingRepository priceParkingRepository;

    public PriceParkingService(PriceParkingRepository priceParkingRepository) {
        this.priceParkingRepository = priceParkingRepository;
    }

    public PriceParking getCurrentPrice(){
      return priceParkingRepository.findTopByOrderByParkingTimeDesc();
    }

    public PriceParking setNewPrice (BigDecimal price, LocalDate parkingTime){
     PriceParking priceParking = new PriceParking();
        priceParking.setPrice(price);
        priceParking.setParkingTime(parkingTime);
        return priceParkingRepository.save(priceParking);
    }
}
