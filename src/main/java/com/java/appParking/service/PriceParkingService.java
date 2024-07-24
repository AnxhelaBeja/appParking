package com.java.appParking.service;

import com.java.appParking.model.Client;
import com.java.appParking.model.PriceParking;
import com.java.appParking.repository.ClientRepository;
import com.java.appParking.repository.PriceParkingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceParkingService {

    private final PriceParkingRepository priceParkingRepository;
    private final EmailService emailService;
    private final ClientRepository clientRepository;

    public PriceParkingService(PriceParkingRepository priceParkingRepository, EmailService emailService, ClientRepository clientRepository) {
        this.priceParkingRepository = priceParkingRepository;
        this.emailService = emailService;
        this.clientRepository = clientRepository;
    }

    public PriceParking getCurrentPrice(){
      return priceParkingRepository.findTopByOrderByParkingTimeDesc();
    }

    public PriceParking setNewPrice (BigDecimal price, LocalDate parkingTime){
     PriceParking currentPriceParking = getCurrentPrice();

     if (currentPriceParking != null && price.compareTo(currentPriceParking.getPrice()) > 0){
         sendPriceIncreaseNotification(price, currentPriceParking.getPrice(), parkingTime);
     }
        PriceParking priceParking = new PriceParking();
        priceParking.setPrice(price);
        priceParking.setParkingTime(parkingTime);
        return priceParkingRepository.save(priceParking);
    }

    private void sendPriceIncreaseNotification(BigDecimal newPrice , BigDecimal oldPrice, LocalDate parkingTime){
        String subject = "Parking Price Increase Reminder!";
        String body = String.format(
                "Hello ,\n \n "+
                        "We would like to inform you that the price for parking will increase from  %s në %s.\n" +
                        "The price increase will take effect on %s.\n\n " +
                        "Don't forget to renew your subscription to take advantage of the current price.\n"+
                        "Thank you,\nOur team",
                oldPrice.toString(), newPrice.toString(), parkingTime.toString());
        List<String>clientEmails= getClientEmails();
        for (String email : clientEmails){
        emailService.sendEmail(email, subject, body);
        }
    }
    private List<String> getClientEmails(){
        List<Client> clients= clientRepository.findAll();
        return clients.stream()
                .map(Client::getEmail)
                .collect(Collectors.toList());
    }
    public   void sendPriceIncreaseReminder(){
        PriceParking currentPriceParking = priceParkingRepository.findTopByOrderByParkingTimeDesc();
        if (currentPriceParking != null ){
            LocalDate today = LocalDate.now();
            if (today.equals(currentPriceParking.getParkingTime())){
                String subject = "Parking Price Increase Reminder!";
                String body = String.format(
                        "Hello ,\n\n" +
                         "We would like to inform you that the price for parking starts to increase from today.\n" +
                                "The price increase is valid from today.\n\n"+
                                "Please renew your subscription to take advantage of the current price.\n\n"+
                                "Thank You \n ,Our Team ."
                );
                List<String>clientEmails= getClientEmails();
                for (String email : clientEmails){
                    emailService.sendEmail(email, subject, body);
                }
            }
        }
    }
}
