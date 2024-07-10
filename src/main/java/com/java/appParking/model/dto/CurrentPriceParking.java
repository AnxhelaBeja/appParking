package com.java.appParking.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrentPriceParking {

    private BigDecimal price;
    private LocalDate parkingTime;


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(LocalDate parkingTime) {
        this.parkingTime = parkingTime;
    }
}
