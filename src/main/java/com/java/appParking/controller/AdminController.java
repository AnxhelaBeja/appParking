package com.java.appParking.controller;

import com.java.appParking.model.Admin;
import com.java.appParking.model.dto.UpdateDto;
import com.java.appParking.repository.AdminRepository;
import com.java.appParking.service.AdminService;
import com.java.appParking.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminRepository adminRepository;
    private final AdminService adminService;
    private final JwtService jwtService;


    public AdminController(AdminRepository adminRepository, AdminService adminService, JwtService jwtService) {
        this.adminRepository = adminRepository;
        this.adminService = adminService;
        this.jwtService = jwtService;
    }


    @PutMapping("/updateAdmin")
    public ResponseEntity<String> updateAdmin(@RequestHeader("Authorization") String authorizationHeader,
                                              @RequestBody UpdateDto updateDto) {
        String token = authorizationHeader.substring(7);
        String username = jwtService.extractUsername(token);

        Optional<Admin> adminOptional = adminRepository.findByUserUsername(username);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            adminService.updateAdminProperties(admin, updateDto);
            adminRepository.save(admin);
            return ResponseEntity.ok("Admin updated successfully");
        } else {
            return ResponseEntity.status(404).body("Admin not found");
        }
    }

    }

