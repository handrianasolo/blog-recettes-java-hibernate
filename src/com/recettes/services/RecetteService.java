package com.recettes.services;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.recettes.models.Recette;
import com.recettes.util.HibernateUtil;

public class RecetteService {

	public Recette createRecette(Recette recette) throws Exception {
		
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {              
			
			transaction = session.beginTransaction();      
			session.save(recette);
			session.flush();
			transaction.commit();          
			
		} catch (Exception e) {              
				
			if (transaction != null) {                  
				transaction.rollback();             
			}              
			
			e.printStackTrace();         
		}
		
		return recette;
		
	}

	public Recette getRecetteById(int id) throws Exception {
		
		Recette recette = null;         
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {              
			            
			recette = (Recette) session.get(Recette.class, id);
			
		} catch (Exception e) {                          
			
			e.printStackTrace();         
		}   
		
		return recette;
	}
	
	public void updateRecette (Recette recette) throws Exception {
		
		Transaction transaction = null;   
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction(); 
			
			if (recette != null) {                 
				session.update(recette); 
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
	
	public void deleteRecette(Recette recette) throws Exception {
		
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction(); 
			
			if (recette != null) {                 
				session.delete(recette); 
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
	public List<Recette> getAllRecette() throws Exception {
		
		List<Recette> allRecette = null;

		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			Query query = session.createQuery("from Recette");
			allRecette = (List<Recette>) query.getResultList();
	 
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		for (Recette recette : allRecette) {
			
			recette.setMoyenneNote(this.moyNoteRecetteByRecette(recette.getId()));
		}
        
		return allRecette;
	}


	@SuppressWarnings("unchecked")
	public List<Recette> getAllRecetteByCategorie(int idCategorie) throws Exception {
		
		List<Recette> allRecetteByCategorie = null;

		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			 Query query = session.createQuery("select r from Recette r join r.categorie c where c.id = :id");
			 query.setParameter("id", idCategorie);
			 allRecetteByCategorie = (List<Recette>) query.getResultList();
			 
		}catch (Exception e) {            
			
			e.printStackTrace();
		}
		
		for (Recette recette : allRecetteByCategorie) {
			
			recette.setMoyenneNote(this.moyNoteRecetteByRecette(recette.getId()));
		}
        
		return allRecetteByCategorie;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Recette> getAllRecetteByMembre(int idMembre) throws Exception {
		List<Recette> allRecetteByMembre = null; 

		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			 Query query = session.createQuery("select r from Recette r join r.membre m where m.id = :id");
			 query.setParameter("id", idMembre);
			 allRecetteByMembre = (List<Recette>) query.getResultList();
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		for (Recette recette : allRecetteByMembre) {
			
			recette.setMoyenneNote(this.moyNoteRecetteByRecette(recette.getId()));
		}
		
		return allRecetteByMembre;
	}
	
	public double moyNoteRecetteByRecette(int idRecette) throws Exception {
		double note = 0;
		
		try (Session session = HibernateUtil.getSessionFactory()
				.openSession()) {
			 String hql = "SELECT AVG(c.note) FROM Recette r "
			 		+ "JOIN r.commentaires c "
			 		+ "WHERE r.id = :id";
			 
			 Query query = session.createQuery(hql);
			 query.setParameter("id", idRecette);
			 
			 try {
				 note = (double) query.getSingleResult();
			 } catch (Exception e) {
				 note = 0;
			 }
			
			 
		 } catch (Exception e) {
	            e.printStackTrace();
	     }
	
		return note;
	}
}

