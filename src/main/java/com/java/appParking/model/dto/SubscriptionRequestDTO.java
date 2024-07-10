package com.java.appParking.model.dto;

import java.time.LocalDate;

public class SubscriptionRequestDTO {
    private int carId;
    private int clientId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int parkingSpaceId;

    public SubscriptionRequestDTO(int carId, int clientId, LocalDate startDate, LocalDate endDate, int parkingSpaceId) {
        this.carId = carId;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.parkingSpaceId = parkingSpaceId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(int parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }
}
