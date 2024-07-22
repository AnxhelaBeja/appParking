package com.java.appParking.service;

import com.java.appParking.model.Car;
import com.java.appParking.model.Client;
import com.java.appParking.repository.CarRepository;
import com.java.appParking.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    public CarService(CarRepository carRepository, ClientRepository clientRepository) {
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
    }

    public Car registerCar(String registrationNumber, String carType, String color, Client client) {
        Car car = Car.builder()
                .registrationNumber(registrationNumber)
                .carType(carType)
                .color(color)
                .client(client)
                .build();
        return carRepository.save(car);
    }

    public void updateCar(int carId, Car updatedCar) {

        Optional<Car> existingCarOptional = carRepository.findById(carId);

        if (existingCarOptional.isPresent()) {
            Car existingCar = existingCarOptional.get();

            if (updatedCar.getRegistrationNumber() != null) {
                existingCar.setRegistrationNumber(updatedCar.getRegistrationNumber());
            }
            if (updatedCar.getCarType() != null) {
                existingCar.setCarType(updatedCar.getCarType());
            }
            if (updatedCar.getColor() != null) {
                existingCar.setColor(updatedCar.getColor());
            }
            carRepository.save(existingCar);
        } else {
            throw new RuntimeException("Car not found");
        }
    }}
