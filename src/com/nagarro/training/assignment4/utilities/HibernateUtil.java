/**
 * 
 */
package com.nagarro.training.assignment4.utilities;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.nagarro.training.assignment4.pojo.UserDetails;
import com.nagarro.training.assignment4.pojo.UserImage;

/**
 * @author hiteshgarg
 * 
 */
public class HibernateUtil {
	/**
	 * Session Factory reference
	 */
	private static SessionFactory sessionFactory;

	/**
	 * @return
	 */
	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			Configuration configuration = new Configuration().configure();
			configuration.addAnnotatedClass(UserDetails.class);
			configuration.addAnnotatedClass(UserImage.class);
			StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder();
			registry.applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry = registry.build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			// SchemaExport schema = new SchemaExport(configuration);
			// schema.create(true, true);
		}

		return sessionFactory;
	}

	/**
	 * Releases resources
	 */
	public static void closeSessionFactory() {
		if (sessionFactory != null && !sessionFactory.isClosed())
			sessionFactory.close();
	}

}
