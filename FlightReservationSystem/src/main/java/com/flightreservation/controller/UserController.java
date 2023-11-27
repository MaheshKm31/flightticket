package com.flightreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fightreservation.service.UserService;
import com.flightreservation.entity.User;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userservice;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {

		User userObj = userservice.saveUser(user);
		return new ResponseEntity<>(userObj, HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity<String> userLogin(@RequestParam("username") String username,
			@RequestParam("password") String password) {

		String message = userservice.getUser(username,password);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@GetMapping("/getUserDeatils")
	public ResponseEntity<User> userDetails(@RequestParam("username") String username) {

		User user = userservice.getUserDetails(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	} 
}
