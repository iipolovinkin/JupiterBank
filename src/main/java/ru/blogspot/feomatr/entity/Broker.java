/**
 * 
 */
package ru.blogspot.feomatr.entity;

/**
 * @author iipolovinkin
 *
 */
public class Broker {

	/**
	 * Transfer amount money from accountFrom to accountTo
	 * 
	 * @param accountFrom
	 * @param accountTo
	 * @param amount
	 */
	public static void transfer(Account accountFrom, Account accountTo,
			Long amount) {

		if (transferFrom(accountFrom, amount)) {
			transferTo(accountTo, amount);
		}
	}

	/**
	 * Transfer amount money to account
	 * 
	 * @param account
	 * @param amount
	 */
	public static void transferTo(Account account, Long amount) {
		if (amount < 0) {
			throw new UnsupportedOperationException(
					"amount < 0, Unsupported operation!");
		}
		account.setBalance(account.getBalance() + amount);
		return;
	}

	/**
	 * Transfer amount money from account
	 * 
	 * @param account
	 * @param amount
	 * @return
	 */
	public static boolean transferFrom(Account account, Long amount) {
		if (amount < 0) {
			throw new UnsupportedOperationException(
					"amount < 0, Unsupported operation!");
		}
		if (account.getBalance() >= amount) {
			account.setBalance(account.getBalance() - amount);
			return true;
		}
		return false;
	}
}
