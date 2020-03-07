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
import com.recettes.models.Membre;
import com.recettes.models.Recette;
import com.recettes.services.CategorieService;
import com.recettes.services.RecetteService;

/**
 * Servlet implementation class CreerRecette
 */
@WebServlet(name="CreerRecette", urlPatterns = {"/creerRecette"})
public class CreerRecette extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerRecette() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		try {
			
			CategorieService categorieService = new CategorieService();
			List<Categorie> categories = categorieService.getAllCategorie();
			request.setAttribute("categories", categories);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/creerRecette.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String erreur="";
		
		// récuperer la categorie selectionnée
		int idCategorie = Integer.parseInt(request.getParameter("categorie"));
		
		String titre = request.getParameter("titre");
		if(titre.trim().isEmpty()) {
			erreur += "Veuillez saisir un titre valide<br>";
		}
		
		String description = request.getParameter("contenu");
		if(description.trim().isEmpty()) {
			erreur += "Veuillez saisir une description valide<br>";
		}
		
		String photo = request.getParameter("photo");
		if(photo.trim().isEmpty()) {
			erreur += "Veuillez entrer une photo valide<br>";
		}
		
		if(erreur.isEmpty()) {
    
			try {
				
				// récuperer le membre connecté
				if(session.getAttribute("membre")!=null) {
					Membre membre = (Membre) session.getAttribute("membre");
					
					// créer la categorie
					CategorieService categorieService = new CategorieService();
					Categorie categorie = categorieService.getCategorieById(idCategorie);
					
					// créer la recette
					RecetteService recetteService = new RecetteService();
					Recette recette = new Recette(titre, description, photo, new Date(), categorie, membre);
				
					Recette recetteCreated = recetteService.createRecette(recette);
					
					session.setAttribute("recette", recetteCreated);
		            response.sendRedirect("creerIngredient?id="+recetteCreated.getId());
		            
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
          
        }else {
        	
        	request.setAttribute("erreur", erreur);
        	this.doGet(request, response);
        }
	}

}
