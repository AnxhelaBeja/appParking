package com.java.appParking.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Entity
@Table(name = "my_subscription")
public class MySubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name ="car_id")
    private Car car;
    @ManyToOne
    private Client client;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToOne
    private ParkingSpace parkingSpace;
    @Transient
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @Transient
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    public MySubscription() {
    }

    public MySubscription(int id, Car car, Client client, LocalDate startDate, LocalDate endDate, ParkingSpace parkingSpace, Admin admin, User user) {
        this.id = id;
        this.car = car;
        this.client = client;
        this.startDate = startDate;
        this.endDate = endDate;
        this.parkingSpace = parkingSpace;
        this.admin = admin;
        this.user = user;
    }
}
