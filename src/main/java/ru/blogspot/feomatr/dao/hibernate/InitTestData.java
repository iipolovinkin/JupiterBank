/**
 * 
 */
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

/**
 * @author iipolovinkin
 *
 */
public class InitTestData {

	SessionFactory sf = HibernateUtil.getSessionFactory();
	Client clients[] = new Client[7];
	Account accounts[];
	Transaction transactions[];

	void initTransactions() {
		String sTime = " 13:10:30";
		String dts[] = { "2014/01/10", "2014/01/20", "2014/02/15",
				"2014/03/11", "2014/03/22", "2014/04/07", "2014/07/07" };
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd");// HH:mm:ss
		DateTime dates[] = new DateTime[7];
		for (int i = 0; i < dates.length; ++i) {
			dates[i] = formatter.parseDateTime(dts[i]);
		}
		transactions = new Transaction[7];
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		for (int i = 0; i < transactions.length; ++i) {
			transactions[i] = new Transaction(i * 10L, accounts[0 + i],
					accounts[1 + i], dates[i]);
			s.save(transactions[i]);
		}
		s.getTransaction().commit();
	}

	void initAccounts() {
		accounts = new Account[] { new Account(clients[0], 500L),
				new Account(clients[0], 140L), new Account(clients[1], 140L),
				new Account(clients[1], 140L), new Account(clients[2], 670L),
				new Account(clients[2], 1400L), new Account(clients[3], 10L),
				new Account(clients[4], 1040L) };
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		for (int i = 0; i < accounts.length; ++i) {
			s.save(accounts[i]);
		}
		s.getTransaction().commit();
	}

	void initClients() {
		System.out
				.println("================!!!!!!!!!!!!!!================InitTestData");
		clients = new Client[7];
		String a[] = { "New York, Yellow st, 64", "Washington, Black st, 77",
				"Minesote, White st, 12", "Dallas, Red square, 1",
				"Springfield, Simpson st, 1", "Springfield, Simpson st, 2",
				"Springfield, Simpson st, 3" };
		String nm[] = { "John", "John2", "John3", "Lisa", "Bart", "Homer",
				"Marge" };
		Integer age[] = { 21, 22, 25, 12, 14, 35, 34 };
		Session s = sf.getCurrentSession();
		s.beginTransaction();
		for (int i = 0; i < clients.length; ++i) {
			clients[i] = new Client(nm[i], a[i], age[i]);
			s.save(clients[i]);
		}
		s.getTransaction().commit();
		/*
		 * s.beginTransaction(); Client cc = (Client)
		 * s.get(ru.blogspot.feomatr.entity.Client.class, 1L);
		 * System.out.println(cc); s.getTransaction().commit();
		 */
		/*
		 * s.beginTransaction(); for (int i = 0; i < clients.length; ++i) { //
		 * s.save(new Account(1L * i, clients[i], i * 100L)); }
		 * s.getTransaction().commit(); s.beginTransaction(); for (int i = 0; i
		 * < age.length; i++) { // Account ac = (Account) s.get( //
		 * ru.blogspot.feomatr.entity.Account.class, i * 1L); // Client cc =
		 * (Client) // s.get(ru.blogspot.feomatr.entity.Client.class, // i *
		 * 1L); // System.out.println("Client: " + cc + " account: " + ac); }
		 * s.getTransaction().commit();
		 */
	}

	public InitTestData(HibernateUtil hibernateUtil) {
		this();
	}

	public InitTestData() {
		super();
		initClients();
		initAccounts();
		initTransactions();
	}

}
