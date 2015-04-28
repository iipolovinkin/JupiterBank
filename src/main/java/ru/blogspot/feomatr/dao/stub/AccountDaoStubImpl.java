/**
 *
 */
package ru.blogspot.feomatr.dao.stub;

import ru.blogspot.feomatr.dao.AccountDAO;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.service.ClientService;
import ru.blogspot.feomatr.service.ClientServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author iipolovinkin
 */
public class AccountDaoStubImpl implements AccountDAO {
    private ClientService clientService = new ClientServiceImpl();
    private List<Account> accounts = new ArrayList<Account>(
            Arrays.asList(new Account[]{

                    new Account(1L, clientService.getClientById(1L), 100L),
                    new Account(2L, clientService.getClientById(1L), 500L),
                    new Account(3L, clientService.getClientById(1L), 140L),
                    new Account(4L, clientService.getClientById(2L), 140L),
                    new Account(5L, clientService.getClientById(2L), 140L),
                    new Account(6L, clientService.getClientById(3L), 670L),
                    new Account(7L, clientService.getClientById(3L), 1400L),
                    new Account(8L, clientService.getClientById(3L), 10L),
                    new Account(9L, clientService.getClientById(4L), 1040L)}));

    /*
     * (non-Javadoc)
     *
     * @see ru.blogspot.feomatr.dao.AccountDAO#getAllAccounts()
     */
    @Override
    public List<Account> getAllAccounts() {
        return accounts;
    }

    /*
     * (non-Javadoc)
     *
     * @see ru.blogspot.feomatr.dao.AccountDAO#getAccountById()
     */
    @Override
    public Account getAccountById(Long id) {
        for (Account account : accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ru.blogspot.feomatr.dao.AccountDAO#create(ru.blogspot.feomatr.entity.
     * Account)
     */
    @Override
    public Account create(Account acc) {
        if (acc == null) {
            return acc;
        }
        if (acc.getId() == null) {
            acc.setId((long) accounts.size());
            accounts.add(acc);
            return acc;
        }
        Account acc1 = getAccountById(acc.getId());
        if (acc1 == null) {
            accounts.add(acc);
        }
        return acc;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ru.blogspot.feomatr.dao.AccountDAO#delete(ru.blogspot.feomatr.entity.
     * Account)
     */
    @Override
    public boolean delete(Account acc) {
        return accounts.remove(acc);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ru.blogspot.feomatr.dao.AccountDAO#update(ru.blogspot.feomatr.entity.
     * Account)
     */
    @Override
    public void update(Account acc) {
        Account acc1 = getAccountById(acc.getId());
        if (acc1 == null) {
            return;
        }
        acc1.setId(acc.getId());
        acc1.setOwner(acc.getOwner());
        acc1.setBalance(acc.getBalance());
    }

}
