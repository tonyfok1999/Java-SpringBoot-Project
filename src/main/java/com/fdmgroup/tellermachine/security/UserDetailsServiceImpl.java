package com.fdmgroup.tellermachine.security;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fdmgroup.tellermachine.model.User;
import com.fdmgroup.tellermachine.repository.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> userOptional = userRepo.findByUsername(username);
		
		User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		
		logger.info("username " + username + " login successfully");
		return new UserPrincipal(user);
	}

}
