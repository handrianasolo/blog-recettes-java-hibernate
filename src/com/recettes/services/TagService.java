package com.recettes.services;

import java.util.ArrayList;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.recettes.models.Tag;
import com.recettes.util.HibernateUtil;

public class TagService {
	
	public Tag createTag(Tag tag) throws Exception{
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (tag != null) {
               session.save(tag);
               session.flush();
            }
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
            e.printStackTrace();
        }
        
        return tag;

	}

	@SuppressWarnings("unchecked")
	public ArrayList<Tag> getAllTags() throws Exception {
		ArrayList<Tag> allTags = new ArrayList<Tag>();
		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			 String hql = "from Tag";
			 Query query = session.createQuery(hql);
			 allTags = (ArrayList<Tag>) query.getResultList();
		 } catch (Exception e) {
	            e.printStackTrace();
	     }
		return allTags;

	}
	
	public void deleteTag(Tag tag) throws Exception {
		Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (tag != null) {
               session.remove(tag);
               session.flush();
            }
            transaction.commit();
        } catch (Exception e) {
        	if(transaction != null) {
        		transaction.rollback();
        	}
            e.printStackTrace();
        }

	}

	public Tag getTagById(int id) throws Exception{
	 	Tag tag = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	tag = session.get(Tag.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return tag;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Tag> getAllTagsByRecette(int idRecette) throws Exception {
		ArrayList<Tag> allTagsByRecette = new ArrayList<Tag>();
		
		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			 String hql = "select t from Tag t join t.recettes r where r.id = :id";
			 Query query = session.createQuery(hql);
			 query.setParameter("id", idRecette);
			 
			 allTagsByRecette = (ArrayList<Tag>) query.getResultList();
			 
		 } catch (Exception e) {
	            e.printStackTrace();
	     }
		 
		return allTagsByRecette;

	}
	
	public void updateTag (Tag tag) throws Exception {
		
		Transaction transaction = null;   
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction(); 
			
			if (tag != null) {                 
				session.update(tag); 
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
}
