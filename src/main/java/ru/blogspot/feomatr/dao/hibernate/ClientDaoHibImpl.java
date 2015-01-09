/**
 *
 */
package ru.blogspot.feomatr.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ru.blogspot.feomatr.dao.ClientDAO;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.persistence.hibernate.util.HibernateUtil;

/**
 * @author iipolovinkin
 */
public class ClientDaoHibImpl implements ClientDAO {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public ClientDaoHibImpl() {
    }

    /*
     * (non-Javadoc)
     *
     * @see ru.blogspot.feomatr.dao.ClientDAO#getAllClients()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Client> getAllClients() {
        List<Client> l;
        try {
            getCurrentSession().beginTransaction();
            l = getCurrentSession().createQuery("from Client c")
                    .list();
        } finally {
            getCurrentSession().getTransaction().rollback();
        }
        return l;
    }

    /*
     * (non-Javadoc)
     *
     * @see ru.blogspot.feomatr.dao.ClientDAO#getById(java.lang.Long)
     */
    @Override
    public Client getById(Long id) {
        Client c;
        try {
            getCurrentSession().beginTransaction();
            c = (Client) getCurrentSession().get(
                    ru.blogspot.feomatr.entity.Client.class, id);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
        return c;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ru.blogspot.feomatr.dao.ClientDAO#create(ru.blogspot.feomatr.entity.Client
     * )
     */
    @Override
    public Client create(Client cl) {
        if (cl == null)
            return cl;
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().save(cl);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
        return cl;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ru.blogspot.feomatr.dao.ClientDAO#delete(ru.blogspot.feomatr.entity.Client
     * )
     */
    @Override
    public boolean delete(Client cl) {
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().delete(cl);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * ru.blogspot.feomatr.dao.ClientDAO#update(ru.blogspot.feomatr.entity.Client
     * )
     */
    @Override
    public int update(Client cl) {
        try {
            getCurrentSession().beginTransaction();
            getCurrentSession().update(cl);
        } finally {
            getCurrentSession().getTransaction().commit();
        }
        return 1;
    }

}
