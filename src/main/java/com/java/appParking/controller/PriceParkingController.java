package com.java.appParking.controller;

import com.java.appParking.model.PriceParking;
import com.java.appParking.model.dto.CurrentPriceParking;
import com.java.appParking.service.PriceParkingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     public ResponseEntity<PriceParking> setNewPrice(@RequestBody PriceParking priceParking) {
     return ResponseEntity.ok(priceParkingService.setNewPrice(priceParking.getPrice(),priceParking.getParkingTime()));
    }
}
