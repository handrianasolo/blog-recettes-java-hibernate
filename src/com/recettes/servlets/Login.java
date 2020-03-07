package com.recettes.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSession;

import com.recettes.models.Membre;
import com.recettes.services.MembreService;

//import com.recettes.models.Membre;
//import com.recettes.services.MembreService;

/**
 * Servlet implementation class Login
 */
@WebServlet(name="Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// creer un booleen logout pour la déconnexion  
		Boolean logout = Boolean.parseBoolean(request.getParameter("logout"));
		
		// creer une boucle pour la session
		if (logout != null && logout == true) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("index");
		}
		
		// creer la redirection vers login
		else {

			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		MembreService membreService = null;
		Membre membre = new Membre();
		String erreur = "";
		
		String pseudo = request.getParameter("pseudo");
		if(pseudo.trim().isEmpty()) {
			erreur += "Veuillez saisir un pseudo valide<br>";
		}
		
		String mdp = request.getParameter("mdp");
		if(mdp.trim().isEmpty()) {
			erreur += "Veuillez saisir un mot de passe valide valide<br>";
		}
		
		if(erreur.isEmpty()) {
			try {
				
				// test membre connecté
				membreService = new MembreService();
				membre = membreService.getMembreByPseudoMdp(pseudo, mdp);
				
				// test si membre existe
				if(!membre.equals(null)) {
					session.setAttribute("membre", membre);
					response.sendRedirect("index");
				
				}else {
					erreur += "Pseudo ou mot de passe incorrects<br>";
					request.setAttribute("erreur", erreur);
					this.doGet(request, response);
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
