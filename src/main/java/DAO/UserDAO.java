package DAO;

import org.hibernate.Query;
import org.hibernate.Session;

import Hibernate.HibernateUtil;
import Model.User;

public class UserDAO {
	private Session session = null;

	public static UserDAO getInstance() {
		return new UserDAO();
	}

	public boolean register(User user) {
		session = HibernateUtil.getFactory().openSession();
		session.getTransaction().begin();
		try {
			session.save(user);
		} catch (Exception e) {
			return false;
		}
		session.getTransaction().commit();
		session.close();
		return true;
	}

	public User login(String username) {
		session = HibernateUtil.getFactory().openSession();
		try {
			session.beginTransaction();

			Query<User> query = session.createQuery("from User u where u.email =: email");
			query.setParameter("email", username);
			User user = (User) query.uniqueResult();
			session.getTransaction().commit();
			return user;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}
}
