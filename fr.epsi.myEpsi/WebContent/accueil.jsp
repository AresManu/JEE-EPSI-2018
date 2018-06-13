<%@page import="fr.epsi.myEpsi.beans.Utilisateur"%>
<%@page import="fr.epsi.myEpsi.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
</head>
<body>
	
<% Utilisateur utilisateur = (Utilisateur) session.getAttribute(Constantes.PARAM_UTILISATEURS); %>
Bonjour <%= utilisateur.getId() %>
<p>

<p> <a href="listeannonces">Afficher les annonces</a></p>
</p>
</body>
</html>