/**
 * 
 */
package ru.blogspot.feomatr.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.persistence.hibernate.util.HibernateUtil;

/**
 * @author iipolovinkin
 *
 */
public class InitTestData {
	void init() {
		System.out
				.println("================!!!!!!!!!!!!!!================InitTestData");
		Client clients[];
		clients = new Client[7];
		String a[] = { "New York, Yellow st, 64", "Washington, Black st, 77",
				"Minesote, White st, 12", "Dallas, Red square, 1",
				"Springfield, Simpson st, 1", "Springfield, Simpson st, 2",
				"Springfield, Simpson st, 3" };
		String nm[] = { "John", "John2", "John3", "Lisa", "Bart", "Homer",
				"Marge" };
		Integer age[] = { 21, 22, 25, 12, 14, 35, 34 };
		SessionFactory sf = HibernateUtil.getSessionFactory();
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
		init();
	}

}
