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
@Table(name = "users")
public class Users {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "firstname")
	String firstname;
	
	@Column(name = "lastname")
	String lastname;
	
	@Column(name = "mobilenumber")
	String mobilenumber;
	
	@Column(name = "whatsapp")
	String whatsapp;
	
	@Column(name = "email")
	String email;
	
	@Column(name = "gender")
	String gender;
	
	@Column(name = "dob")
	java.sql.Date dob;
	
	@Column(name = "isactive")
	boolean isactive;
	
	@Column(name = "createdatetime")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date createDateTime;
	
	@Column(name = "updatedatetime")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	Date updateDateTimeDate;
	

}
