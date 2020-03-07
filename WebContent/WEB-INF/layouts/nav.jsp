<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
	
<div id="categorie">
	<ul>
	<c:forEach var="categorie"  items="${categories}" >
	<li>
		<a href="<c:out value='categorie?idCategorie=${categorie.id}'/>">
			${categorie.nom}
		</a>
	</li>
	</c:forEach>  
	</ul>
<hr />
</div>