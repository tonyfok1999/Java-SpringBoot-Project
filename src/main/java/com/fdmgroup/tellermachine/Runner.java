package com.fdmgroup.tellermachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.fdmgroup.tellermachine.repository.*;

@Component
public class Runner implements ApplicationRunner {

	@Autowired
	private UserRepo userRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
