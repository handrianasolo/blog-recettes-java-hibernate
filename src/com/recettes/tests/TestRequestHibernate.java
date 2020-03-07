package com.recettes.tests;

import java.util.Date;

import com.recettes.models.Categorie;
import com.recettes.models.Commentaire;
import com.recettes.models.Membre;
import com.recettes.models.Recette;
import com.recettes.services.CategorieService;
import com.recettes.services.CommentaireService;
import com.recettes.services.MembreService;
import com.recettes.services.RecetteService;

public class TestRequestHibernate {
	
	public static void main(String[] args) throws Exception {
		
		// Test OneToMany bi-directionnelle (Recette - Commentaire)
		
		CategorieService categorieService = new CategorieService();
		Categorie categorie = new Categorie("Dessert");
		Categorie categorieCreated = categorieService.createCategorie(categorie);
		
		MembreService membreService = new MembreService();
		Membre membre = new Membre("Hanitra A.", "hanitra", "mdp123", "h@yahoo.com", new Date());
		Membre membreCreated = membreService.createMembre(membre);
		
		RecetteService recetteService = new RecetteService();
		Recette recette = new Recette("Tartiflette", "Description de la tartiflette", "tartiflette.png", new Date());
		
		recette.setCategorie(categorieCreated);
		recette.setMembre(membreCreated);
		
		Recette recetteCreated = recetteService.createRecette(recette);
		
		CommentaireService commentaireService = new CommentaireService();
		Commentaire commentaire = new Commentaire("Auteur", "Contenu", 3, new Date());
		Commentaire commentaire2 = new Commentaire("Auteur2", "Contenu2", 10, new Date());
		
		commentaire.setRecette(recetteCreated);
		commentaire2.setRecette(recetteCreated);
		
		Commentaire commentaireCreated = commentaireService.createCommentaire(commentaire);
		Commentaire commentaireCreated2 = commentaireService.createCommentaire(commentaire2);
		
		recetteCreated.addCommentaire(commentaireCreated);
		recetteCreated.addCommentaire(commentaireCreated2);
		
		System.out.println("Recette créée :");
		System.out.println(recetteCreated.toString());
		
		System.out.println("liste des commentaires de la recette créée :");
		System.out.println(recetteCreated.getCommentaires());
		
		
		
	}
}
