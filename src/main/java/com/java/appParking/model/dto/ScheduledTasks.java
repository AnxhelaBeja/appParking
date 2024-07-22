package com.java.appParking.model.dto;

import com.java.appParking.service.MySubscriptionService;
import jakarta.persistence.Column;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final MySubscriptionService mySubscriptionService;

    public ScheduledTasks(MySubscriptionService mySubscriptionService) {
        this.mySubscriptionService = mySubscriptionService;
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public  void checkSubscriptions(){
        mySubscriptionService.checkAndUpdateExpiredSubscriptions();
    }
}
