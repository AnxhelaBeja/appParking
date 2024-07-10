package com.java.appParking.service;

import com.java.appParking.model.Car;
import com.java.appParking.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car registerCar(String registrationNumber, String carType, String color) {

        Car car = Car.builder()
                .registrationNumber(registrationNumber)
                .carType(carType)
                .color(color)
                .build();

        return carRepository.save(car);
    }
}


//    public Car registerCar(String registrationNumber , String carType , String color) {
//        if (carRepository.existsByRegistrationNumber(registrationNumber)){
//            throw new RuntimeException("Car already exists");
//        }
//        Car car =Car.builder().
//        registrationNumber(registrationNumber)
//                .carType(carType)
//                        .color(color)
//                                .build();
//        return carRepository.save(car);
//    }

