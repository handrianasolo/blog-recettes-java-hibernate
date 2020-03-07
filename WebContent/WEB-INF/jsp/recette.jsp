<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="../layouts/header.jsp"%>

<div id="global">
	<%@ include file="../layouts/nav.jsp"%>
	<fieldset>
	<article>
		<header>
			<img class="imgRecette" src="img/<c:out value='${recette.photo}'/>" alt="<c:out value='${recette.titre}'/>" />
			<h1 class="titreRecette"><c:out value='${recette.titre}'/></h1>
			<c:forEach var="moyenne" begin = "1" end = "${recette.moyenneNote}">
       			 <span class="fa fa-star checked"></span>
      		</c:forEach>
			<c:forEach var="moyenne" begin = "${recette.moyenneNote+1}" end = "5">
       			 <span class="fa fa-star"></span>
      		</c:forEach>
			<c:out value="(${recette.moyenneNote}/5) Avis"/>
			<br>
			<br>
			<fieldset>
				<c:forEach var="tags" items="${tagsByRecette}">
					<c:out value="${tags.nom}"/>
				</c:forEach>
			</fieldset>
			<br>
			Ajouter un tag :
			<c:forEach var="tag"  items="${tags}" >
				<a href="recette?id=${recette.id}&idTag=${tag.id}">
					<input type="submit" class="submitBtn" name="tag" value="${tag.nom}"/>
				</a>
			</c:forEach>
			<br>
			<p><c:out value="${recette.description}"/></p>
			<time><c:out value="Créée le ${recette.dateCreation}"/></time>
			<p>Par <c:out value="${recette.membre.nom}"/></p>
		</header>
	</article>
	<header>
		<h2 id="titreIngredient">Ingrédients</h2>
		<ul>
		<c:forEach var="ingredients"  items="${ingredients}" >
			<li><c:out value='${ingredients.quantite}'/>
			&nbsp;<c:out value='${ingredients.unit}'/>
			&nbsp;<c:out value='${ingredients.nom}'/>
			</li>
		</c:forEach>
		</ul>
	</header>

	<h2 id="titreCommentaire">Commentaires</h2>

	<div class="divCommentaire">
	<c:forEach var="commentaires" items="${commentaires}" >
		<p><c:out value='${commentaires.auteur}'/> : <c:out value='${commentaires.contenu}'/></p>
		<p>Note : <c:out value='${commentaires.note}'/></p>
		<p>Date : <c:out value='${commentaires.dateCreation}'/></p>
		<hr>
	</c:forEach>
	</div>

	<form method="post" action="recette?id=<c:out value='${recette.id}'/>">
		<input id="auteur" name="auteur" type="text" placeholder="Votre nom *" class="inputChamp" />  <br />
		<textarea id="txtCommentaire" name="contenu" rows="4" placeholder="Votre commentaire *" class="inputTextArea">
		</textarea><br />
		<label for="note">Note</label><br /> 
		<select name="note" id="note" class="select">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
		</select> <br /> 
		<input type="submit" value="Commenter" class="submitBtn" />
	</form>
	<div id="erreur">
		<p><c:out value='${erreur}'/></p>
	</div>
		<div id="message">
		<p><c:out value='${message}'/></p>
	</div>
	</fieldset>
</div>
<%@ include file="../layouts/footer.jsp"%>