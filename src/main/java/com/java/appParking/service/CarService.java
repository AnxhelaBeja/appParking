package com.java.appParking.service;

import com.java.appParking.model.Car;
import com.java.appParking.model.Client;
import com.java.appParking.model.dto.CryptoUtil;
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
        try {
            String encryptedRegistrationNumber = CryptoUtil.encrypt(registrationNumber);
            String encryptedCarType = CryptoUtil.encrypt(carType);
            String encryptedColor = CryptoUtil.encrypt(color);

            Car car = Car.builder()
                    .registrationNumber(encryptedRegistrationNumber)
                    .carType(encryptedCarType)
                    .color(encryptedColor)
                    .client(client)
                    .build();
            return carRepository.save(car);
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting car data", e);
        }
    }

    public void updateCar(int carId, Car updatedCar) {
        Optional<Car> existingCarOptional = carRepository.findById(carId);

        if (existingCarOptional.isPresent()) {
            Car existingCar = existingCarOptional.get();

            try {
                if (updatedCar.getRegistrationNumber() != null) {
                    existingCar.setRegistrationNumber(CryptoUtil.encrypt(updatedCar.getRegistrationNumber()));
                }
                if (updatedCar.getCarType() != null) {
                    existingCar.setCarType(CryptoUtil.encrypt(updatedCar.getCarType()));
                }
                if (updatedCar.getColor() != null) {
                    existingCar.setColor(CryptoUtil.encrypt(updatedCar.getColor()));
                }
                carRepository.save(existingCar);
            } catch (Exception e) {
                throw new RuntimeException("Error while encrypting car data", e);
            }
        } else {
            throw new RuntimeException("Car not found");
        }
    }

    public Car getCar(int carId) {
        Optional<Car> carOptional = carRepository.findById(carId);

        if (carOptional.isPresent()) {
            Car car = carOptional.get();

            try {
                String decryptedRegistrationNumber = CryptoUtil.decrypt(car.getRegistrationNumber());
                String decryptedCarType = CryptoUtil.decrypt(car.getCarType());
                String decryptedColor = CryptoUtil.decrypt(car.getColor());

                car.setRegistrationNumber(decryptedRegistrationNumber);
                car.setCarType(decryptedCarType);
                car.setColor(decryptedColor);

                return car;
            } catch (Exception e) {
                throw new RuntimeException("Error while decrypting car data", e);
            }
        } else {
            throw new RuntimeException("Car not found");
        }
    }
}
