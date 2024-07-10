package com.java.appParking.controller;


import com.java.appParking.model.ParkingSpace;
import com.java.appParking.service.ParkingSpaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/parking")
 public class ParkingSpaceController {


    private final ParkingSpaceService parkingSpaceService;

    public ParkingSpaceController(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
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
