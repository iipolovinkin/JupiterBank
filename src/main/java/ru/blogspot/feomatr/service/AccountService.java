/**
 *
 */
package ru.blogspot.feomatr.service;

import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
public interface AccountService {
    Account saveAccount(Account acc);

    List<Account> getAllAccounts();

    Account getAccountById(Long id);

    List<Account> getAccountsByOwner(Client cl);

    void update(Account acc);

}
