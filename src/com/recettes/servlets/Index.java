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
 * Servlet implementation class Index
 */
@WebServlet(name = "Index", urlPatterns = {"", "/index"})
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
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
			
			// afficher tous les recettes
			RecetteService recetteService = new RecetteService();
			List<Recette> recettes = recetteService.getAllRecette();
			request.setAttribute("recettes", recettes);
			
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
