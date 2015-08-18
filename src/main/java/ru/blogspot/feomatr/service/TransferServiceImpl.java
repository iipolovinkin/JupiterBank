package ru.blogspot.feomatr.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Broker;
import ru.blogspot.feomatr.entity.Transaction;

import java.math.BigDecimal;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
public class TransferServiceImpl implements TransferService {

    private AccountService accountService;
    private TransactionService transactionService;
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");

    public boolean transfer(Account accountFrom, Account accountTo, BigDecimal amount) {
        return transfer(accountFrom, accountTo, amount, new DateTime());
    }

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

    @Override
    public boolean transfer(Broker broker) {
        DateTime time;
        if (StringUtils.isEmpty(broker.getDateTime())) {
            time = new DateTime();
        } else {
            time = DateTime.parse(broker.getDateTime(), formatter);
        }
        return transfer(broker.getAccountFrom(), broker.getAccountTo(), broker.getAmount(), time);
    }

    @Override
    public boolean transferTo(Broker broker) {
        DateTime time;
        if (StringUtils.isEmpty(broker.getDateTime())) {
            time = new DateTime();
        } else {
            time = DateTime.parse(broker.getDateTime(), formatter);
        }
        return transferTo(broker.getAccountTo(), broker.getAmount(), time);
    }

    @Override
    public boolean transferFrom(Broker broker) {
        DateTime time;
        if (StringUtils.isEmpty(broker.getDateTime())) {
            time = new DateTime();
        } else {
            time = DateTime.parse(broker.getDateTime(), formatter);
        }
        return transferFrom(broker.getAccountFrom(), broker.getAmount(), time);
    }

    private boolean transferTo(Long accountTo, BigDecimal amount, DateTime dateTime) {
        return transferTo(accountService.getAccountById(accountTo), amount, dateTime);
    }

    private boolean transferFrom(Long accountFrom, BigDecimal amount, DateTime dateTime) {
        return transferFrom(accountService.getAccountById(accountFrom), amount, dateTime);
    }


    public boolean transfer(Long accountFrom, Long accountTo, BigDecimal amount, DateTime dateTime) {
        return transfer(accountService.getAccountById(accountFrom), accountService.getAccountById(accountTo), amount, dateTime);
    }

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
