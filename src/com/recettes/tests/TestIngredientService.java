package com.recettes.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.recettes.models.Ingredient;
import com.recettes.models.Recette;
import com.recettes.services.IngredientService;
import com.recettes.services.RecetteService;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestIngredientService extends TestCase{

	private static IngredientService ingredientService = null;
	private static Ingredient ingredientTest = null;
	private static RecetteService recetteService = null;
	private static Recette recetteTest = null;
	
	
	

	public TestIngredientService(String testName) {
		super(testName);
		recetteTest = new Recette("titreTest", "descriptionTest", "photoTest", new Date());
		ingredientTest = new Ingredient("nomTest", 2.0, "unitTest", recetteTest);
	}

	@Before
	public void setUp() throws Exception {

		recetteTest = new Recette("titreTest", "descriptionTest", "photoTest", new Date());
		ingredientTest = new Ingredient("nomTest", 2.0, "unitTest", recetteTest);
		if(ingredientTest == null) {
			ingredientTest = new Ingredient("nomTest", 2.0, "unitTest", recetteTest);
		}
	}

	@After
	public void tearDown() throws Exception {
		recetteService = null;
		ingredientService = null;
	}

	@Test
	public void testCreateIngredient() throws Exception {
		//Arrange
		recetteTest = recetteService.createRecette(recetteTest);
		ingredientTest = ingredientService.createIngredient(ingredientTest);
		
		//Act
		Ingredient ingredientCreated = ingredientService.getIngredientById(ingredientTest.getId());
		
		//Assert
		assertNotEquals(ingredientCreated.getId(), ingredientTest.getId());
		assertEquals(ingredientCreated.getRecette().getId(), ingredientTest.getRecette().getId());
		assertEquals(ingredientCreated.getNom(), ingredientTest.getNom());
		assertEquals(ingredientCreated.getQuantite(), ingredientTest.getQuantite());
		assertEquals(ingredientCreated.getUnit(), ingredientTest.getUnit());
	}
	
	@Test
	public void testGetIngredientById() throws Exception {
		//Arrange
		Ingredient ingredientGetTest = null;
		
		//Act
		ingredientService.getIngredientById(ingredientTest.getId());
		ingredientGetTest = ingredientService.getIngredientById(ingredientTest.getId());
		
		//Assert
		assertEquals(ingredientGetTest.getId(), ingredientGetTest.getId());
	}
	
	@Test
	public void testGetAllIngredient() throws Exception {
		//Arrange
		List<Ingredient> ingredientTestList = new ArrayList<Ingredient>();
		ingredientTestList.add(ingredientTest);
		List<Ingredient> ingredientGetList = new ArrayList<Ingredient>();
		
		//Act
		ingredientGetList = ingredientService.getAllIngredient();
		
		//Assert
		assertEquals(ingredientGetList.size(), ingredientTestList.size()+2);
	}
	
	@Test
	public void testUpdateIngredient() throws Exception {
		//Arrange
	
		ingredientTest.setNom("testNomTest");
		ingredientTest.setQuantite(3.0);
		ingredientTest.setUnit("TestUnitTest");
		ingredientTest.setRecette(recetteTest);
		
		//Act
		ingredientService.updateIngredient(ingredientTest);
		Ingredient ingredientUpdated = ingredientService.getIngredientById(ingredientTest.getId());
		
		//Assert
		assertEquals(ingredientUpdated.getNom(), ingredientTest.getNom());
		assertEquals(ingredientUpdated.getQuantite(), ingredientTest.getQuantite());
		assertEquals(ingredientUpdated.getUnit(), ingredientTest.getUnit());
		assertEquals(ingredientUpdated.getRecette().getId(), ingredientTest.getRecette().getId());

	}
	
	@Test
	public void testDeleteIngredient() throws Exception {
		//Arrange
		
		//Act
		ingredientService.deleteIngredient(ingredientTest);
		ingredientTest = ingredientService.getIngredientById(ingredientTest.getId());
		
		//Assert
		assertNull(ingredientTest.getNom());
	}
	
	public static junit.framework.Test suite(){
		
		TestSuite suite = new TestSuite("Suite TestIngredientService");
												//nom des méthodes à virgule près
		suite.addTest(new TestIngredientService("testCreateIngredient")); 
		suite.addTest(new TestIngredientService("testGetIngredientById"));
		suite.addTest(new TestIngredientService("testGetAllIngredient"));
		suite.addTest(new TestIngredientService("testUpdateIngredient"));
		suite.addTest(new TestIngredientService("testDeleteIngredient"));
		
		return suite;
	}
}
