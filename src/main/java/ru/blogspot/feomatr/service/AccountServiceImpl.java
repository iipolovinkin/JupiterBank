package ru.blogspot.feomatr.service;

import com.google.common.collect.Lists;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.blogspot.feomatr.dao.AccountDAO;
import ru.blogspot.feomatr.dao.DAOException;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public Account saveAccount(Account account) throws ServiceException {
        try {
            return accountDAO.create(account);
        } catch (DAOException e) {
            log.error("Cannot create account", e);
            throw new ServiceException("Cannot create account", e);
        }
    }

    @Override
    public List<Account> getAllAccounts() throws ServiceException {
        try {
            return accountDAO.getAll();
        } catch (DAOException e) {
            log.error("ServiceException", e);
            throw new ServiceException("ServiceException", e);
        }
    }

    @Override
    public List<Account> getAllAccounts(int pageNumber, int pageSize) throws ServiceException {
        try {
            return accountDAO.getAll(pageNumber, pageSize);
        } catch (DAOException e) {
            log.error("ServiceException", e);
            throw new ServiceException("ServiceException", e);
        }
    }


    @Override
    public Account getAccountById(Long id) throws ServiceException {
        try {
            return accountDAO.getById(id);
        } catch (DAOException e) {
            log.error("ServiceException", e);
            throw new ServiceException("ServiceException", e);
        }
    }

    @Override
    public Account getAccountByNo(String accountNo) throws ServiceException {
        try {
            return accountDAO.getByNo(accountNo);
        } catch (DAOException e) {
            log.error("ServiceException", e);
            throw new ServiceException("ServiceException", e);
        }
    }

    @Override
    public List<Account> getAccountsByOwner(Client client) throws ServiceException {
        List<Account> l = Lists.newArrayList();
        List<Account> list = null;
        try {
            list = accountDAO.getAll();
        } catch (DAOException e) {
            log.error("ServiceException", e);
            throw new ServiceException("ServiceException", e);
        }
        for (Account account : list) {
            if (account.getOwner().getId().equals(client.getId())) {
                l.add(account);
            }
        }
        return l;
    }

    @Override
    public void update(Account account) throws ServiceException {
        try {
            accountDAO.update(account);
        } catch (DAOException e) {
            log.error("ServiceException", e);
            throw new ServiceException("ServiceException", e);
        }
    }

    @Override
    public void delete(Account account) throws ServiceException {
        try {
            accountDAO.delete(account);
        } catch (DAOException e) {
            log.error("ServiceException", e);
            throw new ServiceException("ServiceException", e);
        }
    }

}
