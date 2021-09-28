package com.hibernateutil;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;




public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			try {
		Configuration configuration = new Configuration().configure("hibernateconfig.cfg.xml");
		configuration.addAnnotatedClass(entitySample.Classes.class);
		configuration.addAnnotatedClass(entitySample.Student.class);
		configuration.addAnnotatedClass(entitySample.Subjects.class);
		configuration.addAnnotatedClass(entitySample.Teachers.class);
		
		StandardServiceRegistryBuilder builder = 
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
		
		return sessionFactory;
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
 
		return sessionFactory;
}
}
