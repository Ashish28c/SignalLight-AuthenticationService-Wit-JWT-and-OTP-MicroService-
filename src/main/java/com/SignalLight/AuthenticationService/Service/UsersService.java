package com.SignalLight.AuthenticationService.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SignalLight.AuthenticationService.Repository.UsersDao;
import com.SignalLight.AuthenticationService.entity.Users;

@Component
public class UsersService {
	
	@Autowired
	private UsersDao userDao;
	
	
	
	public List<Users> getAllUsers() {
		List<Users> list = (List<Users>) userDao.findAll();
		return list;
	}
	
	public Users addUser(Users user) {
		user.setCreateDateTime(new Date());
		user.setUpdateDateTimeDate(new Date());
		return userDao.save(user);
		
	}
	
}
