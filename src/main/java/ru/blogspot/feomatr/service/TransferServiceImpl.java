package ru.blogspot.feomatr.service;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Broker;
import ru.blogspot.feomatr.entity.Transaction;

import java.math.BigDecimal;

/**
 * @author iipolovinkin
 */
@Setter
@NoArgsConstructor
@Service
public class TransferServiceImpl implements TransferService {
    private static final Logger log = LoggerFactory.getLogger(TransferServiceImpl.class);
    public static final String CANNOT_TRANSFER = "Cannot transfer";
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd.MM.yyyy");

    public boolean transfer(Account accountFrom, Account accountTo, BigDecimal amount) throws ServiceException {
        return transfer(accountFrom, accountTo, amount, new DateTime());
    }

    public boolean transfer(Account accountFrom, Account accountTo, BigDecimal amount, DateTime dateTime) throws ServiceException {
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

        try {
            accountService.update(accountFrom);
            accountService.update(accountTo);
            transactionService.create(new Transaction(amount, accountFrom.getAccountNo(), accountTo.getAccountNo(), dateTime));
        } catch (ServiceException e) {
            return handleException(e, CANNOT_TRANSFER);
        }

        return true;

    }

    private boolean handleException(ServiceException e, String s) throws ServiceException {
        log.error(s, e);
        throw new ServiceException(s, e);
    }

    @Override
    public boolean transfer(Broker broker) throws ServiceException {
        DateTime time;
        if (StringUtils.isEmpty(broker.getDateTime())) {
            time = new DateTime();
        } else {
            time = DateTime.parse(broker.getDateTime(), formatter);
        }
        return transfer(broker.getSenderAccountNo(), broker.getReceiverAccountNo(), broker.getAmount(), time);
    }

    @Override
    public boolean transferTo(Broker broker) throws ServiceException {
        DateTime time;
        if (StringUtils.isEmpty(broker.getDateTime())) {
            time = new DateTime();
        } else {
            time = DateTime.parse(broker.getDateTime(), formatter);
        }
        return transferTo(broker.getReceiverAccountNo(), broker.getAmount(), time);
    }

    @Override
    public boolean transferFrom(Broker broker) throws ServiceException {
        DateTime time;
        if (StringUtils.isEmpty(broker.getDateTime())) {
            time = new DateTime();
        } else {
            time = DateTime.parse(broker.getDateTime(), formatter);
        }
        return transferFrom(broker.getSenderAccountNo(), broker.getAmount(), time);
    }

    private boolean transferTo(String accountTo, BigDecimal amount, DateTime dateTime) throws ServiceException {
        try {
            return transferTo(accountService.getAccountByNo(accountTo), amount, dateTime);
        } catch (ServiceException e) {
            return handleException(e, "Cannot transferTo");
        }
    }

    private boolean transferFrom(String accountFrom, BigDecimal amount, DateTime dateTime) throws ServiceException {
        try {
            return transferFrom(accountService.getAccountByNo(accountFrom), amount, dateTime);
        } catch (ServiceException e) {
            return handleException(e, "Cannot transferFrom");
        }
    }


    public boolean transfer(String accountFrom, String accountTo, BigDecimal amount, DateTime dateTime) throws ServiceException {
        try {
            return transfer(accountService.getAccountByNo(accountFrom), accountService.getAccountByNo(accountTo), amount, dateTime);
        } catch (ServiceException e) {
            return handleException(e, CANNOT_TRANSFER);
        }
    }

    public boolean transferTo(Account accountTo, BigDecimal amount, DateTime dateTime) throws ServiceException {
        if (accountTo == null || amount == null) {
            return false;
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new UnsupportedOperationException(
                    "amount < 0, Unsupported operation!");
        }
        BigDecimal sum = accountTo.getBalance();
        sum = (sum == null ? BigDecimal.ZERO : sum);
        sum = sum.add(amount);
        accountTo.setBalance(sum);
        try {
            accountService.update(accountTo);
            transactionService.create(new Transaction(amount, null, accountTo.getAccountNo(), dateTime));
        } catch (ServiceException e) {
            handleException(e, "Cannot transferTo");
        }
        return true;
    }

    public boolean transferFrom(Account accountFrom, BigDecimal amount, DateTime dateTime) throws ServiceException {
        if (accountFrom == null || amount == null) {
            return false;
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new UnsupportedOperationException(
                    "amount < 0, Unsupported operation!");
        }
        BigDecimal sum = accountFrom.getBalance();
        sum = (sum == null ? BigDecimal.ZERO : sum);
        if (sum.compareTo(amount) >= 0) {
            accountFrom.setBalance(sum.subtract(amount));
            try {
                accountService.update(accountFrom);
                transactionService.create(new Transaction(amount, accountFrom.getAccountNo(), null, dateTime));
            } catch (ServiceException e) {
                handleException(e, CANNOT_TRANSFER);
            }
            return true;
        }
        return false;
    }

}
