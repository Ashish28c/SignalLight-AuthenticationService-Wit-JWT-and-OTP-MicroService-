package com.SignalLight.AuthenticationService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SignalLight.AuthenticationService.entity.OtpMaster;

public interface OtpDao extends JpaRepository<OtpMaster, Integer> {
	
    OtpMaster findByMobileNumber(String mobileNumber);
    void deleteByMobileNumber(String mobileNumber);
    
   OtpMaster findByOtp(int otp);

    
}
    
