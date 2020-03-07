<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layouts/header.jsp"%> 

<div id="global">
	<div id="creerRecette">
		<form method="post" action="modifierRecette?idMembre=${membre.id}&idRecette=${recette.id}">
		 	<fieldset>
				<h2>Etape 1: Modifier la recette</h2>
				<div class="side">
				<img class="imgRecette" src="img/<c:out value='${recette.photo}'/>" alt="<c:out value='${recette.titre}'/>" /><br>
				</div>
				
				<div class="side">
				<label for="titre">Titre : </label><br>
				<input id="titre" name="titre" type="text" class="inputChamp" value='<c:out value="${recette.titre}"/>' /><br>
				
				<label for="description">Description : </label><br>
				<textarea id="txtCommentaire" name="contenu" class="inputTextArea"><c:out value="${recette.description}"/>
				</textarea><br>
				
				<label for="date">Date de création : </label>
				<time><c:out value="${recette.dateCreation}"/></time><br>
				
				<label for="titre">Catégorie : <c:out value="${recette.categorie.nom}"/> </label>
				<br>
				<br>
				<input type="submit" value="Sauvegarder" class="submitBtn" />
				</div>
			</fieldset>
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