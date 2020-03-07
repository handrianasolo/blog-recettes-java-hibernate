<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/header.jsp"%>

<div id="global">
	<%@ include file="../layouts/nav.jsp"%>
	<c:forEach var="recettes"  items="${recettesByMembre}" >
		<div class="article">
			<div class="side"><img class="imgRecette" src="img/<c:out value='${recettes.photo}'/>" width="300px"
				height="242px" alt="<c:out value='${recettes.titre}'/>" />
			</div> 
			
			<div class="side">
				<a href="recette?id=<c:out value='${recettes.id}'/>">
					<h1 class="titreRecette"><c:out value="${recettes.titre}"/></h1>
				</a>
				<c:forEach var="moyenne" begin = "1" end = "${recettes.moyenneNote}">
       				<span class="fa fa-star checked"></span>
      			</c:forEach>
				<c:forEach var="moyenne" begin = "${recettes.moyenneNote+1}" end = "5">
       				<span class="fa fa-star"></span>
      			</c:forEach>
      		
				<br>
				<time><c:out value="Créée le ${recettes.dateCreation}"/></time>
				<br/>
				<p><c:out value="${recettes.description}"/></p>
				
				<p><a class="primaryBtn" href="modifierRecette?idMembre=${membre.id}&idRecette=${recettes.id}">Modifier</a>
				<a class="primaryBtn" href="<c:out value='mesRecettes?idMembre=${membre.id}&idRecette=${recettes.id}'/>">Supprimer</a></p>
			</div>
		</div>
		<hr />

	</c:forEach>
	<div id="message">
		<p><c:out value='${message}'/></p>
	</div>
</div>

<%@ include file="../layouts/footer.jsp"%>