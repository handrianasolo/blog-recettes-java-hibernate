package com.recettes.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.recettes.models.Categorie;
import com.recettes.models.Membre;
import com.recettes.models.Recette;
import com.recettes.services.CategorieService;
import com.recettes.services.MembreService;
import com.recettes.services.RecetteService;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestRecetteService extends TestCase{

	private static RecetteService recetteService = null;
	private static CategorieService categorieService = null;
	private static MembreService membreService = null;
	private static Recette recetteTest = null;
	private static Categorie categorieTest = null;
	private static Membre membreTest = null;
	
	
	public TestRecetteService(String testName) {
		super(testName);
		categorieTest = new Categorie("categorieTest");
		membreTest = new Membre("nomTest", "pseudoTest", "1234567", "test@email.fr", new Date());
		recetteTest = new Recette("recetteTitreTest", "recetteDescriptionTest", "recettePhotoTest", new Date(), categorieTest, membreTest);
 		
	}

	@Before
	public void setUp() throws Exception {

		recetteService = new RecetteService();
		categorieService = new CategorieService();
		membreService = new MembreService();
		if(recetteTest == null && categorieTest == null && membreTest == null) {
			categorieTest = new Categorie("categorieTest");
			membreTest = new Membre("nomTest", "pseudoTest", "1234567", "test@email.fr", new Date());
			recetteTest = new Recette("recetteTitreTest", "recetteDescriptionTest", "recettePhotoTest", new Date(), categorieTest, membreTest);
		}
	}

	@After
	public void tearDown() throws Exception {
		
		recetteService = null;
		categorieService = null;
		membreService = null;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCreateRecette() throws Exception {
		//créer un jeu de test (Arrange)
		categorieTest = categorieService.createCategorie(categorieTest);
		membreTest = membreService.createMembre(membreTest);
		recetteTest = recetteService.createRecette(recetteTest);
		
		//action (Act)
		Recette recetteCreated = recetteService.getRecetteById(recetteTest.getId());
		
		//Assert
		assertNotEquals(recetteCreated.getId(), recetteTest.getId());
		assertEquals(recetteCreated.getMembre(), recetteTest.getMembre());
		assertEquals(recetteCreated.getCategorie().getId(), recetteTest.getCategorie().getId());
		assertEquals(recetteCreated.getTitre(), recetteTest.getTitre());
		assertEquals(recetteCreated.getDescription(), recetteTest.getDescription());
		assertEquals(recetteCreated.getPhoto(), recetteTest.getPhoto());
		assertEquals(recetteCreated.getDateCreation().getDate(), recetteTest.getDateCreation().getDate());
	}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetRecetteById() throws Exception {
		//Arrange
		
		//Act
		Recette recetteFromById = recetteService.getRecetteById(recetteTest.getId());
		
		//Assert
		assertEquals(recetteFromById.getId(), recetteTest.getId());
		assertEquals(recetteFromById.getMembre().getId(), recetteTest.getMembre().getId());
		assertEquals(recetteFromById.getCategorie().getId(), recetteTest.getCategorie().getId());
		assertEquals(recetteFromById.getTitre(), recetteTest.getTitre());
		assertEquals(recetteFromById.getDescription(), recetteTest.getDescription());
		assertEquals(recetteFromById.getPhoto(), recetteTest.getPhoto());
		assertEquals(recetteFromById.getDateCreation().getDate(), recetteTest.getDateCreation().getDate());
	}
	
	@Test
	public void testGetAllRecette() throws Exception {
		//Arrange
		List<Recette> recetteTestList = new ArrayList<Recette>();
		recetteTestList.add(recetteTest);
		List<Recette> recetteGetList = new ArrayList<Recette>();
		
		//Act
		recetteGetList = recetteService.getAllRecette();
		
		//Assert
		assertEquals(recetteGetList.size(), recetteTestList.size()+2);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testUpdateRecette() throws Exception {
		
		Date newDate = new Date(2020-11-05);
		
		//Arrange
		recetteTest.setMembre(membreTest);
		recetteTest.setCategorie(categorieTest);
		recetteTest.setTitre("recetteTitreUpdated");
		recetteTest.setDescription("recetteDescritpionUpdated");
		recetteTest.setPhoto("recettePhotoUpdated");
		recetteTest.setDateCreation(newDate);
		
		//Act
		recetteService.updateRecette(recetteTest);
		Recette recetteUpdated = recetteService.getRecetteById(recetteTest.getId());
		
		//Assert
		assertEquals(recetteUpdated.getMembre().getId(), recetteTest.getMembre().getId());
		assertEquals(recetteUpdated.getCategorie().getId(), recetteTest.getCategorie().getId());
		assertEquals(recetteUpdated.getTitre(), recetteTest.getTitre());
		assertEquals(recetteUpdated.getDescription(), recetteTest.getDescription());
		assertEquals(recetteUpdated.getPhoto(), recetteTest.getPhoto());
		assertEquals(recetteUpdated.getDateCreation().getDate(), recetteTest.getDateCreation().getDate());
	}
	
	@Test
	public void testDeleteRecette() throws Exception {
		
		//Arrange
		
		//Act
		recetteService.deleteRecette(recetteTest);
		recetteTest = recetteService.getRecetteById(recetteTest.getId());
		
		//Assert
		assertNull(recetteTest.getTitre());
	}
	
	public static junit.framework.Test suite(){
		
		TestSuite suite = new TestSuite("Suite TestRecetteService");
		
											//nom des méthodes à virgule près
		suite.addTest(new TestRecetteService("testCreateRecette")); 
		suite.addTest(new TestRecetteService("testGetRecetteById"));
		suite.addTest(new TestRecetteService("testGetAllRecette"));
		suite.addTest(new TestRecetteService("testUpdateRecette"));
		suite.addTest(new TestRecetteService("testDeleteRecette"));
		
		return suite;
	}


}
