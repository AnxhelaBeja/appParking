package com.java.appParking.controller;

import com.java.appParking.model.Car;
import com.java.appParking.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/car")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }
    @PostMapping("/registerCar")
    public ResponseEntity<String> registerCar(@RequestBody Car request) {
        Car car = carService.registerCar(request.getRegistrationNumber(),request.getCarType(),request.getColor());
    return ResponseEntity.ok("Registration was completed successfully");
    }
}
