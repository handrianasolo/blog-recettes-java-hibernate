<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="../layouts/header.jsp"%>

<div id="global">
<fieldset>
	<article>
		<header>
			<img class="imgRecette" src="img/<c:out value='${recette.photo}'/>" alt="<c:out value='${recette.titre}'/>" />
			<br>
			<h1 class="titreRecette"><c:out value='${recette.titre}'/></h1>
			<br>
			<time>  <c:out value="Créée le ${recette.dateCreation}"/> </time>
		</header>
		<p><c:out value='${recette.description}'/></p>
	</article>
	<header>
		<h2 id="titreIngredient">Ingrédients</h2>
		<ul>
		<c:forEach var="ingredient"  items="${ingredients}" >
			<li><c:out value='${ingredient.quantite}'/>
			&nbsp;<c:out value='${ingredient.unit}'/>
			&nbsp;<c:out value='${ingredient.nom}'/>
			</li>
		</c:forEach>
		</ul>
	</header>
	<div id="modifierIngredient">
		<form method="post" action="modifierIngredient?id=${recette.id}">
		<c:forEach var="ingredient"  items="${ingredients}" >
		 	<fieldset>
				<h2>Etape 2 : Modifier les ingrédients</h2>
				<label for="nom">Nom : </label>
				<input id="nom" name="nom" type="text" class="inputChamp" value='<c:out value="${ingredient.nom}"/>' />
				<label for="quantite">Quantité : </label>
				<input type="number" id="quantite" name="quantite" value='<c:out value="${ingredient.quantite}"/>' min="0" max="1000" step="any">
				<label for="unit"><c:out value="${ingredient.unit}"/></label>
				&nbsp;&nbsp;<input type="submit" value="Sauvegarder" class="submitBtn" />
			</fieldset>
		</c:forEach>
		</form>
		<br>
		<a class="primaryBtn login" href="index">Terminer</a>
	</div>
	<div id="erreur">
		<p style="color:red;"> <c:out value = "${erreur}" /> </p>
	</div>
</fieldset>
</div>

<%@ include file="../layouts/footer.jsp"%>