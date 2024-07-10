package com.java.appParking.service;


import com.java.appParking.model.Client;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendSubscriptionConfirmationEmail(Client client, String subscriptionDetails) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(client.getEmail());
        mailMessage.setSubject("Confirmation of monthly subscription");
        mailMessage.setText("Hello " + client.getFirstName() + ",\n\n"
                + "We have successfully confirmed your subscription. Subscription details:\n"
                + subscriptionDetails + "\n\n"
                + "Thank you for choosing our service.");

        javaMailSender.send(mailMessage);
    }
}
