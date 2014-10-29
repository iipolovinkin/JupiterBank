/**
 * 
 */
package ru.blogspot.feomatr.service;

import javax.inject.Inject;

import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Transaction;

/**
 * @author iipolovinkin
 *
 */
public class TransferServiceImpl implements TransferService {

	private AccountService accountService;
	private TransactionService transactionService;

	/**
	 * @param accountService
	 * @param transactionService
	 */
	@Inject
	public TransferServiceImpl(AccountService accountService,
			TransactionService transactionService) {
		super();
		this.accountService = accountService;
		this.transactionService = transactionService;
	}

	/**
	 * Transfer amount money from accountFrom to accountTo
	 * 
	 * @param accountFrom
	 * @param accountTo
	 * @param amount
	 */
	@Override
	public boolean transfer(Account accountFrom, Account accountTo, Long amount) {
		if (accountFrom == null || accountTo == null || amount == null) {
			return false;
		}
		if (amount < 0) {
			return false;
		}
		if (accountFrom.getBalance() < amount) {
			return false;
		}
		accountFrom.setBalance(accountFrom.getBalance() - amount);
		accountTo.setBalance(accountTo.getBalance() + amount);
		
		accountService.update(accountFrom);
		accountService.update(accountTo);
		transactionService.create(new Transaction(amount, accountFrom,
				accountTo));
		return true;

	}

	/**
	 * Transfer amount money to account
	 * 
	 * @param account
	 * @param amount
	 */
	@Override
	public boolean transferTo(Account account, Long amount) {
		if (account == null || amount == null) {
			return false;
		}
		if (amount < 0) {
			throw new UnsupportedOperationException(
					"amount < 0, Unsupported operation!");
		}
		account.setBalance(account.getBalance() + amount);
		accountService.update(account);
		transactionService.create(new Transaction(amount, null, account));
		return true;
	}

	/**
	 * Transfer amount money from account
	 * 
	 * @param account
	 * @param amount
	 * @return
	 */
	@Override
	public boolean transferFrom(Account account, Long amount) {
		if (account == null || amount == null) {
			return false;
		}
		if (amount < 0) {
			throw new UnsupportedOperationException(
					"amount < 0, Unsupported operation!");
		}
		if (account.getBalance() >= amount) {
			account.setBalance(account.getBalance() - amount);
			accountService.update(account);
			transactionService.create(new Transaction(amount, account, null));
			return true;
		}
		return false;
	}

}
