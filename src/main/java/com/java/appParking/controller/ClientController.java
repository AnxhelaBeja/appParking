package com.java.appParking.controller;


import com.java.appParking.model.Client;
import com.java.appParking.model.dto.UpdateDto;
import com.java.appParking.repository.ClientRepository;
import com.java.appParking.service.ClientService;
import com.java.appParking.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final JwtService jwtService;
    private final ClientRepository clientRepository;
    private final ClientService clientService;

    public ClientController(JwtService jwtService, ClientRepository clientRepository, ClientService clientService) {
        this.jwtService = jwtService;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }


    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Endpoint is working");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateClientProperties(@RequestHeader("Authorization") String authorizationHeader,
                                                         @RequestBody UpdateDto updateDto) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUsername(token);

        Optional<Client> clientOptional = clientRepository.findByUserUsername(username);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            clientService.updateClientProperties(client, updateDto);
            clientRepository.save(client);
            return ResponseEntity.ok("Client updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


