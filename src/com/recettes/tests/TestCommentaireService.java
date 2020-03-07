package com.recettes.tests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.recettes.models.Commentaire;
import com.recettes.models.Recette;
import com.recettes.services.CommentaireService;
import com.recettes.services.RecetteService;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestCommentaireService extends TestCase {
	
	private static CommentaireService commentaireService = null;
	private static Commentaire commentaireTest = null;
	private static RecetteService recetteService = null;
	private static Recette recetteTest = null;
	
	
	public TestCommentaireService(String testName) {
		super(testName);
		recetteTest = new Recette("titreTest", "descriptionTest", "photoTest", new Date());
		commentaireTest = new Commentaire("auteurTest", "contenuTest", 3, new Date(), recetteTest);
	}

	@Before
	public void setUp() throws Exception {
		
		recetteTest = new Recette("titreTest", "descriptionTest", "photoTest", new Date());
		commentaireTest = new Commentaire("auteurTest", "contenuTest", 3, new Date(), recetteTest);
		if(commentaireTest == null) {
			commentaireTest = new Commentaire("auteurTest", "contenuTest", 3, new Date(), recetteTest);
		}
	}

	@After
	public void tearDown() throws Exception {
		recetteService = null;
		commentaireService = null;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCreateCommentaire() throws Exception {
		//Arrange
		recetteTest = recetteService.createRecette(recetteTest);
		commentaireTest = commentaireService.createCommentaire(commentaireTest);
		
		//Act
		Commentaire commentaireCreated = commentaireService.getCommentaireById(commentaireTest.getId());
		
		//Assert
		assertEquals(commentaireCreated.getId(), commentaireTest.getId());
		assertEquals(commentaireCreated.getAuteur(), commentaireTest.getAuteur());
		assertEquals(commentaireCreated.getContenu(), commentaireTest.getContenu());
		assertEquals(commentaireCreated.getNote(), commentaireTest.getNote());
		assertEquals(commentaireCreated.getDateCreation().getDate(), commentaireTest.getDateCreation().getDate());
		assertEquals(commentaireCreated.getRecette().getId(), commentaireTest.getRecette().getId());
		
	}
	
	@Test
	public void testGetCommentaireById() throws Exception {
		//Arrange
		
		//Act
		Commentaire commentaireGetId = commentaireService.getCommentaireById(commentaireTest.getId());
		
		//Assert
		assertEquals(commentaireGetId.getId(), commentaireTest.getId());
		assertEquals(commentaireGetId.getAuteur(), commentaireTest.getAuteur());
		assertEquals(commentaireGetId.getContenu(), commentaireTest.getContenu());
		assertEquals(commentaireGetId.getNote(), commentaireTest.getNote());
		assertEquals(commentaireGetId.getDateCreation(), commentaireTest.getDateCreation());
		assertEquals(commentaireGetId.getRecette().getId(), commentaireTest.getRecette().getId());
	}

	@Test
	public void testGetAllCommentaire() throws Exception {
		//Arrange
		List<Commentaire> commentaireTestList = new ArrayList<Commentaire>();
		commentaireTestList.add(commentaireTest);
		
		List<Commentaire> commentaireGetList = new ArrayList<Commentaire>();
		
		//Act
		commentaireGetList = commentaireService.getAllCommentaire();
		
		//Assert
		assertEquals(commentaireGetList.size(), commentaireTestList.size());
	}
	
	@Test
	public void testDeleteCommentaire() throws Exception {
		//Arrange
		
		//Act
		commentaireService.deleteCommentaire(commentaireTest);
		commentaireTest = commentaireService.getCommentaireById(commentaireTest.getId());
		
		//Assert
		assertNull(commentaireTest.getId());

	}
	
	public static junit.framework.Test suite(){
		
		TestSuite suite = new TestSuite("Suite TestCommentaireService");
		
											//nom des méthodes à virgule près
		suite.addTest(new TestCommentaireService("testCreateCommentaire"));
		suite.addTest(new TestCommentaireService("testGetCommentaireById"));
		suite.addTest(new TestCommentaireService("testGetAllCommentaire"));
		suite.addTest(new TestCommentaireService("testDeleteCommentaire"));

		
		return suite;
	}
}
