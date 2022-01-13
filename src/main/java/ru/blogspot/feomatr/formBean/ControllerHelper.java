package ru.blogspot.feomatr.formBean;

import com.google.common.base.Stopwatch;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.AccountNo;
import ru.blogspot.feomatr.entity.Broker;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.ClientService;
import ru.blogspot.feomatr.service.ServiceException;
import ru.blogspot.feomatr.service.TransferService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * todo write test
 */
@Setter
@NoArgsConstructor
@Service
public class ControllerHelper {
    private static final Logger log = LoggerFactory.getLogger(ControllerHelper.class);

    private ClientService clientService;
    private AccountService accountService;
    private TransferService transferService;

    public void generateThreadCATs(final int clientCount, final int accountCount, final int transferCount, int threads) {
        try {
            ExecutorService pool = Executors.newCachedThreadPool();
            //use Callable
            List<Callable<String>> tasks = new ArrayList<Callable<String>>();

            for (int i = 0; i < threads; i++) {
                final int finalI = i;
                tasks.add(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        Stopwatch stopwatch = Stopwatch.createStarted();
                        log.info("starting thread # " + finalI);
                        generateCATs(clientCount, accountCount, transferCount);
                        stopwatch.stop();
                        log.info("stopping thread # {}, time: {}", finalI, stopwatch);
                        return null;
                    }
                }); //prepare tasks
            }

            pool.invokeAll(tasks); // run, wait and get answers
            pool.shutdown();
        } catch (InterruptedException e) {
            log.error("", e);

        }
    }

    public void generateCATs(int clientCount, int accountCount, int transferCount) {
        try {
            createClients(clientCount);
            createAccounts(clientCount, accountCount);
            createTransfers(accountCount, transferCount);
        } catch (ServiceException e) {
            log.error("Operation failed", e);
            UIUtils.showErrorMessage("Operation failed", e);
        }
    }

    public void createClients(int clientCount) throws ServiceException {
        Random random = new Random();
        for (int i = 0; i < clientCount; i++) {
            Client client = new Client("Name", "Address Bingo st. ", 20 + random.nextInt(40));
            clientService.saveClient(client);
        }
    }

    public void createTransfers(int accountCount, int transferCount) throws ServiceException {
        Random random = new Random();
        List<Account> accounts = accountService.getAllAccounts();
        int bound = transferCount - 6;
        bound = bound < 2 ? 10 : bound;

        for (int i = 0; i < transferCount; i++) {

            int n = random.nextInt(bound);
            Broker broker = new Broker();
            BigDecimal amount = new BigDecimal(n);
            broker.setReceiverAccountNo(accounts.get((i + 1) % accountCount).getAccountNo());
            broker.setAmount(amount.multiply(new BigDecimal(n * 3)));
            transferService.transferTo(broker);
        }
    }

    public void createAccounts(int clientCount, int accountCount) throws ServiceException {
        Random random = new Random();
        int clientCountX10k = clientCount * 10000;
        List<Client> allClients = clientService.getAllClients();
        for (int i = 0; i < accountCount; i++) {
            Account account = new Account();

            int n = random.nextInt(clientCount);
            account.setOwner(allClients.get(n));
            account.setBalance(new BigDecimal(1000));
            int clientNum = clientCountX10k + i;
            account.setAccountNo(AccountNo.generatePrivateBankAccountNo());
            log.info(account.toString());
            accountService.saveAccount(account);
        }
    }
}