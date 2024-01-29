package com.SignalLight.AuthenticationService.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "otpmaster")
public class OtpMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	
	@Column(name = "otp")
	int otp;
	
	@Column(name = "mobilenumber")
	String mobileNumber;
	
	@Column(name = "status")
	boolean status;
		
	@Column(name = "createdatetime")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date createDateTime;


}
