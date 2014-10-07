/**
 * 
 */
package ru.blogspot.feomatr.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ru.blogspot.feomatr.dao.AccountDAO;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.persistence.hibernate.util.HibernateUtil;

/**
 * @author iipolovinkin
 *
 */
public class AccountDaoHibImpl implements AccountDAO {
	private Class<Account> clazz = Account.class;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ru.blogspot.feomatr.dao.AccountDAO#getAllAccounts()
	 */
	@Override
	public List<Account> getAllAccounts() {
		getCurrentSession().beginTransaction();
		@SuppressWarnings("unchecked")
		List<Account> l = getCurrentSession().createQuery("from Account")
				.list();
		getCurrentSession().getTransaction().rollback();
		return l;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ru.blogspot.feomatr.dao.AccountDAO#getAccountById()
	 */
	@Override
	public Account getAccountById(Long id) {
		getCurrentSession().beginTransaction();
		Account c = (Account) getCurrentSession().get(clazz, id);
		getCurrentSession().getTransaction().commit();
		return c;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.dao.AccountDAO#create(ru.blogspot.feomatr.entity.
	 * Account)
	 */
	@Override
	public Account create(Account acc) {
		if (acc == null)
			return acc;
		getCurrentSession().beginTransaction();
		getCurrentSession().save(acc);
		getCurrentSession().getTransaction().commit();
		System.out.println(acc.toString());
		return acc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.dao.AccountDAO#delete(ru.blogspot.feomatr.entity.
	 * Account)
	 */
	@Override
	public boolean delete(Account acc) {
		getCurrentSession().beginTransaction();
		getCurrentSession().delete(acc);
		getCurrentSession().getTransaction().commit();
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ru.blogspot.feomatr.dao.AccountDAO#update(ru.blogspot.feomatr.entity.
	 * Account)
	 */
	@Override
	public int update(Account acc) {
		getCurrentSession().beginTransaction();
		getCurrentSession().update(acc);
		getCurrentSession().getTransaction().commit();
		return 1;
	}

}
