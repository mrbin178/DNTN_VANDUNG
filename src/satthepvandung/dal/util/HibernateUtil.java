package satthepvandung.dal.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	private static SessionFactory buildSessionFactory() {
			Configuration configuration = new Configuration();
			configuration.configure("resources/hibernate.cfg.xml");  
//			configuration.configure();
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		}

		public static SessionFactory getSessionFactory() {
			if (sessionFactory == null){
				sessionFactory = buildSessionFactory();
			}
			return sessionFactory;
		}
}