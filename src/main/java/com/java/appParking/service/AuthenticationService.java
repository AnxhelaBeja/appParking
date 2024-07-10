package com.java.appParking.service;


import com.java.appParking.model.*;
import com.java.appParking.model.dto.ClientRegistrationRequest;
import com.java.appParking.model.dto.LoginRequest;
import com.java.appParking.model.dto.UserRegistrationRequest;
import com.java.appParking.repository.AdminRepository;
import com.java.appParking.repository.ClientRepository;
import com.java.appParking.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, AdminRepository adminRepository, ClientRepository clientRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager=authenticationManager;
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
    }

    public AuthenticationResponse register(UserRegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ADMIN);
        user = userRepository.save(user);

        Admin admin = new Admin();
        admin.setFirstname(request.getFirstName());
        admin.setLastname(request.getLastName());
        admin.setEmail(request.getEmail());
        admin.setUser(user);

        admin = adminRepository.save(admin);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

//    public AuthenticationResponse register(User request){
//        User user = new User();
////        user.setFirstname(request.getFirstname());
////        user.setLastname(request.getLastname());
//        user.setUsername(request.getUsername());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//
//        user.setRole(request.getRole());
//        user = userRepository.save(user);
//
//        String token = jwtService.generateToken(user);
//        return new AuthenticationResponse(token);
//
//    }

    public AuthenticationResponse authenticate(LoginRequest request) {
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       request.getUsername(),
                       request.getPassword())
       );

       User user = userRepository.findByUsername(request.getUsername()).orElse(null);
       String token = jwtService.generateToken(user);
       return new AuthenticationResponse(token);
    }
    public AuthenticationResponse registerClient(ClientRegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CLIENT);
        user = userRepository.save(user);

        Client client = new Client();
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());
        client.setEmail(request.getEmail());
        client.setUser(user);

        client = clientRepository.save(client);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

}
