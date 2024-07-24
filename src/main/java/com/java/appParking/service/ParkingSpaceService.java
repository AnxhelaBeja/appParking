package com.java.appParking.service;


import com.java.appParking.model.Client;
import com.java.appParking.model.MySubscription;
import com.java.appParking.model.ParkingSpace;
import com.java.appParking.repository.ClientRepository;
import com.java.appParking.repository.MySubscriptionRepository;
import com.java.appParking.repository.ParkingSpaceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpaceService {

    private final MySubscriptionRepository mySubscriptionRepository;
    private final ClientRepository clientRepository;
    private ParkingSpaceRepository parkingSpaceRepository;
    private final MySubscriptionService mySubscriptionService;

     private List<ParkingSpace>parkingSpaces;

    public ParkingSpaceService(ParkingSpaceRepository parkingSpaceRepository, MySubscriptionRepository mySubscriptionRepository, ClientRepository clientRepository, MySubscriptionService mySubscriptionService) {
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.mySubscriptionRepository = mySubscriptionRepository;
        this.clientRepository = clientRepository;
        this.mySubscriptionService = mySubscriptionService;
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
