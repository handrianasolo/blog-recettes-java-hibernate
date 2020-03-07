<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layouts/header.jsp"%> 

<div id="global">
	<fieldset>
	<h3>Veuillez vous connectez pour ajouter une nouvelle recette, merci!</h3>
	<div id="login">
		<form method="post" action="login" >
			<input id="pseudo" name="pseudo" type="text" class="inputChamp" placeholder="Votre pseudo *"/><br/>
			
			<input id="mdp" name="mdp" type="password" class="inputChamp" placeholder="Votre mot de passe *"/><br/>
			
			<input type="submit" value="Se connecter" class="submitBtn" />
			ou <a href="inscription" style="color:#FF0000;">S'inscrire</a> 
		</form>
	</div>	
	<div id="erreur">
		<p style="color:red;"> <c:out value = "${erreur}" /> </p>
	</div>
	</fieldset>
</div>

<%@ include file="../layouts/footer.jsp" %>
