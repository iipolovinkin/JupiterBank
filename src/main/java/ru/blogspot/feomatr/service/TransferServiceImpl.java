/**
 *
 */
package ru.blogspot.feomatr.service;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Broker;
import ru.blogspot.feomatr.entity.Transaction;

import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * @author iipolovinkin
 */
public class TransferServiceImpl implements TransferService {

    private AccountService accountService;
    private TransactionService transactionService;

    /**
     * @param accountService
     * @param transactionService
     */
    @Inject
    public TransferServiceImpl(AccountService accountService,
                               TransactionService transactionService) {
        super();
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    public boolean transfer(Account accountFrom, Account accountTo, BigDecimal amount) {
        return transfer(accountFrom, accountTo, amount, new DateTime());
    }

    /**
     * Transfer amount money from accountFrom to accountTo
     *
     * @param accountFrom
     * @param accountTo
     * @param amount
     */
    public boolean transfer(Account accountFrom, Account accountTo, BigDecimal amount, DateTime dateTime) {
        if (accountFrom == null || accountTo == null || amount == null) {
            return false;
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }

        BigDecimal sumFrom = accountFrom.getBalance();
        sumFrom = (sumFrom == null ? BigDecimal.ZERO : sumFrom);

        if (sumFrom.compareTo(amount) < 0) {
            return false;
        }

        BigDecimal sumTo = accountTo.getBalance();
        sumTo = (sumTo == null ? BigDecimal.ZERO : sumTo);


        accountFrom.setBalance(sumFrom.subtract(amount));
        accountTo.setBalance(sumTo.add(amount));

        accountService.update(accountFrom);
        accountService.update(accountTo);
        transactionService.create(new Transaction(amount, accountFrom, accountTo, dateTime));
        return true;

    }


    public boolean transfer(Broker broker) {
        DateTime time;
        if (StringUtils.isEmpty(broker.getDateTime())) {
            time = new DateTime();
        } else {
            time = DateTime.parse(broker.getDateTime());
        }
        return transfer(broker.getAccountFrom(), broker.getAccountTo(), broker.getAmount(), time);
    }

    @Override
    public boolean transferTo(Broker broker) {
        DateTime time;
        if (StringUtils.isEmpty(broker.getDateTime())) {
            time = new DateTime();
        } else {
            time = DateTime.parse(broker.getDateTime());
        }
        return transferTo(broker.getAccountTo(), broker.getAmount(), time);
    }

    @Override
    public boolean transferFrom(Broker broker) {
        DateTime time;
        if (StringUtils.isEmpty(broker.getDateTime())) {
            time = new DateTime();
        } else {
            time = DateTime.parse(broker.getDateTime());
        }
        return transferFrom(broker.getAccountFrom(), broker.getAmount(), time);
    }

    private boolean transferTo(Long accountTo, BigDecimal amount, DateTime dateTime) {
        return transferTo(accountService.getAccountById(accountTo), amount, dateTime);
    }

    private boolean transferFrom(Long accountFrom, BigDecimal amount, DateTime dateTime) {
        return transferTo(accountService.getAccountById(accountFrom), amount, dateTime);
    }


    public boolean transfer(Long accountFrom, Long accountTo, BigDecimal amount, DateTime dateTime) {
        return transfer(accountService.getAccountById(accountFrom), accountService.getAccountById(accountTo), amount, dateTime);
    }

    /**
     * Transfer amount money to account
     *
     * @param account
     * @param amount
     */

    public boolean transferTo(Account account, BigDecimal amount, DateTime dateTime) {
        if (account == null || amount == null) {
            return false;
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new UnsupportedOperationException(
                    "amount < 0, Unsupported operation!");
        }
        BigDecimal sum = account.getBalance();
        sum = (sum == null ? BigDecimal.ZERO : sum);
        sum = sum.add(amount);
        account.setBalance(sum);
        accountService.update(account);
        transactionService.create(new Transaction(amount, null, account, dateTime));
        return true;
    }

    /**
     * Transfer amount money from account
     *
     * @param account
     * @param amount
     * @return
     */

    public boolean transferFrom(Account account, BigDecimal amount, DateTime dateTime) {
        if (account == null || amount == null) {
            return false;
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new UnsupportedOperationException(
                    "amount < 0, Unsupported operation!");
        }
        BigDecimal sum = account.getBalance();
        sum = (sum == null ? BigDecimal.ZERO : sum);
        if (sum.compareTo(amount) >= 0) {
            account.setBalance(sum.subtract(amount));
            accountService.update(account);
            transactionService.create(new Transaction(amount, account, null, dateTime));
            return true;
        }
        return false;
    }

}
