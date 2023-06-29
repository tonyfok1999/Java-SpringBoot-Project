package com.fdmgroup.tellermachine.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	private static final Logger logger = LogManager.getLogger();
	
	public ResourceNotFoundException(String message) {
		super(message);
		logger.error("Required record doesn't not exist");
	}
}
