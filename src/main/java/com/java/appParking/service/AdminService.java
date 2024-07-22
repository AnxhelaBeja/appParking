package com.java.appParking.service;

import com.java.appParking.model.Admin;
import com.java.appParking.model.dto.UpdateDto;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    public void updateAdminProperties(Admin admin , UpdateDto userUpdateDto){
        if (userUpdateDto.getFirstname() !=null){
            admin.setFirstname(userUpdateDto.getFirstname());
        }
        if(userUpdateDto.getLastname() !=null){
            admin.setLastname(userUpdateDto.getLastname());
        }
        if(userUpdateDto.getEmail() !=null){
            admin.setEmail(userUpdateDto.getEmail());}
    }
}
