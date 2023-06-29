package com.fdmgroup.tellermachine.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;

@Entity
@Component
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accountId;

	@Column
	private String accountName;

	@Column
	private double balance;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	public long getAccountId() {
		return accountId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account() {
		super();
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Override
	public String toString() {
		return "Account [accountName=" + accountName + ", balance=" + balance + "]";
	}

}