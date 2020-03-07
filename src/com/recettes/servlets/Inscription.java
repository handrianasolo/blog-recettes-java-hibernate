package com.recettes.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recettes.models.Membre;
import com.recettes.services.MembreService;


/**
 * Servlet implementation class Inscription
 */
@WebServlet(name="Inscription", urlPatterns = {"/inscription"})
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date dateInscription = new Date();
		String erreur="";
		
		String nom = request.getParameter("nom");
		if(nom.trim().isEmpty()) {
			erreur += "Veuillez saisir un nom valide<br>";
		}
		
		String pseudo = request.getParameter("pseudo");
		if(pseudo.trim().isEmpty()) {
			erreur += "Veuillez saisir un pseudo valide<br>";
		}
		
		String email = request.getParameter("email");
		if(email.trim().isEmpty()) {
			erreur += "Veuillez saisir un email valide<br>";
		}
		
		String mdp = request.getParameter("mdp");
		if(mdp.trim().isEmpty()) {
			erreur += "Veuillez saisir un mot de passe valide valide<br>";
		}
		
		String motDePasseConfirm = request.getParameter("mdpconfirm");
		if(motDePasseConfirm.trim().isEmpty() || !motDePasseConfirm.equals(mdp)) {
			erreur += "La confirmation de mot de passe n'est pas identitque<br>";
		}
		
		Membre membre = new Membre(nom, pseudo, mdp, email, dateInscription);
	
		if(erreur.isEmpty()) {
    
			try {
				
				MembreService membreService = new MembreService();
				membreService.createMembre(membre);
				request.setAttribute("message", "votre inscription a été enregistrée...vous pouver vous connecter maintenant");
				this.doGet(request, response);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
          
        }else {
        	request.setAttribute("erreur", erreur);
        	request.setAttribute("membre", membre);
        	this.doGet(request, response);
        }
	}

}
