/**
 * 
 */
package com.recettes.tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.recettes.models.Membre;
import com.recettes.services.MembreService;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @author HB
 *
 */
public class TestMembreService extends TestCase{
	
	private static MembreService membreService = null;
	private static Membre membreTest = null;
	
	
	
	public TestMembreService(String testName) {
		super(testName);
		membreTest = new Membre("nomTest", "pseudoTest", "1234567", "test@email.fr", new Date());
	}

	@Before
	public void setUp() throws Exception {
		
		membreService = new MembreService();
		if(membreTest == null) {
			membreTest = new Membre("nomTest", "pseudoTest", "12345", "email@test.fr", new Date());
		}
	}

	@After
	public void tearDown() throws Exception {
	
		membreService = null;
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCreateMembre() throws Exception {
		//créer un jeu de test (Arrange)
		//Membre membre = new Membre("TestNnom", "TestPpseudo", "TestMdp", "Test@email.fr", new Date());
		
		//action (Act)
		membreTest = membreService.createMembre(membreTest);
		Membre membreCreated = membreService.getMembreById(membreTest.getId());
		
		//Assert
		assertNotEquals(membreCreated.getId(), membreTest.getId());
		assertEquals(membreCreated.getNom(), membreTest.getNom());
		assertEquals(membreCreated.getPseudo(), membreTest.getPseudo());
		assertEquals(membreCreated.getMdp(), membreTest.getMdp());
		assertEquals(membreCreated.getEmail(), membreTest.getEmail());
		assertEquals(membreCreated.getDateInscription().getDate(), membreTest.getDateInscription().getDate());
		
	}

	
	@SuppressWarnings("deprecation")
	@Test
	public void testUpdateMembre() throws Exception {
		//créer un jeu de test (Arrange)
		Date newDate = new Date(2020-11-07);
		//Membre membre = new Membre("Hajatiana après update","Hanitra après update","abcdef après update","h@yahoo.fr après", newDate);
		//membre.setIdMembre(2);
		membreTest.setNom("membreTestUpdate");
		membreTest.setPseudo("membrePseudoUpdate");
		membreTest.setMdp("12345");
		membreTest.setEmail("membre@email.test");
		membreTest.setDateInscription(newDate);
		
		//action (Act)
		membreService.updateMembre(membreTest);
		Membre membreUpdated = membreService.getMembreById(membreTest.getId());
		
		//Assert
		assertEquals(membreUpdated.getNom(), membreTest.getNom());
		assertEquals(membreUpdated.getPseudo(), membreTest.getPseudo());
		assertEquals(membreUpdated.getMdp(), membreTest.getMdp());
		assertEquals(membreUpdated.getEmail(), membreTest.getEmail());
		assertEquals(membreUpdated.getDateInscription().getDate(), membreTest.getDateInscription().getDate());
	}

	@Test
	public void testDeleteMembre() throws Exception {
		//créer un jeu de test (Arrange)
		//int id = 4;
		//Membre membreDeleted = null; 
		
		//action (Act)
		//membreDeleted = membreService.getMembreById(id);
		membreService.deleteMembre(membreTest);
		//membreDeleted = membreService.getMembreById(id);
		membreTest = membreService.getMembreById(membreTest.getId());
		
		//Assert
		assertNull(membreTest.getNom());
		assertNull(membreTest.getPseudo());
		assertNull(membreTest.getMdp());
		assertNull(membreTest.getEmail());
		assertNull(membreTest.getDateInscription());
	}
	
	public static junit.framework.Test suite(){
		
		TestSuite suite = new TestSuite("Suite TestMembreService");
		
											//nom des méthodes à virgule près
		suite.addTest(new TestMembreService("testCreateMembre")); 
		suite.addTest(new TestMembreService("testUpdateMembre"));
		suite.addTest(new TestMembreService("testDeleteMembre"));
		
		return suite;
	}
}
