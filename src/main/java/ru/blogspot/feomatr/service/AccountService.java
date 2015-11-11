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

    List<Account> getAllAccounts(int pageNumber, int pageSize) throws ServiceException;

    Account getAccountById(Long id) throws ServiceException;

    Account getAccountByNo(String accountNo) throws ServiceException;

    List<Account> getAccountsByOwner(Client client) throws ServiceException;

    void update(Account account) throws ServiceException;

    void delete(Account account) throws ServiceException;
}
