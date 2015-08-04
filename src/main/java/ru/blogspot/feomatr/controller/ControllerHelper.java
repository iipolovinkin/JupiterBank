package ru.blogspot.feomatr.controller;

import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Broker;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.service.AccountService;
import ru.blogspot.feomatr.service.ClientService;
import ru.blogspot.feomatr.service.TransferService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class ControllerHelper {
    ClientService clientService;
    AccountService accountService;
    TransferService transferService;

    public ControllerHelper() {
    }

    void generateThreadCATs(final int clientCount, final int accountCount, final int transferCount, int threads) {
        for (int i = 0; i < threads; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("starting thread # " + finalI);
                    generateCATs(clientCount, accountCount, transferCount);
                    System.out.println("stopping thread # " + finalI);
                }
            }).start();
        }
    }


    void generateCATs(int clientCount, int accountCount, int transferCount) {
        createClients(clientCount);
        createAccounts(clientCount, accountCount);
        createTransfers(accountCount, transferCount);
    }

    void createClients(int clientCount) {
        Random random = new Random();
        for (int i = 0; i < clientCount; i++) {
            Client client = new Client("Name", "Address Bingo st. ", 20 + random.nextInt(40));
            clientService.saveClient(client);
        }
    }

    void createTransfers(int accountCount, int transferCount) {
        Random random = new Random();
        List<Account> accounts = accountService.getAllAccounts();
        for (int i = 0; i < transferCount; i++) {

            int n = random.nextInt(transferCount - 6);
            Broker broker = new Broker();
            BigDecimal amount = new BigDecimal(n);
            broker.setAccountTo(accounts.get((i + 1) % accountCount).getId());
            broker.setAmount(amount.multiply(new BigDecimal(n * 3)));
            transferService.transferTo(broker);
        }
    }

    void createAccounts(int clientCount, int accountCount) {
        Random random = new Random();
        List<Client> allClients = clientService.getAllClients();
        for (int i = 0; i < accountCount; i++) {
            Account account = new Account();

            int n = random.nextInt(clientCount);
            account.setOwner(allClients.get(n));
            account.setBalance(new BigDecimal(1000));
            accountService.saveAccount(account);
        }
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public void setTransferService(TransferService transferService) {
        this.transferService = transferService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }
}