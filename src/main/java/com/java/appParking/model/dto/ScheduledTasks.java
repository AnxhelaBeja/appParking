package com.java.appParking.model.dto;

import com.java.appParking.service.MySubscriptionService;
import com.java.appParking.service.PriceParkingService;
import jakarta.persistence.Column;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final MySubscriptionService mySubscriptionService;
    private final PriceParkingService priceParkingService;

    public ScheduledTasks(MySubscriptionService mySubscriptionService, PriceParkingService priceParkingService) {
        this.mySubscriptionService = mySubscriptionService;
        this.priceParkingService = priceParkingService;
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public  void checkSubscriptions(){
        mySubscriptionService.checkAndUpdateExpiredSubscriptions();
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkAndSendPriceIncreaseReminder() {
        priceParkingService.sendPriceIncreaseReminder();
    }
}
