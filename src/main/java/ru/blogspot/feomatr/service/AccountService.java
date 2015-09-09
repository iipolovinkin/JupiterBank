package ru.blogspot.feomatr.service;

import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
public interface AccountService {
    Account saveAccount(Account account) throws ServiceException;

    List<Account> getAllAccounts() throws ServiceException;

    Account getAccountById(Long id) throws ServiceException;

    List<Account> getAccountsByOwner(Client client) throws ServiceException;

    void update(Account account) throws ServiceException;

}
