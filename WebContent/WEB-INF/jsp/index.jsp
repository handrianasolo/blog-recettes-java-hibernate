<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layouts/header.jsp"%>

<div id="global">
	<%@ include file="../layouts/nav.jsp"%>
	<c:forEach var="recettes"  items="${recettes}" >
		<div class="article">
				<div class="side">
					<img class="imgRecette" src="img/<c:out value='${recettes.photo}'/>" width="300px"
					height="242px" alt="<c:out value='${recettes.titre}'/>" />
				</div> 
				
				<div class="side">
					<a href="recette?id=<c:out value='${recettes.id}'/>">
						<h1 class="titreRecette"><c:out value="${recettes.titre}"/></h1>
					</a>
					<br>
					<c:forEach var="moyenne" begin = "1" end = "${recettes.moyenneNote}">
       					<span class="fa fa-star checked"></span>
      				</c:forEach>
					<c:forEach var="moyenne" begin = "${recettes.moyenneNote+1}" end = "5">
       					 <span class="fa fa-star"></span>
      				</c:forEach>
      				<c:out value="(${recettes.moyenneNote}/5) Avis"/>
					<br>
					<p><c:out value="${recettes.description}"/></p>
					<br>
					<time><c:out value="Créée le ${recettes.dateCreation}"/></time>
					<p>Par <c:out value="${recettes.membre.nom}"/></p>
				</div>
		</div>
		<hr />
	</c:forEach>
</div>

<%@ include file="../layouts/footer.jsp"%>