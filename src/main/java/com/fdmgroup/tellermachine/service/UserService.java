package com.fdmgroup.tellermachine.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.tellermachine.exception.ResourceNotFoundException;
import com.fdmgroup.tellermachine.model.Account;
import com.fdmgroup.tellermachine.model.User;
import com.fdmgroup.tellermachine.repository.UserRepo;

@Service
public class UserService {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public boolean verifyUserCredentials(String username, String password) {

		Optional<User> userOptional = userRepo.findByUsername(username);
		
		if(userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
			logger.info("User " + username + " has been verified");
			return true;
		}
		
		logger.error("User " + username + " doesn't match user credential");
		return false;
	}

	public User getExistingUser(String username) {
		return userRepo.findByUsername(username)
				.orElseThrow(() ->new ResourceNotFoundException("User" + username + " not found"));
	}

	public List<User> retrieveAllUsers() {
		return userRepo.findAll();
	}
	
	public List<Account> retrieveAllAccounts(String username) {
		
		Optional<User> userOptional = userRepo.findByUsername(username);
		
		return userOptional.get().getAccounts();
	}


	public boolean registerUser(User user) {
		
		Optional<User> userOptional = userRepo.findByUsername(user.getUsername());
		
		if(userOptional.isEmpty()) {
			String pw = user.getPassword();
			String hashedPw = encoder.encode(pw);
			user.setPassword(hashedPw);
			userRepo.save(user);
			logger.info("Username " + user.getUsername() + " has been registered successfully");
			return true;
		}
		
		logger.warn("Username " + user.getUsername() + " has already existed");
		return false;
	}
	

	
	
	
	
	
}

