package com.java.appParking.service;

import com.java.appParking.model.Client;
import com.java.appParking.model.dto.UpdateDto;
import com.java.appParking.repository.ClientRepository;
import com.java.appParking.repository.MySubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService {

    private final MySubscriptionRepository mySubscriptionRepository;
    private final MySubscriptionService mySubscriptionService;
    private final ClientRepository clientRepository;

    public ClientService(MySubscriptionRepository mySubscriptionRepository, MySubscriptionService mySubscriptionService, ClientRepository clientRepository) {
        this.mySubscriptionRepository = mySubscriptionRepository;
        this.mySubscriptionService = mySubscriptionService;
        this.clientRepository = clientRepository;
    }


    public void updateClientProperties(Client client, UpdateDto updateDto) {
        if (updateDto.getFirstname() != null){
            client.setFirstName(updateDto.getFirstname());
        }
        if(updateDto.getLastname() !=null){
            client.setLastName(updateDto.getLastname());
        }
        if(updateDto.getEmail() !=null){
            client.setEmail(updateDto.getEmail());
        }
    }

}
