package com.java.appParking.controller;


import com.java.appParking.model.Car;
import com.java.appParking.model.Client;
import com.java.appParking.model.MySubscription;
import com.java.appParking.model.ParkingSpace;
import com.java.appParking.model.dto.SubscriptionRequestDTO;
import com.java.appParking.repository.CarRepository;
import com.java.appParking.repository.ClientRepository;
import com.java.appParking.repository.ParkingSpaceRepository;
import com.java.appParking.service.ClientService;
import com.java.appParking.service.EmailService;
import com.java.appParking.service.MySubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
public class MySubscriptionController {

    private final CarRepository carRepository;
    private final MySubscriptionService mySubscriptionService;
    private final ParkingSpaceRepository parkingSpaceRepository;
    private final ClientRepository clientRepository;
    private final EmailService emailService;

    public MySubscriptionController(CarRepository carRepository, MySubscriptionService mySubscriptionService, ParkingSpaceRepository parkingSpaceRepository, ClientRepository clientRepository, EmailService emailService) {
        this.carRepository = carRepository;
        this.mySubscriptionService = mySubscriptionService;
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.clientRepository = clientRepository;
        this.emailService = emailService;
    }
    @PostMapping("/createSubscription")
    public ResponseEntity<String> createSubscription(@RequestBody SubscriptionRequestDTO requestDTO) {
        MySubscription subscription = new MySubscription();

        Car car = carRepository.findById(requestDTO.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        subscription.setCar(car);

        Client client = clientRepository.findById(requestDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        subscription.setClient(client);

        subscription.setStartDate(requestDTO.getStartDate());
        subscription.setEndDate(requestDTO.getEndDate());

        ParkingSpace parkingSpace = parkingSpaceRepository.findById(requestDTO.getParkingSpaceId())
                .orElseThrow(() -> new RuntimeException("Parking space not found"));

        subscription.setParkingSpace(parkingSpace);

        MySubscription savedSubscription = mySubscriptionService.createSubscription(subscription);
        String subscriptionDetails= "StartDate: " + subscription.getStartDate() + "\n"
                + "EndDate: " + subscription.getEndDate() + "\n"
                + "Parking Space: " + subscription.getParkingSpace().getId();
        emailService.sendSubscriptionConfirmationEmail(client,subscriptionDetails);

        return ResponseEntity.ok("Subscription created");
    }

  @GetMapping("/checkExpired")
    public ResponseEntity<String>checkExpiredSubscriptions(){
        mySubscriptionService.checkAndUpdateExpiredSubscriptions();
        return ResponseEntity.ok("Subscriptions expired");
  }
   @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteSubscription(@PathVariable int id){
        mySubscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
   }
    @GetMapping("/reminders/send")
    public String sendReminders(){
    mySubscriptionService.sendReminderEmails();
        return "Reminder emails sent successfully!";
    }
}
