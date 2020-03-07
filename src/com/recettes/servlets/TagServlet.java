package com.recettes.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recettes.models.Categorie;
import com.recettes.models.Tag;
import com.recettes.services.CategorieService;
import com.recettes.services.TagService;

/**
 * Servlet implementation class TagServlet
 */
@WebServlet(name = "TagServlet", urlPatterns = {"/tag"})
public class TagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TagServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Tag tag = null;
		
		int idTag = 0;
		if(request.getParameter("id") != null) {
			idTag = Integer.parseInt(request.getParameter("id"));
		}
		
		try {
			
			CategorieService categorieService = new CategorieService();
			List<Categorie> categories = categorieService.getAllCategorie();
			request.setAttribute("categories", categories);
			
			TagService ts = new TagService();
			if(idTag!=0){

				tag = ts.getTagById(idTag);
				ts.deleteTag(tag);
			}
			
			List<Tag> tags = ts.getAllTags();
			request.setAttribute("tags", tags);
			
			
				
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tag.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String erreur = "";
		String nomTag = request.getParameter("nom");
		if (nomTag.isEmpty()) {
			erreur+="Veuillez saisir un tag <br>";
		}
		
		if(erreur.isEmpty()) {
			try {
				
				// créer de nouveaux tags
				TagService tagService = new TagService();
				Tag tag = new Tag(nomTag);
				tagService.createTag(tag);
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
