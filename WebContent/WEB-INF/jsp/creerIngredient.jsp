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
	<h3>Etape 2: Ajouter les ingrédients</h3>
	<div class="field_wrapper">
	<fieldset>
	<form method="post" action="creerIngredient?id=${recette.id}">
		<input id="nom" name="nom" type="text" class="inputChamp" placeholder="Nom de l'ingrédient *" />
		<label for="quantite">Quantité : </label>
		<input type="number" id="quantite" name="quantite" value="0" min="0" max="1000" step="any">
		<label for="unit">Unit : </label>
		<select name="unit" id="unit" class="select">
			<option value="g">g</option>
			<option value="l">l</option>
			<option value="kg">kg</option>
			<option value="u">u</option>
			<option value="p">p</option>
			<option value="cs">cs</option>
			<option value="cc">cc</option>
		</select>
	<br/>
	<input type="submit" value="Ajouter les ingrédients" class="submitBtn" />
	</form>
	</fieldset>
	</div>
	<a class="primaryBtn login" href="index">Terminer</a>
	<div id="erreur">
		<p><c:out value='${erreur}'/></p>
	</div>
</fieldset>
</div>
<%@ include file="../layouts/footer.jsp"%>