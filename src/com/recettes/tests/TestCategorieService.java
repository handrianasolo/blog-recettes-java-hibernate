package com.recettes.tests;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.recettes.models.Categorie;
import com.recettes.services.CategorieService;
import com.recettes.util.HibernateUtil;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

public class TestCategorieService extends TestCase {
	
	private static SessionFactory sessionFactory = null;
	private static CategorieService categorieService = null;
	private static Categorie categorieTest = null;
	
	public TestCategorieService(){
		
	}
	
	public TestCategorieService(String testName){
		super(testName);
	}
	
	@Before
	public void setUp() throws Exception {
		
		sessionFactory = HibernateUtil.getSessionFactory();
		categorieService = new CategorieService();
		
		if (categorieTest == null) {
		categorieTest = new Categorie("dessert");
		}
	}

	@After
	public void tearDown() throws Exception {
		sessionFactory.close();
		categorieService = null;
		
	}

	@Test
	public void testCreateCategorie() throws Exception {
		
		// action
		categorieTest = categorieService.createCategorie(categorieTest);
		Categorie categorieCree = categorieService.getCategorieById(categorieTest.getId());
				
		// assert
		assertNotSame(0, categorieTest.getId());
		assertEquals(categorieTest.getNom(), categorieCree.getNom());
	
	}
	
	@Test
	public void testGetCategorieFromId() throws Exception {

		// action
		
		Categorie categorieFromId = categorieService.getCategorieById(categorieTest.getId());
				
		// assert
		assertEquals(categorieFromId.getNom(), categorieTest.getNom());
		
		
	}
	
	@Test
	public void testgetAllCategories() throws Exception {

		// action
		List <Categorie> categories = categorieService.getAllCategorie();
				
		// assert
		assertEquals(categories.size(), 2);
		
	}
	
	
	@Test
	public void testUpdateCategorie() throws Exception{
		
		//Creer un jeu de tests (arrange)
		
		//membre.setId(4);
		categorieTest.setNom("nom");
		
		// action
		categorieService.updateCategorie(categorieTest);
		Categorie categorieUpdate = categorieService.getCategorieById(categorieTest.getId());
		
		// assert
		assertEquals(categorieUpdate.getNom(), categorieTest.getNom());

	}
	
	@Test
	public void testDeleteCategorie() throws Exception {
		
		//Creer un jeu de tests (arrange)
		categorieService.deleteCategorie(categorieTest);
		categorieTest = categorieService.getCategorieById(categorieTest.getId());
		
		// assert
		assertNull(categorieTest);
		
	}
	
	public static junit.framework.Test suite() {
		
		TestSuite suite = new TestSuite("Suite TestCategorieService");
		
		suite.addTest(new TestCategorieService("testCreateCategorie"));
		suite.addTest(new TestCategorieService("testGetCategorieFromId"));	
		suite.addTest(new TestCategorieService("testUpdateCategorie"));	
		suite.addTest(new TestCategorieService("testgetAllCategories"));
		suite.addTest(new TestCategorieService("testDeleteCategorie"));
		
		return suite;
	}

}
