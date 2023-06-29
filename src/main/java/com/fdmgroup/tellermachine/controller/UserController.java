package com.fdmgroup.tellermachine.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdmgroup.tellermachine.model.Account;
import com.fdmgroup.tellermachine.model.User;
import com.fdmgroup.tellermachine.security.UserPrincipal;
import com.fdmgroup.tellermachine.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	private static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String goToIndexPage() {
		return "login";
	}

	@GetMapping("/login")
	public String goToLoginPage() {
		return "login";
	}

	@ModelAttribute("user")
	public User userModel() {
		return new User();
	}

	@GetMapping("/register")
	public String goToRegisterPage() {
		return "register";
	}

	@PostMapping("/register")
	public String registerNewuser(User user, Model model) {

		if (userService.registerUser(user)) {
			return "redirect:/register?success";
		} else {
			return "redirect:/register?error";
		}
	}

	@GetMapping("/home")
	public String goToHomePage(Model model, HttpSession session, @AuthenticationPrincipal UserPrincipal principal) {

		String username = principal.getUsername();

		User user = userService.getExistingUser(username);
		List<Account> accounts = userService.retrieveAllAccounts(username);

		model.addAttribute("user", user);
		model.addAttribute("accounts", accounts);

		return "home";
	}
}
