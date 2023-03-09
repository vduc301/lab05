package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import Hibernate.HibernateUtil;
import Model.Product;

public class ProductDAO {
	private Session session = null;

	public static ProductDAO getInstance() {
		return new ProductDAO();
	}

	public List<Product> getAll() {
		session = HibernateUtil.getFactory().openSession();

		try {
			session.beginTransaction();

			List<Product> products = session.createQuery("from Product").getResultList();

			session.getTransaction().commit();
			return products;
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
		}
	}

	public boolean save(Product product) {
		session = HibernateUtil.getFactory().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(product);
			session.getTransaction().commit();
		} catch (Exception e) {
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	public void remove(int id) {
		session = HibernateUtil.getFactory().openSession();
		try {
			session.beginTransaction();
			Query<Product> query = session.createQuery("delete from Product p where p.id =: id");
			query.setParameter("id", id);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
	}
}
