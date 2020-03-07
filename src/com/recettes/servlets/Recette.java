package com.recettes.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recettes.models.Categorie;
import com.recettes.models.Commentaire;
import com.recettes.models.Ingredient;
import com.recettes.models.Membre;
import com.recettes.models.Tag;
import com.recettes.services.CategorieService;
import com.recettes.services.CommentaireService;
import com.recettes.services.IngredientService;
import com.recettes.services.RecetteService;
import com.recettes.services.TagService;


/**
 * Servlet implementation class Recette
 */
@WebServlet(name="Recette", urlPatterns = {"/recette"})
public class Recette extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recette() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		
		request.setCharacterEncoding("UTF-8");
		
		double noteMoyenneByRecette = 0.0;
		
		int idTag = 0;
		
		try {

			// afficher les recettes du membre connecté
			if(session.getAttribute("membre") != null) {
				
				// afficher les recettes par membre connecté
				RecetteService recetteService = new RecetteService();
				Membre membre = (Membre) session.getAttribute("membre");
				List<com.recettes.models.Recette> recettesByMembre = recetteService.getAllRecetteByMembre(membre.getId());
				request.setAttribute("recettes", recettesByMembre);
				
				// récuperer l'id de la recette passé en url
				int idRecette = Integer.parseInt(request.getParameter("id"));
				
				// afficher les categories 
				CategorieService categorieService = new CategorieService();
				List<Categorie> categories = categorieService.getAllCategorie();
				request.setAttribute("categories", categories);
				
				// afficher les commentaires
				CommentaireService commentaireService = new CommentaireService();
				List<Commentaire> commentaires = commentaireService.getAllCommentaireByRecette(idRecette);
				request.setAttribute("commentaires", commentaires);
				
				// calcul moyenne par recette
				com.recettes.models.Recette recetteById = recetteService.getRecetteById(idRecette);
				noteMoyenneByRecette = recetteService.moyNoteRecetteByRecette(idRecette);
				request.setAttribute("moyenneNote", noteMoyenneByRecette);
			
				// afficher recette par id
				recetteById.setMoyenneNote(noteMoyenneByRecette);
				request.setAttribute("recette", recetteById);
				
				// afficher les ingredients
				IngredientService ingredientService = new IngredientService();
				List<Ingredient> ingredients = ingredientService.getAllIngredientByRecette(idRecette);
				request.setAttribute("ingredients", ingredients);
				
			}else {
				
				// afficher la recette sans la session
				// récuperer l'id de la recette passé en url
				int idRecette = Integer.parseInt(request.getParameter("id"));
				
				// afficher les categories
				CategorieService categorieService = new CategorieService();
				List<Categorie> categories = categorieService.getAllCategorie();
				request.setAttribute("categories", categories);
				
				// afficher les commentaires
				CommentaireService commentaireService = new CommentaireService();
				List<Commentaire> commentaires = commentaireService.getAllCommentaireByRecette(idRecette);
				if(commentaires.isEmpty()) {
					request.setAttribute("message", "il n'y a pas de commentaires pour cette recette");
				}else {
					request.setAttribute("commentaires", commentaires);
				}
				
				// calcul moyenne par recette
				RecetteService recetteService = new RecetteService();
				noteMoyenneByRecette = recetteService.moyNoteRecetteByRecette(idRecette);
				request.setAttribute("moyenneNote", noteMoyenneByRecette);
				
				// afficher recette par id
				com.recettes.models.Recette recetteById = recetteService.getRecetteById(idRecette);
				recetteById.setMoyenneNote(noteMoyenneByRecette);
				request.setAttribute("recette", recetteById);
				
				// afficher les ingredients
				IngredientService ingredientService = new IngredientService();
				List<Ingredient> ingredients = ingredientService.getAllIngredientByRecette(idRecette);
				request.setAttribute("ingredients", ingredients);
				
				// affiche tous les tags de BDD
				TagService tagService = new TagService();
				List<Tag> tags = tagService.getAllTags();
				request.setAttribute("tags", tags);

				
				// ajouter tag à une recette
				if(request.getParameter("idTag") != null) {
					idTag = Integer.parseInt(request.getParameter("idTag"));
					
					Tag tag = tagService.getTagById(idTag);
					recetteById.getTags().add(tag);
					tag.getRecettes().add(recetteById);
					
					recetteService.updateRecette(recetteById);
					tagService.updateTag(tag);
	
				}
				
				// supprimer tag à une recette
				if(request.getParameter("idTagDel") != null) {
					idTag = Integer.parseInt(request.getParameter("idTagDel"));
					recetteById.getTags().remove(idTag);
					recetteService.updateRecette(recetteById);
				}
				
				// affiche tags par recette
				List<Tag> tagsByRecette = tagService.getAllTagsByRecette(idRecette);
				request.setAttribute("tagsByRecette", tagsByRecette);
			}
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/recette.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String erreur = "";
		
		String auteur = request.getParameter("auteur");
		if(auteur.trim().isEmpty()) {
			erreur += "Veuillez saisir un auteur valide<br>";
		}
		
		String contenu = request.getParameter("contenu");
		if(contenu.trim().isEmpty()) {
			erreur += "Veuillez saisir un commentaire valide<br>";
		}
		
		int note = Integer.parseInt(request.getParameter("note"));
		
		int idRecette = Integer.parseInt(request.getParameter("id"));
		
		if(erreur.isEmpty()) {
			try {
				
				RecetteService recetteService = new RecetteService();
				com.recettes.models.Recette recette = recetteService.getRecetteById(idRecette);
				
				// créer les commentaires associés à la recette
				CommentaireService commentaireService = new CommentaireService();
				Commentaire commentaire = new Commentaire(auteur, contenu, note, new Date());
				recette.addCommentaire(commentaire);
				
				Commentaire commentaireCreated = commentaireService.createCommentaire(commentaire);
				request.setAttribute("commentaire", commentaireCreated);
				this.doGet(request, response);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}else {
			
			request.setAttribute("erreur", erreur);
			this.doGet(request, response); 
		}
		
		
	}

}
