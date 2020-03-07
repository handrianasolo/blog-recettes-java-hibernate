package com.recettes.services;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.recettes.models.Commentaire;
import com.recettes.util.HibernateUtil;

public class CommentaireService {
	
	public Commentaire createCommentaire(Commentaire commentaire) {
		
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {              
			
			transaction = session.beginTransaction();              
			session.save(commentaire);
			session.flush();
			transaction.commit();          
			
		} catch (Exception e) {              
				
			if (transaction != null) {                  
				transaction.rollback();             
			}              
			
			e.printStackTrace();          
		}
		
		return commentaire;
	}
	
	public Commentaire getCommentaireById(int id) throws Exception{
		
		Commentaire commentaire = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {              
			       
			commentaire = (Commentaire)session.get(Commentaire.class, id);       
			
		} catch (Exception e) {                          
			
			e.printStackTrace();          
		}    
		
		return commentaire;
	}
	
	public void updateCommentaire (Commentaire commentaire) {
		
		Transaction transaction = null;   
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction(); 
			
			if (commentaire != null) {                 
				session.update(commentaire);
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
	
	public void deleteCommentaire(Commentaire commentaire) {
		Transaction transaction = null; 
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction(); 
			
			if (commentaire != null) {                 
				session.delete(commentaire);  
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
	public List<Commentaire> getAllCommentaire() throws Exception {
		
		List<Commentaire> allCommentaire = null;

			try(Session session = HibernateUtil.getSessionFactory().openSession()){
				Query query = session.createQuery("from Commentaire");
				allCommentaire = (List<Commentaire>) query.getResultList();
				 
			}catch (Exception e) {
            
				e.printStackTrace();   
			}
		
		 return allCommentaire;
	}
	
	@SuppressWarnings("unchecked")
	public List<Commentaire> getAllCommentaireByRecette(int idRecette) throws Exception{
		
		List<Commentaire> allCommentaireByRecette = null;

		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			 Query query = session.createQuery("select c from Commentaire c join c.recette r where r.id = :id");
			 query.setParameter("id", idRecette);
			 allCommentaireByRecette = (List<Commentaire>) query.getResultList();
			 
		}catch (Exception e) {              
			
			e.printStackTrace();
		}
		
        return allCommentaireByRecette;
	}
	
}
