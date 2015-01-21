/**
 *
 */
package ru.blogspot.feomatr.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.blogspot.feomatr.dao.AccountDAO;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * @author igor
 */
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDAO accountDAO;

    public AccountServiceImpl(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ru.blogspot.feomatr.service.AccountService#saveAccount(ru.blogspot.feomatr
     * .entity.Account)
     */
    @Override
    public Account saveAccount(Account acc) {
        return accountDAO.create(acc);
    }

    /*
     * (non-Javadoc)
     *
     * @see ru.blogspot.feomatr.service.AccountService#getAllAccounts()
     */
    @Override
    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ru.blogspot.feomatr.service.AccountService#getAccountById(java.lang.Long)
     */
    @Override
    public Account getAccountById(Long id) {
        return accountDAO.getAccountById(id);
    }

    @Override
    public List<Account> getAccountsByOwner(Client cl) {
        List<Account> l = new ArrayList<Account>();
        List<Account> list = accountDAO.getAllAccounts();
        for (Account account : list) {
            if (account.getOwner().getId().equals(cl.getId())) {
                l.add(account);
            }
        }
        return l;

    }

    @Override
    public void update(Account acc) {
        accountDAO.update(acc);
    }

}
