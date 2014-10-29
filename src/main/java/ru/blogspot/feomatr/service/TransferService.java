/**
 * 
 */
package ru.blogspot.feomatr.service;

import ru.blogspot.feomatr.entity.Account;

/**
 * @author iipolovinkin
 *
 */
public interface TransferService {


	public boolean transfer(Account accountFrom, Account accountTo, Long amount);

	public boolean transferTo(Account account, Long amount);

	public boolean transferFrom(Account account, Long amount);

}
