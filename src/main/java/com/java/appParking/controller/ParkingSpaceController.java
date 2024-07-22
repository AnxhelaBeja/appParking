package com.java.appParking.controller;


import com.java.appParking.model.Client;
import com.java.appParking.model.ParkingSpace;
import com.java.appParking.repository.ClientRepository;
import com.java.appParking.repository.ParkingSpaceRepository;
import com.java.appParking.service.ClientService;
import com.java.appParking.service.JwtService;
import com.java.appParking.service.ParkingSpaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/parking")
 public class ParkingSpaceController {


    private final ParkingSpaceService parkingSpaceService;
    private final ParkingSpaceRepository parkingSpaceRepository;
    private final JwtService jwtService;
    private final ClientRepository clientRepository;
    private final ClientService clientService;


    public ParkingSpaceController(ParkingSpaceService parkingSpaceService, ParkingSpaceRepository parkingSpaceRepository, JwtService jwtService, ClientRepository clientRepository, ClientService clientService) {
        this.parkingSpaceService = parkingSpaceService;
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.jwtService = jwtService;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String,Long>> getTotalParkingSpaces() {
        long totalSpaces = parkingSpaceService.getTotalParkingSpaces();
        Map<String ,Long>response= new HashMap<>();
        response.put("totalSpaces",totalSpaces);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/add")
    public ResponseEntity<Void> addParkingSpaces(@RequestBody ParkingSpace request) {
        parkingSpaceService.addParkingSpaces(request.getNumberOfSpaces());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/available")
    public ResponseEntity<Map<String, Long>> getAvailableParkingSpaces() {
        long availableSpaces = parkingSpaceService.getAvailableParkingSpaces();
        Map<String, Long> response = new HashMap<>();
        response.put("availableSpaces", availableSpaces);
        return ResponseEntity.ok(response);
    }

}
