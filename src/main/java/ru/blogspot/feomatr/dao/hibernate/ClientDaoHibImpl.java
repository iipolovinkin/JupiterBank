/**
 * 
 */
package ru.blogspot.feomatr.dao.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ru.blogspot.feomatr.dao.ClientDAO;
import ru.blogspot.feomatr.entity.Client;
import ru.blogspot.feomatr.persistence.hibernate.util.HibernateUtil;

/**
 * @author iipolovinkin
 *
 */
public class ClientDaoHibImpl implements ClientDAO {
	List<Client> clients = new ArrayList<Client>(Arrays.asList(new Client[] {
			new Client(1L, "Alex", "NY", 22), new Client(2L, "Nick", "LA", 27),
			new Client(3L, "Ivan", "Spb", 39),
			new Client(4L, "Petr", "RnD", 13) }));

	private Class<Client> clazz = Client.class;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public ClientDaoHibImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ru.blogspot.feomatr.dao.ClientDAO#getAllClients()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAllClients() {
		getCurrentSession().beginTransaction();
		List<Client> l = getCurrentSession().createQuery("from Client c")
				.list();
		getCurrentSession().getTransaction().rollback();
		return l;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ru.blogspot.feomatr.dao.ClientDAO#getById(java.lang.Long)
	 */
	@Override
	public Client getById(Long id) {
		getCurrentSession().beginTransaction();
		System.out.println("getById(" + id + ")");
		System.out.println("class (" + clazz + ")");
		Client c = (Client) getCurrentSession().get(
				ru.blogspot.feomatr.entity.Client.class, id);
		getCurrentSession().getTransaction().commit();
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
		getCurrentSession().beginTransaction();
		getCurrentSession().save(cl);
		getCurrentSession().getTransaction().commit();
		System.out.println(cl.toString());
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
		getCurrentSession().beginTransaction();
		getCurrentSession().delete(cl);
		getCurrentSession().getTransaction().commit();
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
		getCurrentSession().beginTransaction();
		getCurrentSession().update(cl);
		getCurrentSession().getTransaction().commit();
		return 1;
	}

}
