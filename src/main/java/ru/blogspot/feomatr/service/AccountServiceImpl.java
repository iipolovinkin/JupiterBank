package ru.blogspot.feomatr.service;

import com.google.common.collect.Lists;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.blogspot.feomatr.dao.AccountDAO;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;

import java.util.List;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO;

    @Override
    public Account saveAccount(Account acc) {
        return accountDAO.create(acc);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountDAO.getAll();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountDAO.getAccountById(id);
    }

    @Override
    public List<Account> getAccountsByOwner(Client cl) {
        List<Account> l = Lists.newArrayList();
        List<Account> list = accountDAO.getAll();
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
