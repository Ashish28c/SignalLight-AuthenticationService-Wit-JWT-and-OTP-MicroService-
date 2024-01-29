package com.SignalLight.AuthenticationService.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;



    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
    
    public String generateToken(String mobileNumber) {
        return jwtService.generateToken(mobileNumber);
    }
    
    




}
