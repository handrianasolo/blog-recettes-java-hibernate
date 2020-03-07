package com.recettes.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.recettes.models.Categorie;
import com.recettes.util.HibernateUtil;

public class CategorieService {
	
	public CategorieService() {
		
	}
	
	public Categorie createCategorie(Categorie categorie) throws Exception {
		
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {              
			
			transaction = session.beginTransaction();              
			session.save(categorie);
			session.flush();
			transaction.commit();          
			
		} catch (Exception e) {              
				
			if (transaction != null) {                  
				transaction.rollback();             
			}              
			
			e.printStackTrace();          
		}
		
		return categorie;
		
	}
	
	public Categorie getCategorieById(int id) throws Exception {
		        
		Categorie categorie = null;          
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {              
             
			categorie = (Categorie) session.get(Categorie.class, id);         
			
		} catch (Exception e) {                          
			
			e.printStackTrace();          
		}    
		
		return categorie; 
		
	}
	
	public void updateCategorie (Categorie categorie) throws Exception {
	
		Transaction transaction = null; 
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction(); 
			
			if (categorie != null) {                 
				session.update(categorie);
				session.flush();
			}   
			
			transaction.commit();   
			
			} catch (Exception e) {   
				
				if (transaction != null) {                
					transaction.rollback();            
				}     
				
				e.printStackTrace();         
		}
		
	}
	
	public void deleteCategorie(Categorie categorie) throws Exception {
		
		Transaction transaction = null; 
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction(); 
			
			if (categorie != null) {                 
				session.delete(categorie);
				session.flush();
			}   
			
			transaction.commit();   
			
			} catch (Exception e) {   
				
				if (transaction != null) {                
					transaction.rollback();            
				}     
				
				e.printStackTrace();         
		}  
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Categorie> getAllCategorie() throws Exception {
		
		List<Categorie> allCategorie = null;
		
			try(Session session = HibernateUtil.getSessionFactory().openSession()){
				 allCategorie = (List<Categorie>) session.createQuery("from Categorie").getResultList();
				 
			}catch (Exception e) {             
				
				e.printStackTrace();   
			}
		
		 return allCategorie;
	}
	
}
