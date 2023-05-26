package com.hibernate.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.hibernate.model.Alimento;

/**
 * HibernateUtil es una clase de utilidad que proporciona una única instancia
 * de la factoría de sesiones de Hibernate.
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	/**
     * Obtiene la instancia de la factoría de sesiones de Hibernate.
     *
     * @return la instancia de SessionFactory
     */
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
			Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3307/alimentacion?useSSL=false");
				settings.put(Environment.USER, "alumno");
				settings.put(Environment.PASS, "alumno");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "update");
				configuration.setProperties(settings);
				
				configuration.addAnnotatedClass(Alimento.class);
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
