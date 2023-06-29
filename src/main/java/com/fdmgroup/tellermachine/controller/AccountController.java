package com.fdmgroup.tellermachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fdmgroup.tellermachine.model.Account;
import com.fdmgroup.tellermachine.model.User;
import com.fdmgroup.tellermachine.security.UserPrincipal;
import com.fdmgroup.tellermachine.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@GetMapping("/new")
	public String goToCreateForm(Model model) {
		model.addAttribute("account", new Account());
		return "create_account";
	}
	
	@PostMapping("/new")
	public String saveAccount(Account account,@AuthenticationPrincipal UserPrincipal principal) {
		account.setUser(principal.getUser());
		accountService.saveAccount(account);
		return "redirect:/home";
	}
	
	@GetMapping("/edit/{id}")
	public String goToEditForm(@PathVariable Long id, Model model) {
		model.addAttribute("account", accountService.getAccountById(id));
		return "edit_account";
	}
	
	@PostMapping("/edit/{id}")
	public String goToeditForm(@PathVariable Long id, Account account) {
		accountService.updateAccount(id, account);
		return "redirect:/home";
	}
	
	@GetMapping("/{id}")
	public String deleteAccount(@PathVariable Long id) {
		accountService.deleteAccountById(id);
		return "redirect:/home";
	}
	
}
