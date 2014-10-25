package ru.blogspot.feomatr.dao;

import java.util.List;

import ru.blogspot.feomatr.entity.Account;

public interface AccountDAO {
	List<Account> getAllAccounts();

	Account getAccountById(Long id);
	
	Account create(Account acc);
	
	boolean delete(Account acc);
	
	void update(Account acc);

}
