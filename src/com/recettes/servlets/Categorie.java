package com.recettes.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recettes.models.Recette;
import com.recettes.services.CategorieService;
import com.recettes.services.RecetteService;

/**
 * Servlet implementation class Categorie
 */
@WebServlet(name="Categorie", urlPatterns = {"/categorie"})
public class Categorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Categorie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// récuperer l'id de la catégorie passé en url
		int idCategorie = Integer.parseInt(request.getParameter("idCategorie"));
		
		try {
			
			// afficher les categories
			CategorieService categorieService = new CategorieService();
			List<com.recettes.models.Categorie> categories = categorieService.getAllCategorie();
			request.setAttribute("categories", categories);
			
			// afficher les recettes par categories
			RecetteService recetteService = new RecetteService();
			List<Recette> recettesByCategorie= recetteService.getAllRecetteByCategorie(idCategorie);
			request.setAttribute("recettesByCategorie", recettesByCategorie);
           
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/categorie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
