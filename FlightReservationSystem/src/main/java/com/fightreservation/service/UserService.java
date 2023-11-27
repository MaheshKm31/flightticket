package com.fightreservation.service;

import com.flightreservation.entity.User;

public interface UserService {

	public User saveUser(User user);
	public String getUser(String username,String password);
	public User getUserDetails(String username);
}
