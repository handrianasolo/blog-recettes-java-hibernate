package com.recettes.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.recettes.models.Membre;
import com.recettes.util.HibernateUtil;

public class MembreService {
	
	
	public MembreService() {
		
	}
	
	
	public Membre createMembre(Membre membre) throws Exception {
		
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {              
			
			transaction = session.beginTransaction();  
			session.save(membre);
			session.flush();
			transaction.commit();          
			
		} catch (Exception e) {              
				
			if (transaction != null) {                  
				transaction.rollback();             
			}              
			
			e.printStackTrace();          
		}
		
		return membre;
		
	}
	
	public Membre getMembreById(int id) throws Exception {
		
		Membre membre = null;         
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {                            
			membre = (Membre) session.get(Membre.class, id);       
			
		} catch (Exception e) {              

			e.printStackTrace();          
		}    
		
		return membre;
	}
	
	public void updateMembre (Membre membre) {
		
		Transaction transaction = null;   
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction(); 
			
			if (membre != null) {                 
				session.update(membre);   
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
	
	public void deleteMembre(Membre membre) throws Exception {
		
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction(); 
			
			if (membre != null) {                 
				session.delete(membre); 
				session.flush();
			}   
			
			transaction.commit();   
			
			} catch (Exception e) {   
				
				if (transaction != null) {                
					transaction.rollback();            
				}	e.printStackTrace();         
		}  
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Membre> getAllMembre() throws Exception{
		
		List<Membre> allMembre = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			Query query = session.createQuery("from Membre");
			allMembre = (List<Membre>) query.getResultList();
			 
		}catch (Exception e) {
			e.printStackTrace();   
		}
        
		return allMembre;
	}
	
	public Membre getMembreByPseudoMdp(String pseudo, String mdp) throws Exception {
		
		Membre membre = null;
		
		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			 
			 Query query = session.createQuery("select m from Membre m where m.pseudo = :pseudo and m.mdp = :mdp");
			 query.setParameter("pseudo", pseudo);
			 query.setParameter("mdp", mdp);
			 
			 @SuppressWarnings("unchecked")
			 List<Membre> membres = (ArrayList<Membre>) query.getResultList();
			 
			 if(membres.isEmpty()) {
				 membre = null;
				 
			 } else {
				 membre = membres.get(0);
			 }
			 
		 } catch (Exception e) {
	            e.printStackTrace();
	     }
		 
		return membre;
	}
}
