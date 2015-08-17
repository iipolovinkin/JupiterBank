package ru.blogspot.feomatr.dao.stub;

import ru.blogspot.feomatr.dao.AccountDAO;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.service.ClientService;
import ru.blogspot.feomatr.service.ClientServiceImpl;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author iipolovinkin
 */
public class AccountDaoStubImpl implements AccountDAO {
    private ClientService clientService = new ClientServiceImpl();
    private List<Account> accounts = asList(
            new Account(1L, clientService.getClientById(1L), 100L),
            new Account(2L, clientService.getClientById(1L), 500L),
            new Account(3L, clientService.getClientById(1L), 140L),
            new Account(4L, clientService.getClientById(2L), 140L),
            new Account(5L, clientService.getClientById(2L), 140L),
            new Account(6L, clientService.getClientById(3L), 670L),
            new Account(7L, clientService.getClientById(3L), 1400L),
            new Account(8L, clientService.getClientById(3L), 10L),
            new Account(9L, clientService.getClientById(4L), 1040L));

    @Override
    public List<Account> getAll() {
        return accounts;
    }

    @Override
    public Account getAccountById(Long id) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public Account create(Account account) {
        if (account == null) {
            return account;
        }
        if (account.getId() == null) {
            account.setId((long) accounts.size());
            accounts.add(account);
            return account;
        }
        Account acc1 = getAccountById(account.getId());
        if (acc1 == null) {
            accounts.add(account);
        }
        return account;
    }

    @Override
    public boolean delete(Account account) {
        return accounts.remove(account);
    }

    @Override
    public void update(Account account) {
        Account acc1 = getAccountById(account.getId());
        if (acc1 == null) {
            return;
        }
        acc1.setId(account.getId());
        acc1.setOwner(account.getOwner());
        acc1.setBalance(account.getBalance());
    }

}
