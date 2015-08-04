package ru.blogspot.feomatr.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.entity.Transaction;
import ru.blogspot.feomatr.persistence.hibernate.util.HibernateUtil;

import java.math.BigDecimal;

/**
 * @author iipolovinkin
 */
public final class InitTestData {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Client[] clients = new Client[7];
    private static Account[] accounts;
    private static Transaction[] transactions;

    private static final String[] addresses = {"New York, Yellow st, 64",
            "Washington, Black st, 77", "Minesote, White st, 12",
            "Dallas, Red square, 1", "Springfield, Simpson st, 1",
            "Springfield, Simpson st, 2", "Springfield, Simpson st, 3"};
    private static final String[] names = {"John", "John2", "John3", "Lisa", "Bart", "Homer", "Marge"};
    private static final Integer[] ages = {21, 22, 25, 12, 14, 35, 34};
    private static final String[] dates = {"2014/01/10", "2014/01/20", "2014/02/15", "2014/03/11",
            "2014/03/22", "2014/04/07", "2014/07/07"};

    static void initTransactions() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd");
        DateTime[] dates = new DateTime[7];
        for (int i = 0; i < dates.length; ++i) {
            dates[i] = formatter.parseDateTime(InitTestData.dates[i]);
        }
        transactions = new Transaction[7];
        Session s = sessionFactory.getCurrentSession();
        s.beginTransaction();
        for (int i = 0; i < transactions.length; ++i) {
            BigDecimal bd10 = new BigDecimal(10);
            transactions[i] = new Transaction(bd10.multiply(new BigDecimal(i)), accounts[0 + i],
                    accounts[1 + i], dates[i]);
            s.save(transactions[i]);
        }
        s.getTransaction().commit();
    }

    static void initAccounts() {
        accounts = new Account[]{new Account(clients[0], 500L),
                new Account(clients[0], 140L), new Account(clients[1], 140L),
                new Account(clients[1], 140L), new Account(clients[2], 670L),
                new Account(clients[2], 1400L), new Account(clients[3], 10L),
                new Account(clients[4], 1040L)};
        Session s = sessionFactory.getCurrentSession();
        s.beginTransaction();
        for (int i = 0; i < accounts.length; ++i) {
            s.save(accounts[i]);
        }
        s.getTransaction().commit();
    }

    static void initClients() {
        Session s = sessionFactory.getCurrentSession();
        s.beginTransaction();
        for (int i = 0; i < clients.length; ++i) {
            clients[i] = new Client(names[i], addresses[i], ages[i]);
            s.save(clients[i]);
        }
        s.getTransaction().commit();
    }

    public static void initTestData() {
        initClients();
        initAccounts();
        initTransactions();
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        InitTestData.sessionFactory = sessionFactory;
    }
}
