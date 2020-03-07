<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../layouts/header.jsp"%> 

<div id="global">
	<%@ include file="../layouts/nav.jsp"%>
	<h1>Ajout d'un tag</h1>
	<div id="tag">
		<form method="post" action="tag" >
			<input id="nom" name="nom" type="text" class="inputChamp" placeholder="Le nom du tag *"/><br/>
			<input type="submit" value="Ajouter un tag" class="submitBtn" />
		</form>
	</div>
	<c:forEach var="tag" items="${tags}">
		<li>
            ${tag.nom}  <a href="tag?id=${tag.id}"><span class="fa fa-trash"></span></a>
		</li>
    </c:forEach>
	<div id="erreur">
		<p style="color:red;"><c:out value = "${erreur}" /></p>
	</div>
</div>

<%@ include file="../layouts/footer.jsp" %>
