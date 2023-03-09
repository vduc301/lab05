package Hibernate;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import Model.Product;
import Model.User;



public class HibernateUtil {
	private static final SessionFactory FACTORY;
	static {
		Configuration conf=new Configuration();
		Properties props=new Properties();
		props.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
		props.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
		props.put(Environment.URL, "jdbc:mysql://localhost:3306/lab05");
		props.put(Environment.USER, "root");
		props.put(Environment.PASS, "");
		props.put(Environment.SHOW_SQL, "false");
		conf.setProperties(props);
		conf.addAnnotatedClass(User.class);
		conf.addAnnotatedClass(Product.class);
		ServiceRegistry registry= new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
		FACTORY=conf.buildSessionFactory(registry);
	}

	public static SessionFactory getFactory() {
		return FACTORY;
	}
}
