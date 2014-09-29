/**
 * 
 */
package ru.blogspot.feomatr.service;

import java.util.List;

import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;

/**
 * @author iipolovinkin
 *
 */
public interface AccountService {
	Account saveAccount(Account acc);

	List<Account> getAllAccounts();

	Account getAccountById(Long id);

	List<Account> getAccountsByOwner(Client cl);

}
