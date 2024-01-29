package com.SignalLight.AuthenticationService.Request;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String mobileNumber;
    private int otp;
	

}
