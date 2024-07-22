package com.java.appParking.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "parking_space")

public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private boolean status;
    private int numberOfSpaces;

    public ParkingSpace(int id, boolean status, int numberOfSpaces) {
        this.id = id;
        this.status = status;
        this.numberOfSpaces = numberOfSpaces;
    }

    public ParkingSpace() {
    }


}
