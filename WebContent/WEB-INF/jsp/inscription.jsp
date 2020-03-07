<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layouts/header.jsp"%> 

<div id="global">
	<h1>Inscription</h1>
	<div id="inscription">
		<form method="post" action="inscription" >
			
			<input id="nom" name="nom" type="text" class="inputChamp" placeholder="Votre nom *"
			value = '<c:out value = "${membre.nom}"/>'/><br/>
		
			<input id="pseudo" name="pseudo" type="text" class="inputChamp" placeholder="Votre pseudo *"
			value = '<c:out value = "${membre.pseudo}"/>'/><br/>
			
			<input id="email" name="email" type="text" class="inputChamp" placeholder="Votre email *" 
			value = '<c:out value = "${membre.email}"/>'/><br/>
			
			<input id="mdp" name="mdp" type="password" class="inputChamp" placeholder="Votre mot de passe *" /><br/>
			
			<input id="mdpconfirm" name="mdpconfirm" type="password" class="inputChamp" 
			placeholder="Confirmation de votre mot de passe *" /><br/>
			
			<input type="submit" value="S'inscrire" class="submitBtn" />
			
		</form>
	</div>	
	<div id="erreur">
		<p style="color:red;"> <c:out value = "${erreur}" /> </p>
	</div>
	<div id="message">
		<p style="color:red;"> <c:out value = "${message}" /> </p>
	</div>
</div>

<%@ include file="../layouts/footer.jsp" %>
