package com.recettes.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil  {   
	
	private static SessionFactory sessionFactory; 
	
	public static SessionFactory getSessionFactory() {         
		if (sessionFactory == null) {             
			try {                 
				SessionFactory sf = new Configuration().configure().buildSessionFactory();                 
				sessionFactory = sf;                 
				return sessionFactory; 
				
			} catch (Exception e) {                 
				e.printStackTrace();             
        	}         
		}         
	return sessionFactory;     
	} 
}
