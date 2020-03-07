package com.recettes.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recettes.models.Categorie;
import com.recettes.models.Recette;
import com.recettes.services.CategorieService;
import com.recettes.services.RecetteService;

/**
 * Servlet implementation class MesRecettes
 */
@WebServlet(name="MesRecettes", urlPatterns = {"/mesRecettes"})
public class MesRecettes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MesRecettes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// récuperer l'id du membre connecté passé en url
		int idMembre = Integer.parseInt(request.getParameter("idMembre"));
		
		int idRecette = 0;
		if(request.getParameter("idRecette") != null) {
			idRecette = Integer.parseInt(request.getParameter("idRecette"));
		}
		
		try {
			
			CategorieService categorieService = new CategorieService();
			List<Categorie> categories = categorieService.getAllCategorie();
			request.setAttribute("categories", categories);
			
			RecetteService recetteService = new RecetteService();
			if(idRecette!=0){

				Recette recette = recetteService.getRecetteById(idRecette);
				
				// supprimer une recette
				recetteService.deleteRecette(recette);
			}
			
			// afficher les recettes du membre connecté
			List<Recette> recettesByMembre = recetteService.getAllRecetteByMembre(idMembre);
			if(recettesByMembre == null) {
				request.setAttribute("message", "vous n'avez pas de recettes");
			}else {
				request.setAttribute("recettesByMembre", recettesByMembre);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/mesRecettes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
