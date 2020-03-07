package com.recettes.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recettes.models.Ingredient;
import com.recettes.models.Recette;
import com.recettes.services.IngredientService;

/**
 * Servlet implementation class CreerIngredient
 */
@WebServlet(name = "CreerIngredient", urlPatterns = {"/creerIngredient"})
public class CreerIngredient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerIngredient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("id") != null) {
			int idRecette = Integer.parseInt(request.getParameter("id"));
			
			// afficher les inredients
			IngredientService ingredientService = new IngredientService();
			List<Ingredient> ingredients = ingredientService.getAllIngredientByRecette(idRecette);
			request.setAttribute("ingredients", ingredients);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/creerIngredient.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String erreur = "";
		
		double quantite = Double.parseDouble(request.getParameter("quantite"));
		
		String nom = request.getParameter("nom");
		if(nom.trim().isEmpty()) {
			erreur += "Veuillez saisir un nom valide<br>";
		}
		
		String unit = request.getParameter("unit");
		if(unit.trim().isEmpty()) {
			erreur += "Veuillez saisir une unité valide<br>";
		}
		
		// recupérer la recette passée en session
		if(session.getAttribute("recette") != null) {
			Recette recette = (Recette) session.getAttribute("recette");
			
			if(erreur.isEmpty()) {
				
				// créer les ingredients associé à la recette
				IngredientService ingredientService = new IngredientService();
				Ingredient ingredient = new Ingredient(nom, quantite, unit);
				recette.addIngredient(ingredient);
				//ingredient.setRecette(recette);
				
				Ingredient ingredientCreated = ingredientService.createIngredient(ingredient);
				request.setAttribute("ingredient", ingredientCreated);
				this.doGet(request, response);
				
			}else {
				request.setAttribute("erreur", erreur);
				this.doGet(request, response);
			}
			
		}

	}

}
