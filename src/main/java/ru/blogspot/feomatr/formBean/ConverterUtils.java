package ru.blogspot.feomatr.formBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;

import java.util.HashSet;
import java.util.Set;

/**
 * @author iipolovinkin
 * @since 04.10.2015
 */
public class ConverterUtils {
    private static final Logger log = LoggerFactory.getLogger(ConverterUtils.class);

    public static Account getDetachedAccount(Account account) {
        return new Account(account.getId(), null, account.getBalance(), account.getAccountNo());
    }

    public static Client getDetachedClient(Client client) {
        Client client_ = new Client(client);

        Set<Account> accounts = new HashSet<>();
        for (Account account : client.getAccounts()) {
            Account detachedAccount = getDetachedAccount(account);
            detachedAccount.setOwner(client_);
            accounts.add(detachedAccount);
        }
        client_.setAccounts(accounts);
        return client_;
    }

}
