package com.java.appParking.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column( nullable = false)
    private String registrationNumber;
    @Column(nullable = false)
    private String carType;
    @Column(nullable = false)
    private String color;


    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Car() {
    }

    public Car(int id, String registrationNumber, String carType, String color, Client client) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.carType = carType;
        this.color = color;
        this.client = client;
    }
}
