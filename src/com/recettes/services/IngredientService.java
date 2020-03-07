package com.recettes.services;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.recettes.models.Ingredient;
import com.recettes.util.HibernateUtil;

public class IngredientService {
	
	public Ingredient createIngredient(Ingredient ingredient) {
		
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {              
			
			transaction = session.beginTransaction();              
			session.save(ingredient);
			session.flush();
			transaction.commit();          
			
		} catch (Exception e) {              
				
			if (transaction != null) {                  
				transaction.rollback();             
			}              
			
			e.printStackTrace();          
		}
		
		
		return ingredient;
		
	}
	
	public Ingredient getIngredientById(int id) {
		
		Ingredient ingredient = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {              
		           
			ingredient = (Ingredient) session.get(Ingredient.class, id);      
			
		} catch (Exception e) {              
				
			e.printStackTrace();          
		}    
		
		return ingredient;
	}
	
	public void updateIngredient (Ingredient ingredient) {
		
		Transaction transaction = null;   
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction(); 
			
			if (ingredient != null) {                 
				session.update(ingredient);
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
	
	public void deleteIngredient(Ingredient ingredient) {
		
		Transaction transaction = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction(); 
			
			if (ingredient != null) {                 
				session.delete(ingredient); 
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
	public List<Ingredient> getAllIngredient(){
		
		List<Ingredient> allIngredient = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			Query query = session.createQuery("from Ingredient");
			allIngredient  = (List<Ingredient>) query.getResultList();
			
		}catch (Exception e) {              
			
			e.printStackTrace();   
		}
        
		return allIngredient;
	}
	
	@SuppressWarnings("unchecked")
	public List<Ingredient> getAllIngredientByRecette(int idRecette){
		
		List<Ingredient> allIngredientByrecette = null;

		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			
			 Query query = session.createQuery("select i from Ingredient i join i.recette r where r.id = :id");
			 query.setParameter("id", idRecette);
			 allIngredientByrecette = (List<Ingredient>) query.getResultList();
			 
		}catch (Exception e) {             
			
			e.printStackTrace();
		}
        
		return allIngredientByrecette;
	}
}
