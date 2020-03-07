package com.recettes.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recettes.models.Recette;
import com.recettes.services.RecetteService;

/**
 * Servlet implementation class ModifierRecette
 */
@WebServlet(name = "ModifierRecette", urlPatterns = {"/modifierRecette"})
public class ModifierRecette extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierRecette() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int idRecette = 0;
		if(request.getParameter("idRecette") != null) {
			idRecette = Integer.parseInt(request.getParameter("idRecette"));
		}
		
		try {
			
			if(idRecette!=0) {
				
				RecetteService recetteService = new RecetteService();
				Recette recette = recetteService.getRecetteById(idRecette);
				request.setAttribute("recette", recette);
			}
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/modifierRecette.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String erreur = "";
		
		int idRecette = 0;
		if(request.getParameter("idRecette") != null) {
			idRecette = Integer.parseInt(request.getParameter("idRecette"));
		}
		
		String titre = request.getParameter("titre");
		if(titre.trim().isEmpty()) {
			erreur += "Veuillez entrer un titre de la recette valide<br>";
		}
		
		String description = request.getParameter("contenu");
		if(description.trim().isEmpty()) {
			erreur += "Veuillez entrer une description valide<br>";
		}
		
		if(erreur.isEmpty()) {
			try {
				
				RecetteService recetteService  = new RecetteService();
				Recette recette = recetteService.getRecetteById(idRecette);
				recette.setDescription(description);
				recette.setTitre(titre);
				recetteService.updateRecette(recette);
				
				session.setAttribute("recette", recette);
	            response.sendRedirect("modifierIngredient?id="+recette.getId());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			
			request.setAttribute("erreur", erreur);
			this.doGet(request, response);
		}
		
	}

}
