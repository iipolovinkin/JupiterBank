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
    @NotNull
    @SuppressWarnings("unchecked")
    public List<Account> getAllAccounts() {
        List<Account> l;
        try {
            getCurrentSession().beginTransaction();
            l = getCurrentSession().createQuery("from Account a").list();
        } finally {
            getCurrentSession().getTransaction().rollback();
        }
        return l;
    }

    /*
     * (non-Javadoc)
     *
     * @see ru.blogspot.feomatr.dao.AccountDAO#getAccountById()
     */
    @Override
    public Account getAccountById(Long id) {
        Account a;
        try {
            getCurrentSession().beginTransaction();
            a = (Account) getCurrentSession().get(clazz, id);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
        return a;
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
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().save(acc);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
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
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().delete(acc);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
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
    public void update(Account acc) {
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().update(acc);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
    }

}
