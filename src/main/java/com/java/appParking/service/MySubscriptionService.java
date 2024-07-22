package com.java.appParking.service;


import com.java.appParking.model.Car;
import com.java.appParking.model.Client;
import com.java.appParking.model.MySubscription;
import com.java.appParking.model.ParkingSpace;
import com.java.appParking.repository.CarRepository;
import com.java.appParking.repository.ClientRepository;
import com.java.appParking.repository.MySubscriptionRepository;
import com.java.appParking.repository.ParkingSpaceRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MySubscriptionService {

    private final MySubscriptionRepository mySubscriptionRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;

    public MySubscriptionService(MySubscriptionRepository mySubscriptionRepository, CarRepository carRepository, ClientRepository clientRepository, ParkingSpaceRepository parkingSpaceRepository) {
        this.mySubscriptionRepository = mySubscriptionRepository;
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    public MySubscription createSubscription(MySubscription subscription) {
        Car car = carRepository.findById(subscription.getCar().getId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        Client client = clientRepository.findById(subscription.getClient().getId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        ParkingSpace parkingSpace = parkingSpaceRepository.findById(subscription.getParkingSpace().getId())
                .orElseThrow(() -> new RuntimeException("Parking space not found"));

        subscription.setCar(car);
        subscription.setClient(client);
        subscription.setParkingSpace(parkingSpace);
        parkingSpace.setStatus(true);
        parkingSpaceRepository.save(parkingSpace);

        return mySubscriptionRepository.save(subscription);
    }

    public void checkAndUpdateExpiredSubscriptions() {

        List<MySubscription> allSubscriptions = mySubscriptionRepository.findByEndDateBefore(LocalDate.now());

        for (MySubscription subscription : allSubscriptions) {
            ParkingSpace parkingSpace = subscription.getParkingSpace();
            parkingSpace.setStatus(false);
            parkingSpaceRepository.save(parkingSpace);
        }}

        public void deleteSubscription ( int id){
            MySubscription subscription = mySubscriptionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Subscription not found"));

            ParkingSpace parkingSpace = subscription.getParkingSpace();
            if (parkingSpace != null) {
                parkingSpace.setStatus(false);
                parkingSpaceRepository.save(parkingSpace);
            }

            mySubscriptionRepository.deleteById(id);
        }


    }