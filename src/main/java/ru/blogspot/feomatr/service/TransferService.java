/**
 *
 */
package ru.blogspot.feomatr.service;

import ru.blogspot.feomatr.entity.Account;

import java.math.BigDecimal;

/**
 * @author iipolovinkin
 */
public interface TransferService {


    public boolean transfer(Account accountFrom, Account accountTo, BigDecimal amount);

    public boolean transferTo(Account account, BigDecimal amount);

    public boolean transferFrom(Account account, BigDecimal amount);

}
