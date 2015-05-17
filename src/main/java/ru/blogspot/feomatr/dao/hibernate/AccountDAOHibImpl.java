/**
 *
 */
package ru.blogspot.feomatr.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import ru.blogspot.feomatr.dao.AccountDAO;
import ru.blogspot.feomatr.entity.Account;
import ru.blogspot.feomatr.persistence.hibernate.util.HibernateUtil;

import java.util.List;

/**
 * @author iipolovinkin
 */
public class AccountDAOHibImpl implements AccountDAO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public AccountDAOHibImpl() {
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public List<Account> getAllAccounts() {
        List<Account> l;
        try {
            getCurrentSession().beginTransaction();
            l = getCurrentSession().createCriteria(Account.class).list();
        } finally {
            getCurrentSession().getTransaction().rollback();
        }
        return l;
    }

    @Override
    public Account getAccountById(Long id) {
        Account a;
        try {
            getCurrentSession().beginTransaction();
            a = (Account) getCurrentSession().get(Account.class, id);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
        return a;
    }

    @Override
    public Account create(Account account) {
        if (account == null) {
            return account;
        }
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().save(account);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
        return account;
    }

    @Override
    public boolean delete(Account account) {
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().delete(account);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
        return true;
    }

    @Override
    public void update(Account account) {
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().update(account);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
    }

}
