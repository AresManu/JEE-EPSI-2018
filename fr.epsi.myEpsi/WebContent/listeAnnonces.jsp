<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des annonces</title>
</head>
<body>

<h1>Liste des annonces:</h1>

  
<c:forEach items="${result}" var="item"> 
<tr>
<td><c:out value="${item.id}" /></td>
<td><c:out value="${item.titre}" /></td>
<td><c:out value="${item.description}" /></td>
</tr>
</c:forEach>

<p><a href="creerannonces">Cr�er une annonce</a></p>



</body>
</html>