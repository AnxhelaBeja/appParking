package com.java.appParking.controller;

import com.java.appParking.model.Car;
import com.java.appParking.model.Client;
import com.java.appParking.repository.CarRepository;
import com.java.appParking.repository.ClientRepository;
import com.java.appParking.service.CarService;
import com.java.appParking.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


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
        Optional<Client> clientOptional = clientRepository.findById(request.getClient().getId());

        if (!clientOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Client not found");
        }

        Car car = carService.registerCar(request.getRegistrationNumber(), request.getCarType(), request.getColor(), clientOptional.get());
        return ResponseEntity.ok("Car registration was completed successfully");
    }

    @PutMapping("/updateCar/{carId}")
    public ResponseEntity<String> updateCar(@PathVariable int carId, @RequestBody Car updatedCar) {
        carService.updateCar(carId, updatedCar);
        return ResponseEntity.ok("Car updated successfully");
    }

    @GetMapping("/{carId}")
    public ResponseEntity<Car> getCar(@PathVariable int carId) {
        Car car = carService.getCar(carId);
        return ResponseEntity.ok(car);
    }

}

