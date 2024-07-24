package com.java.appParking.controller;

import com.java.appParking.model.PriceParking;
import com.java.appParking.model.dto.CurrentPriceParking;
import com.java.appParking.service.PriceParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/priceParking")
public class PriceParkingController {

   private final  PriceParkingService priceParkingService;

     public PriceParkingController(PriceParkingService priceParkingService) {
         this.priceParkingService = priceParkingService;
     }


     @GetMapping("/current")
     public ResponseEntity<CurrentPriceParking> getCurrentPrice() {
         PriceParking currentPriceParking = priceParkingService.getCurrentPrice();
         CurrentPriceParking response = new CurrentPriceParking();
         response.setPrice(currentPriceParking.getPrice());
         response.setParkingTime(currentPriceParking.getParkingTime());
         return ResponseEntity.ok(response);
     }

    @PostMapping("/set")
     public ResponseEntity<PriceParking> setNewPrice(@RequestParam BigDecimal price,
                                                     @RequestParam LocalDate parkingTime) {
     PriceParking updatedPrice = priceParkingService.setNewPrice(price,parkingTime);
        return ResponseEntity.ok(updatedPrice);

    }

    @GetMapping("/send-price-increase-reminder")
    public ResponseEntity<Void> sendPriceIncreaseReminder() {
        priceParkingService.sendPriceIncreaseReminder();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
