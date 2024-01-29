package com.SignalLight.AuthenticationService.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SignalLight.AuthenticationService.entity.Users;

public interface UsersDao extends JpaRepository<Users, Integer>{
	
	Optional<Users> findByMobilenumber(String Mobilenumber);
	
	@Query(nativeQuery = true,value = "{call getAllUsers()}")
    List<Users> getallusers();
	
	@Query(nativeQuery = true,value = "{call getUserByID(:id)}")
    Users getUserbyId(@Param("id") int id);
	
	

}
