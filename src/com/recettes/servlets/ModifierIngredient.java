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
 * Servlet implementation class ModifierIngredient
 */
@WebServlet(name="ModifierIngredient", urlPatterns = {"/modifierIngredient"})
public class ModifierIngredient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierIngredient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		Recette recette = null;
		if(session.getAttribute("recette") != null) {
			recette = (Recette) session.getAttribute("recette");
			request.setAttribute("recette", recette);
		}
		
		try {
			
			if(recette!=null) {

				IngredientService ingredientService = new IngredientService();
				List<Ingredient> ingredients = ingredientService.getAllIngredientByRecette(recette.getId());
				request.setAttribute("ingredients", ingredients);
			}
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/modifierIngredient.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String erreur = "";
		
		int idIngredient = 0;
		
		double quantite = Double.parseDouble(request.getParameter("quantite"));
		
		String nom = request.getParameter("nom");
		if(nom.trim().isEmpty()) {
			erreur += "Veuillez saisir un nom valide<br>";
		}
		
		// recupérer id ingrédient de la recette
		if(request.getParameter("id") != null) {
			idIngredient = Integer.parseInt(request.getParameter("id"));
			
			if(erreur.isEmpty()) {
				
				// modifier les ingredients associé à la recette
				IngredientService ingredientService = new IngredientService();
				
				if(idIngredient != 0) {
					Ingredient ingredient = ingredientService.getIngredientById(idIngredient);
					ingredient.setNom(nom);
					ingredient.setQuantite(quantite);
					ingredientService.updateIngredient(ingredient);
					
					this.doGet(request, response);
				}
				
			}else {
				request.setAttribute("erreur", erreur);
				this.doGet(request, response);
			}
			
		}

	}

}
