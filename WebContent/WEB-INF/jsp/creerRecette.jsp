<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layouts/header.jsp"%> 

<div id="global">
	<div id="creerRecette">
		<form method="post" action="creerRecette">
		 	<fieldset>
				<h2>Etape 1: Ajouter une recette</h2>
				
				<label for="titre">Nom : </label><br>
				<input id="titre" name="titre" type="text" class="inputChamp" placeholder="Titre de la recette *" /><br><br>
				
				<label for="description">Description : </label><br>
				<textarea id="txtCommentaire" name="contenu" placeholder="Description de la recette *" class="inputTextArea">
				</textarea><br><br>
				
				<label for="photo">Image : </label>
				<input id="photo" name="photo" type="file" accept=".jpg, .png" /><br><br>
				
				<label for="dateCreation">Date : </label>
				<input id="dateCreation" name="dateCreation" type="date" /><br><br>
				
				<label for="categorie">Catégorie : </label>
				<select name="categorie">
					<c:forEach var="categorie" items="${categories}">
						<option value='<c:out value="${categorie.id}"/>'><c:out value="${categorie.nom}"/></option>
					</c:forEach>
				</select>
				<br><br>
				<input type="submit" value="Créer la recette" class="submitBtn" />
			</fieldset>
		</form>
	</div>	
	<div id="erreur">
		<p style="color:red;"> <c:out value = "${erreur}" /> </p>
	</div>
</div>

<%@ include file="../layouts/footer.jsp" %>