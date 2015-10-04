package ru.blogspot.feomatr.dao.hibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Broker;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.ClientService;
import ru.blogspot.feomatr.service.ServiceException;
import ru.blogspot.feomatr.service.TransferService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * Inject test data into database before initializing.
 * Checked on H2, but not checked in case when date already exists in database.
 * if we use MySQL (not in memory DBRMS);
 *
 * @author iipolovinkin
 */
public class InitTestData {
    public static final int CLIENT_COUNT = 7;
    private static final Logger log = LoggerFactory.getLogger(InitTestData.class);
    private final String[] addresses = {"New York, Yellow st, 64",
            "Washington, Black st, 77", "Minesote, White st, 12",
            "Dallas, Red square, 1", "Springfield, Simpson st, 1",
            "Springfield, Simpson st, 2", "Springfield, Simpson st, 3"};
    private final String[] names = {"John", "John2", "John3", "Lisa", "Bart", "Homer", "Marge"};
    private final Integer[] ages = {21, 22, 25, 12, 14, 35, 34};
    private final String[] dates = {"10.01.14", "20.01.14", "15.02.14", "11.03.14", "22.03.14", "07.04.14", "07.07.14"};
    private ClientService clientService;
    private AccountService accountService;
    private TransferService transferService;

    @PostConstruct
    public void init() throws Exception {
        log.info("InitTestData initializing ");
        initClients();
        initTransactions();
    }

    private void initTransactions() throws ServiceException {
        for (int i = 0; i < CLIENT_COUNT * 2 - 1; ++i) {
            transferService.transferTo(new Broker(1l + i, 1l + i, new BigDecimal(i * 10), dates[i % 7]));
        }

        for (int i = 0; i < CLIENT_COUNT * 2 - 1; ++i) {
            transferService.transfer(new Broker(0l + i, 1l + i, new BigDecimal(i * 2), dates[i % 7]));
        }
    }

    private void initClients() throws ServiceException {
        for (int i = 0; i < CLIENT_COUNT; ++i) {
            Client client = new Client(names[i], addresses[i], ages[i]);
            clientService.saveClient(client);
            accountService.saveAccount(new Account(client));
            accountService.saveAccount(new Account(client));
            log.info("# " + i + " clients = " + client);
        }
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setTransferService(TransferService transferService) {
        this.transferService = transferService;
    }
}
