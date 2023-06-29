package com.fdmgroup.tellermachine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.tellermachine.model.Account;
import com.fdmgroup.tellermachine.model.User;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
	Optional<Account> findByAccountId(long accountId);
}
