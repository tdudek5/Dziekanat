package pl.edu.agh.dziekanat.persistance;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class BusinessSessionFactory {

	private SessionFactory sessionFactory;
	private static BusinessSessionFactory businessSessionFactory;

	private BusinessSessionFactory() {
	}

	public static BusinessSessionFactory getInstance(){
		if (businessSessionFactory == null)
			businessSessionFactory = new BusinessSessionFactory();
		return businessSessionFactory;
	}
	
	public SessionFactory getSession() {
		if (sessionFactory == null)
			return sessionFactory = new Configuration().configure().buildSessionFactory();
		else
			return sessionFactory;
	}
	
	public void close(){
		if (sessionFactory != null)
			sessionFactory.close();
		businessSessionFactory = null;
		sessionFactory = null;
	}

}
