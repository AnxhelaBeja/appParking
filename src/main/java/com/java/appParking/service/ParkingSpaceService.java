package com.java.appParking.service;


import com.java.appParking.model.ParkingSpace;
import com.java.appParking.repository.ParkingSpaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpaceService {

    private ParkingSpaceRepository parkingSpaceRepository;

     private List<ParkingSpace>parkingSpaces;

    public ParkingSpaceService(ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
    }
    public long getTotalParkingSpaces() {
        return parkingSpaceRepository.count();
     }
     public void addParkingSpaces(int numberOfSpaces){
        for(int i=0; i<numberOfSpaces; i++){
            ParkingSpace parkingSpace = new ParkingSpace();
            parkingSpace.setStatus(false);
            parkingSpaceRepository.save(parkingSpace);
        }
     }
    public long getAvailableParkingSpaces() {
        return parkingSpaceRepository.countByStatusFalse();
    }
}
