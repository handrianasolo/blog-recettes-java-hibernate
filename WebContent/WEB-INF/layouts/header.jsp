<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
	<title>Mon Blog de Recettes</title> 
	<meta http-equiv="Content-Type" content="text/html;  charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href='<c:url value="css/style.css"/>' rel="stylesheet" type="text/css"/>

</head>
<body>
<header id="header">
	<a href="index">
		<h1 id="titreBlog">Mon Blog de Recettes</h1>
	</a>
	<div style="width:300px;margin:20px auto;">
		Bienvenue sur mon blog de recettes
	</div>
	<div id="loginBar">
		<div class="login">
			<a class="primaryBtn login" href="tag">Administration Tags</a>
			<c:choose>
			    <c:when test="${empty sessionScope.membre.pseudo}">
			        <a class="primaryBtn login" href="login">Se connecter</a> 
			    </c:when>    
			    <c:otherwise>
			        Bonjour ${sessionScope['membre'].pseudo}!
			        <a class="primaryBtn login" href="<c:out value='mesRecettes?idMembre=${membre.id}'/>">Mes recettes</a>
			        <a class="primaryBtn login" href="<c:out value='creerRecette?idMembre=${membre.id}'/>">+Recette</a>
			        <a class="primaryBtn login" href="login?logout=true">Se deconnecter</a>
			    </c:otherwise>
			</c:choose>
		</div>
	</div>
</header>

