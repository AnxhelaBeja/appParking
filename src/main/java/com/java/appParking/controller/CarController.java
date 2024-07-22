package com.java.appParking.controller;

import com.java.appParking.model.Car;
import com.java.appParking.repository.CarRepository;
import com.java.appParking.repository.ClientRepository;
import com.java.appParking.service.CarService;
import com.java.appParking.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/car")
public class CarController {

    private final JwtService jwtService;
    private final CarRepository carRepository;
    private final CarService carService;
    private final ClientRepository clientRepository;

    public CarController(CarService carService, JwtService jwtService, CarRepository carRepository, ClientRepository clientRepository) {
        this.carService = carService;
        this.jwtService = jwtService;
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
    }

    @PostMapping("/registerCar")
    public ResponseEntity<String> registerCar(@RequestBody Car request) {
        Car car = carService.registerCar(request.getRegistrationNumber(), request.getCarType(), request.getColor(), request.getClient());
        return ResponseEntity.ok("Car registration was completed successfully");
    }

    @PutMapping("/updateCar/{carId}")
    public ResponseEntity<String> updateCar(@PathVariable int carId, @RequestBody Car updatedCar) {
        carService.updateCar(carId, updatedCar);
        return ResponseEntity.ok("Car updated successfully");
    }
}

