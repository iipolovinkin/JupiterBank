/**
 *
 */
package ru.blogspot.feomatr.service;

import ru.blogspot.feomatr.entity.Account;
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

    /**
     * Transfer amount money from accountFrom to accountTo
     *
     * @param accountFrom
     * @param accountTo
     * @param amount
     */
    @Override
    public boolean transfer(Account accountFrom, Account accountTo, BigDecimal amount) {
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
        transactionService.create(new Transaction(amount, accountFrom,
                accountTo));
        return true;

    }

    /**
     * Transfer amount money to account
     *
     * @param account
     * @param amount
     */
    @Override
    public boolean transferTo(Account account, BigDecimal amount) {
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
        transactionService.create(new Transaction(amount, null, account));
        return true;
    }

    /**
     * Transfer amount money from account
     *
     * @param account
     * @param amount
     * @return
     */
    @Override
    public boolean transferFrom(Account account, BigDecimal amount) {
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
            transactionService.create(new Transaction(amount, account, null));
            return true;
        }
        return false;
    }

}
