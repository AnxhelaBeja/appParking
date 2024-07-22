package com.java.appParking.repository;

import com.java.appParking.model.Client;
import com.java.appParking.model.MySubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MySubscriptionRepository extends JpaRepository<MySubscription, Integer> {


    List<MySubscription> findByEndDateBefore(LocalDate now);

    List<MySubscription> findByEndDate(LocalDate reminderDate);
}
