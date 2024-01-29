package com.SignalLight.AuthenticationService.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SignalLight.AuthenticationService.Request.AuthRequest;
import com.SignalLight.AuthenticationService.Service.AuthService;
import com.SignalLight.AuthenticationService.Service.OtpService;
import com.SignalLight.AuthenticationService.Service.UsersService;
import com.SignalLight.AuthenticationService.entity.ApiResponse;
import com.SignalLight.AuthenticationService.entity.OtpMaster;
import com.SignalLight.AuthenticationService.entity.Users;


@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
	
    @Autowired
    private AuthService service;
    
    @Autowired
    private OtpService otpService;
    
    @Autowired
    private UsersService usersService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/token")
    public ResponseEntity<ApiResponse> getToken(@RequestBody AuthRequest authRequest) {
        int otpValidationResult = otpService.validateOtp(authRequest.getOtp(), authRequest.getMobileNumber());
        
        if (otpValidationResult == 1) {
            OtpMaster otp = otpService.getByMobileNumber(authRequest.getMobileNumber());
            
            if (otp != null) {
            	
                String token = service.generateToken(authRequest.getMobileNumber());
                return ResponseEntity.ok().body(new ApiResponse(200,"Token generated",token));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(400,"Invalid otp",null));
            }
        } else if (otpValidationResult == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(400,"Expired otp",null));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(400,"Invalid otp",null));
        }
    }
    
//    @PostMapping("/token")
//    public ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest) {
//    	
//        int otpValidationResult = otpService.validateOtp(authRequest.getOtp(), authRequest.getMobileNumber());
//
//        if (otpValidationResult == 1) {
//            OtpMaster otp = otpService.getByMobileNumber(authRequest.getMobileNumber());
//            System.out.println("in");
//
//            if (otp != null) {
//                OtpMaster oo = otpService.getIdByotp(authRequest.getOtp());
//                int i = oo.getId();
//                System.out.println("in2");
//                // Retrieve user id
//                service.generateToken(i); // Generate token using user id
//                return ResponseEntity.ok().build();
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//            }
//        } else if (otpValidationResult == 0) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Expired OTP.");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP");
//        }
//    }

    
    
	@PostMapping("/register")
	public ResponseEntity<Users> addUser(@RequestBody Users user) {
	
		try {
			this.usersService.addUser(user); 
			return ResponseEntity.of(Optional.of(user));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

   

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
    	
        service.validateToken(token);
        return "Token is valid"; 
    }
    
    
    @PostMapping("/generateOtp")
	public ResponseEntity<ApiResponse> addUser(@RequestParam("mobileNumber") String mobileNumber) {
		
		OtpMaster m = otpService.getByMobileNumber(mobileNumber);
		
		if (otpService.validateMobilenumber(mobileNumber) == 1) {
			try {
				if (m!=null) {
					otpService.deletbyMobileNumber(mobileNumber);
					this.otpService.generateUserOtp(mobileNumber);
					return ResponseEntity.ok().body(new ApiResponse(200, "otp generated", null));
					
				}
				else {
					this.otpService.generateUserOtp(mobileNumber);
					return ResponseEntity.ok().body(new ApiResponse(200,"Mobile number varified",null));
					
				}	

			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} else if (otpService.validatePremiseMobilenumber(mobileNumber) == 1) {
			try {
				this.otpService.generateUserOtp(mobileNumber);
				return ResponseEntity.ok().body(new ApiResponse(200, "Mobilnumber varified", null));

			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}

		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(400,"mobile number not found",null));
		}

	}

@PostMapping("/validateOtp")
public ResponseEntity<String> validateOtp(@RequestParam("otp") int otp,@RequestParam("mobileNumber") String mobileNumber ) {

    int result = otpService.validateOtp(otp,mobileNumber);
    if (result == 1) {
        return ResponseEntity.ok("OTP validated successfully.");
    } else if (result == 0) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Expired OTP.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid OTP or Mobile Number.");
    }
}

}


