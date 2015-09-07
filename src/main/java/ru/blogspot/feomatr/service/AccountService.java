package ru.blogspot.feomatr.service;

import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
public interface AccountService {
    Account saveAccount(Account acc) throws ServiceException;

    List<Account> getAllAccounts() throws ServiceException;

    Account getAccountById(Long id) throws ServiceException;

    List<Account> getAccountsByOwner(Client cl) throws ServiceException;

    void update(Account acc) throws ServiceException;

}
